package server.drawwang.domain.board.service.implementation.event.Listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.board.service.implementation.event.BoardLikedEvent;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

@Component
@RequiredArgsConstructor
public class BoardLikedEventListener {
    private final ThreadRepository threadRepository;
    private final BoardRepository boardRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateThreadKing(BoardLikedEvent event) {
        BoardEntity boardEntity = event.getBoardEntity();
        ThreadEntity threadEntity = boardEntity.getThread();

        BoardEntity kingBoardEntity = boardRepository.findById(boardEntity.getThread().getKingBoardId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.THREAD_KING_NOT_FOUND_ERROR));

        if (boardEntity.getLikes() > kingBoardEntity.getLikes()) {
            threadEntity.setKingBoardId(boardEntity.getId());
            threadRepository.save(threadEntity);
        }
    }
}
