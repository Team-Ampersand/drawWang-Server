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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ThreadRepository threadRepository;

    @Override
    @Transactional
    public void submitBoard(BoardSubmitRequest request) throws RuntimeException {
        Optional<ThreadEntity> threadEntity = threadRepository.findById(request.getThreadId());

        if(threadEntity.isPresent()){
            BoardEntity boardEntity = new BoardEntity(
                    request.getUserName(),
                    threadEntity.get(),
                    request.getImageUrl(),
                    0);

            boardRepository.save(boardEntity);
        } else throw new RuntimeException();
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
