package server.drawwang.domain.thread.entity.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ThreadListResponse {
    private final List<ToThreadResponse> Threads;
}
