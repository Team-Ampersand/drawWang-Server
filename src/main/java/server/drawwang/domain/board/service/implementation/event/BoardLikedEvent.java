package server.drawwang.domain.board.service.implementation.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.drawwang.domain.board.entity.BoardEntity;

@Getter
@AllArgsConstructor
public class BoardLikedEvent {
    private BoardEntity boardEntity;
}
