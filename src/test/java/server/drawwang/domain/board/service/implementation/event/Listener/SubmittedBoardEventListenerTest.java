package server.drawwang.domain.board.service.implementation.event.Listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.board.service.implementation.event.SubmittedBoardEvent;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class SubmittedBoardEventListenerTest {
    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    void updateKingIfEmptyThread() {
        //given
        ThreadEntity threadEntity = threadRepository.save(ThreadEntity.builder()
                .threadName("쓰레드 이름")
                .kingBoardId(null)
                .expirationDate(LocalDateTime.now())
                .build());
        BoardEntity boardEntity = boardRepository.save(BoardEntity.builder()
                .userName("유저 이름")
                .thread(threadEntity)
                .imageUrl("이미지 경로")
                .likes(0)
                .reports(0)
                .build());
        SubmittedBoardEvent event = new SubmittedBoardEvent(threadEntity, boardEntity);

        //when
        publisher.publishEvent(event);
        TestTransaction.flagForCommit();
        TestTransaction.end();

        //then
        ThreadEntity savedThreadEntity = threadRepository.findAll().get(0);
        assertThat(savedThreadEntity.getKingBoardId()).isEqualTo(1L);
    }
}