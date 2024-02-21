package server.drawwang.domain.thread.service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;
import server.drawwang.domain.thread.entity.dto.response.ToThreadResponse;
import server.drawwang.domain.thread.repository.ThreadRepository;
import server.drawwang.domain.thread.service.ThreadService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ThreadServiceImplTest {

    @Autowired
    ThreadService threadService;
    @Autowired
    ThreadRepository threadRepository;

    @BeforeEach
    void setup() {
        threadRepository.deleteAll();
    }

    @Test
    void createThread() {
        //given
        CreateThreadRequest request = new CreateThreadRequest("쓰레드 이름");

        //when
        threadService.createThread(request);

        //then
        ThreadEntity savedThreadEntity = threadRepository.findAll().get(0);
        ThreadEntity expectedThreadEntity = ThreadEntity.builder()
                .id(savedThreadEntity.getId())
                .threadName("쓰레드 이름")
                .kingBoardId(null)
                .expirationDate(savedThreadEntity.getExpirationDate())
                .build();
        assertThat(savedThreadEntity).usingRecursiveComparison().isEqualTo(expectedThreadEntity);
    }

    @Test
    void getThread() {
        //given
        ThreadEntity threadEntity = threadRepository.save(ThreadEntity.builder()
                .id(1L)
                .threadName("쓰레드 이름")
                .kingBoardId(null)
                .expirationDate(LocalDateTime.now())
                .build());

        //when
        List<ToThreadResponse> threadList = threadService.getThread();

        //then
        List<ToThreadResponse> expactedThreadList = new ArrayList<>();
        expactedThreadList.add(new ToThreadResponse(threadEntity.getId(), "쓰레드 이름", null, "", threadEntity.getExpirationDate()));
        assertThat(threadList).usingRecursiveComparison().isEqualTo(expactedThreadList);
    }
}