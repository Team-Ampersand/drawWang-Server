package server.drawwang.domain.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToBoardResponse {
    private Long id;

    private String userName;

    private Long threadId;

    private String imageUrl;

    private Integer likes;
    private Integer reports;
}
