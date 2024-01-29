package server.drawwang.domain.board.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardSubmitRequest {
    @NotNull
    private Long threadId;

    @NotBlank
    private String userName;

    @NotBlank
    private String imageUrl;
}
