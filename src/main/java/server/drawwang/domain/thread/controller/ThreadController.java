package server.drawwang.domain.thread.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;
import server.drawwang.domain.thread.entity.dto.response.ThreadListResponse;
import server.drawwang.domain.thread.entity.dto.response.ToThreadResponse;
import server.drawwang.domain.thread.service.ThreadService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/thread")
public class ThreadController {
    private final ThreadService threadService;

    @PostMapping
    public ResponseEntity<Void> createThread(@Validated @RequestBody CreateThreadRequest createThreadRequest) {
        threadService.createThread(createThreadRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ThreadListResponse> getThread() {
        List<ToThreadResponse> threads = threadService.getThread();
        return new ResponseEntity<>(new ThreadListResponse(threads), HttpStatus.OK);
    }
}
