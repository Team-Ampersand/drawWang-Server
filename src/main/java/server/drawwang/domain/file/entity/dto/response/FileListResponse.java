package server.drawwang.domain.file.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.drawwang.domain.file.entity.ToFileResponse;

import java.util.List;

@Getter
@AllArgsConstructor
public class FileListResponse {
    List<ToFileResponse> imageBytes;
}
