package server.drawwang.domain.board.entity.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.drawwang.domain.board.entity.ToBoardResponse;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardListResponse {
    private final List<ToBoardResponse> boards;
}
