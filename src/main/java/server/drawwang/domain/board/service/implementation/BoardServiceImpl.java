package server.drawwang.domain.board.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.board.entity.dto.request.BoardSubmitRequest;
import server.drawwang.domain.board.repository.BoardRepository;
import server.drawwang.domain.board.service.BoardService;
import server.drawwang.domain.thread.entity.ThreadEntity;
import server.drawwang.domain.thread.repository.ThreadRepository;
import server.drawwang.global.exception.ThreadNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ThreadRepository threadRepository;

    @Override
    @Transactional
    public void submitBoard(BoardSubmitRequest request) {
        ThreadEntity threadEntity = threadRepository.findById(request.getThreadId())
                .orElseThrow(ThreadNotFoundException::new);

        BoardEntity boardEntity = BoardEntity.builder()
                .userName(request.getUserName())
                .thread(threadEntity)
                .imageUrl(request.getImageUrl())
                .likes(0)
                .build();

        boardRepository.save(boardEntity);
    }

    @Override
    public List<ToBoardResponse> listBoard() {

        return boardRepository.findAll()
                .stream()
                .map( boardEntity -> new ToBoardResponse(
                        boardEntity.getId(),
                        boardEntity.getUserName(),
                        boardEntity.getThread().getId(),
                        boardEntity.getImageUrl(),
                        boardEntity.getLikes()))
                .toList();
    }
}