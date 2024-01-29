package server.drawwang.domain.board.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.drawwang.domain.board.entity.ToBoardResponse;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponse {
    List<ToBoardResponse> boards;
}
