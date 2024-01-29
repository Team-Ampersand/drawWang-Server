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
        threadRepository.save(new ThreadEntity("Test", "Test", 1L));
    }
}
