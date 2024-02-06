package server.drawwang.domain.thread.service;

import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;
import server.drawwang.domain.thread.entity.dto.response.ToThreadResponse;

import java.util.List;

public interface ThreadService {
    void createThread(CreateThreadRequest createThreadRequest);

    List<ToThreadResponse> listThread();

}
