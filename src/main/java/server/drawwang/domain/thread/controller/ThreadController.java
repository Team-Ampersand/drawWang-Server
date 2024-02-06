package server.drawwang.domain.thread.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.drawwang.domain.thread.entity.dto.request.CreateThreadRequest;
import server.drawwang.domain.thread.service.ThreadService;

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
}
