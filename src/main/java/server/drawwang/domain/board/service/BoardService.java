package server.drawwang.domain.board.service;

import org.springframework.web.multipart.MultipartFile;
import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.board.entity.dto.request.BoardSubmitRequest;

import java.util.List;

public interface BoardService {
    void submitBoard(BoardSubmitRequest request, MultipartFile file);
    List<ToBoardResponse> listBoard();

    void boardLike(Long board);

    void boardReport(Long board);
}
