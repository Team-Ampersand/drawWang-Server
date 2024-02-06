package server.drawwang.domain.thread.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToThreadResponse {
    private Long threadId;

    private String threadName;

    private Long kingBoardId;

    private LocalDateTime expirationDate;

}
