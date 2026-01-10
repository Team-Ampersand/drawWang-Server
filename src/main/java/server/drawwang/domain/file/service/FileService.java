package server.drawwang.domain.file.service;

import server.drawwang.domain.file.entity.ToFileResponse;

import java.util.List;

public interface FileService {
    List<ToFileResponse> listImage(long[] paramBoardId);
}
