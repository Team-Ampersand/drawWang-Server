package server.drawwang.domain.file.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToFileResponse {

    private final long boardId;
    private final byte[] imageByte;
}
