package server.drawwang.domain.thread.service;

import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;

public interface ThreadService {
    void createThread(CreateThreadRequest createThreadRequest);
}
