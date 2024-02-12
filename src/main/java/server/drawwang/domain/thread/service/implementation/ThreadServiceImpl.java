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
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createThread(CreateThreadRequest createThreadRequest) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusHours(24);

        ThreadEntity threadEntity = ThreadEntity.builder()
                .threadName(createThreadRequest.getThreadName())
                .expirationDate(expirationDate)
                .build();

        threadRepository.save(threadEntity);
    }

    @Override
    public List<ToThreadResponse> getThread() {
        return threadRepository.findAll().stream()
                .map(threadEntity -> {
                    Long kingBoardId = threadEntity.getKingBoardId();
                    String kingImageUrl = Optional.ofNullable(kingBoardId)
                            .flatMap(id -> boardRepository.findById(id).map(BoardEntity::getImageUrl))
                            .orElse("");

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

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateThreadKing(Long threadId) {
        ThreadEntity threadEntity = threadRepository.findById(threadId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.THREAD_NOT_FOUND_ERROR));
        validateThreadKing(threadEntity);

        Comparator<BoardEntity> comparator = Comparator.comparing(BoardEntity::getLikes);
        BoardEntity maxBoardEntity = boardRepository.findAllByThread(threadEntity).stream()
                .max(comparator)
                .orElseThrow(() -> new CustomException(CustomErrorCode.BOARD_NOT_FOUND_ERROR));

        threadEntity.setKingBoardId(maxBoardEntity.getId());
    }

    public void validateThreadKing(ThreadEntity threadEntity) {
        if (threadEntity.getKingBoardId() != null) {
            throw new CustomException(CustomErrorCode.THREAD_KING_ALREADY_EXISTS);
        }

        if (threadEntity.getExpirationDate().isAfter(LocalDateTime.now())) {
            throw new CustomException(CustomErrorCode.THREAD_NOT_EXPIRED);
        }
    }
}
