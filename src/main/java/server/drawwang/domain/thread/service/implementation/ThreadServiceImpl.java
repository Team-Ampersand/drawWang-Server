package server.drawwang.domain.thread.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;
import server.drawwang.domain.thread.entity.dto.response.ToThreadResponse;
import server.drawwang.domain.thread.repository.ThreadRepository;
import server.drawwang.domain.thread.service.ThreadService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createThread(CreateThreadRequest createThreadRequest) {
        ThreadEntity threadEntity = ThreadEntity.builder()
                .threadName(createThreadRequest.getThreadName())
                .build();
        threadRepository.save(threadEntity);
    }

    @Override
    public List<ToThreadResponse> listThread() {
        return threadRepository.findAll()
                .stream()
                .map(threadEntity -> {
                    Long kingBoardId = threadEntity.getKingBoardId();
                    String kingImageUrl = ""; 

                    if (kingBoardId != null) {
                        BoardEntity boardEntity = boardRepository.findById(kingBoardId).orElse(null);
                        if (boardEntity != null) {
                            kingImageUrl = boardEntity.getImageUrl();
                        }
                    }

                    return new ToThreadResponse(
                            threadEntity.getId(),
                            threadEntity.getThreadName(),
                            kingBoardId,
                            kingImageUrl,
                            threadEntity.getExpirationDate()
                    );
                })
                .toList();
    }
}
