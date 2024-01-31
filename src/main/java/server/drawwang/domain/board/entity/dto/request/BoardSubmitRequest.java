package server.drawwang.domain.board.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardSubmitRequest {
    @NotNull
    private final Long threadId;

    @NotBlank
    private final String userName;

    @NotBlank
    private final String imageUrl;
}
