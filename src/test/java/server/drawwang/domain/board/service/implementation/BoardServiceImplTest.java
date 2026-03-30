package server.drawwang.domain.board.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.board.entity.dto.request.BoardSubmitRequest;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.board.service.BoardService;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        boardRepository.deleteAll();
        threadRepository.deleteAll();
    }

    @Test
    void submitBoard() {
        //given
        ThreadEntity threadEntity = createThreadEntity();
        BoardSubmitRequest boardSubmitRequest = new BoardSubmitRequest(threadEntity.getId(), "유저 이름", "이미지 경로");

        //when
        boardService.submitBoard(boardSubmitRequest);

        //then
        ThreadEntity savedThreadEntity = threadRepository.findAll().get(0);
        BoardEntity savedBoardEntity = boardRepository.findAll().get(0);
        BoardEntity expectedBoardEntity = BoardEntity.builder()
                .id(savedBoardEntity.getId())
                .userName("유저 이름")
                .thread(savedThreadEntity)
                .imageUrl("이미지 경로")
                .likes(0)
                .reports(0)
                .build();
        assertThat(savedBoardEntity).usingRecursiveComparison().isEqualTo(expectedBoardEntity);
    }

    @Test
    void listBoard() {
        //given
        ThreadEntity threadEntity = createThreadEntity();
        BoardEntity boardEntity = createBoardEntity(threadEntity);

        //when
        List<ToBoardResponse> boardList = boardService.listBoard();

        //then
        List<ToBoardResponse> expectedBoardList = new ArrayList<>();
        expectedBoardList.add(new ToBoardResponse(boardEntity.getId(), "유저 이름", threadEntity.getId(), "이미지 경로", 0, 0));
        assertThat(boardList).usingRecursiveComparison().isEqualTo(expectedBoardList);
    }

    @Test
    void boardLike() {
        //given
        ThreadEntity threadEntity = createThreadEntity();
        BoardEntity boardEntity = createBoardEntity(threadEntity);

        //when
        boardService.boardLike(boardEntity.getId());

        //then
        BoardEntity savedBoardEntity = boardRepository.findAll().get(0);
        assertThat(savedBoardEntity.getLikes()).isEqualTo(1);
    }

    @Test
    void boardReport() {
        //given
        ThreadEntity threadEntity = createThreadEntity();
        BoardEntity boardEntity = createBoardEntity(threadEntity);

        //when
        boardService.boardReport(boardEntity.getId());

        //then
        BoardEntity savedBoardEntity = boardRepository.findAll().get(0);
        assertThat(savedBoardEntity.getReports()).isEqualTo(1);
    }

    ThreadEntity createThreadEntity() {
        return threadRepository.save(ThreadEntity.builder()
                .threadName("쓰레드 이름")
                .kingBoardId(1L)
                .expirationDate(LocalDateTime.now())
                .build());
    }

    BoardEntity createBoardEntity(ThreadEntity threadEntity) {
        return boardRepository.save(BoardEntity.builder()
                .userName("유저 이름")
                .thread(threadEntity)
                .imageUrl("이미지 경로")
                .likes(0)
                .reports(0)
                .build());
    }
}