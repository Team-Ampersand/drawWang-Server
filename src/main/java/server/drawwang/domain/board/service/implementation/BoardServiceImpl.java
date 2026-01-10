package server.drawwang.domain.board.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.board.entity.dto.request.BoardSubmitRequest;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.board.service.BoardService;
import server.drawwang.domain.board.service.implementation.event.BoardLikedEvent;
import server.drawwang.domain.board.service.implementation.event.SubmittedBoardEvent;
import server.drawwang.domain.file.FileStore;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ThreadRepository threadRepository;
    private final ApplicationEventPublisher publisher;
    private final FileStore fileStore;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void submitBoard(BoardSubmitRequest request, MultipartFile file) {
        ThreadEntity threadEntity = threadRepository.findById(request.getThreadId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.THREAD_NOT_FOUND_ERROR));

        BoardEntity boardEntity = BoardEntity.builder()
                .userName(request.getUserName())
                .thread(threadEntity)
                .imageId(fileStore.storeFile(file))
                .likes(0)
                .reports(0)
                .build();

        boardRepository.save(boardEntity);

        publisher.publishEvent(new SubmittedBoardEvent(threadEntity, boardEntity));
    }

    @Override
    public List<ToBoardResponse> listBoard() {

        return boardRepository.findAll()
                .stream()
                .map(boardEntity -> new ToBoardResponse(
                        boardEntity.getId(),
                        boardEntity.getUserName(),
                        boardEntity.getThread().getId(),
                        boardEntity.getImageId(),
                        boardEntity.getLikes(),
                        boardEntity.getReports()))
                .toList();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void boardLike(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND_ERROR));
        boardEntity.plusLike();

        publisher.publishEvent(new BoardLikedEvent(boardEntity));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void boardReport(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND_ERROR));
        boardEntity.plusReports();
    }
}
