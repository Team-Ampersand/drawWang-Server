package server.drawwang.domain.board.service.implementation.event.Listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.board.service.implementation.event.BoardLikedEvent;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardLikedEventListenerTest {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void updateThreadKing() {
        //given
        ThreadEntity threadEntity = threadRepository.save(ThreadEntity.builder()
                .threadName("쓰레드 이름")
                .kingBoardId(1L)
                .expirationDate(LocalDateTime.now())
                .build());
        boardRepository.save(BoardEntity.builder()
                .userName("1번째 유저 이름")
                .thread(threadEntity)
                .imageUrl("1번째 이미지 경로")
                .likes(0)
                .reports(0)
                .build());
        BoardEntity boardEntity = boardRepository.save(BoardEntity.builder()
                .userName("2번째 유저 이름")
                .thread(threadEntity)
                .imageUrl("2번째 이미지 경로")
                .likes(1)
                .reports(0)
                .build());
        BoardLikedEvent event = new BoardLikedEvent(boardEntity);

        //when
        publisher.publishEvent(event);
        TestTransaction.flagForCommit();
        TestTransaction.end();

        //then
        ThreadEntity savedThreadEntity = threadRepository.findAll().get(0);
        assertThat(savedThreadEntity.getKingBoardId()).isEqualTo(2L);
    }
}