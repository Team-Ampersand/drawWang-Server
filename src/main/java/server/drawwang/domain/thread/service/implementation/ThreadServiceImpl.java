package server.drawwang.domain.thread.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;
import server.drawwang.domain.thread.repository.ThreadRepository;
import server.drawwang.domain.thread.service.ThreadService;


@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createThread(CreateThreadRequest createThreadRequest) {
        ThreadEntity threadEntity = ThreadEntity.builder()
                .threadName(createThreadRequest.getThreadName())
                .kingBoardId(1L)
                .build();
        threadRepository.save(threadEntity);
    }
}
