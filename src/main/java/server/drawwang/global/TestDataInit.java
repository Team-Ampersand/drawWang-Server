/*
package server.drawwang.global;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ThreadRepository threadRepository;

    @PostConstruct
    public void Init() {
        ThreadEntity threadEntity = ThreadEntity.builder()
                .threadName("Test")
                .expireTime("Test")
                .kingBoardId(1L)
                .build();

        threadRepository.save(threadEntity);
    }
}
*/
