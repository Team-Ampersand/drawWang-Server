package server.drawwang.domain.board.service.implementation.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.drawwang.domain.board.entity.BoardEntity;
import server.drawwang.domain.thread.entity.ThreadEntity;

@Getter
@AllArgsConstructor
public class SubmittedBoardEvent {
    private ThreadEntity threadEntity;
    private BoardEntity boardEntity;
}
