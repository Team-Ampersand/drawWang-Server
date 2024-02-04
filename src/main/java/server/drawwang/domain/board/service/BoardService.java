package server.drawwang.domain.board.service;

import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.board.entity.dto.request.BoardSubmitRequest;

import java.util.List;

public interface BoardService {
    void submitBoard(BoardSubmitRequest request);
    List<ToBoardResponse> listBoard();

    void boardLike(Long boardId);

    void boardReport(Long boardId);
}
