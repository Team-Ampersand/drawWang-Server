package server.drawwang.domain.board.service.implementation.event.Listener;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.service.implementation.event.SubmittedBoardEvent;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;

@Component
@RequiredArgsConstructor
public class SubmittedBoardEventListener {
    private final ThreadRepository threadRepository;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateKingIfEmptyThread(SubmittedBoardEvent event) {
        ThreadEntity threadEntity = event.getThreadEntity();
        BoardEntity boardEntity = event.getBoardEntity();

        if(threadEntity.getKingBoardId() == null) {
            threadEntity.setKingBoardId(boardEntity.getId());
            threadRepository.save(threadEntity);
        }
    }
}
