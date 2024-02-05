package server.drawwang.domain.board.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class BoardStateUpdate {
    private final Long boardId = 0L;

}
