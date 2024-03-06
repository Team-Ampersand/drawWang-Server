package server.drawwang.domain.thread.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToThreadResponse {
    private final Long threadId;

    private final String threadName;

    private final Long kingBoardId;

    private final String kingImageUrl;

    private final LocalDateTime expirationDate;

}
