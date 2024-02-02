package server.drawwang.global.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private CustomErrorCode status;
    private String statusMessage;
}
