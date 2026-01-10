package server.drawwang.domain.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.drawwang.domain.file.entity.ToFileResponse;
import server.drawwang.domain.file.entity.dto.response.FileListResponse;
import server.drawwang.domain.file.service.FileService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class FileController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<FileListResponse> downloadImage(@RequestParam("boardId") long[] paramBoardId) {
        List<ToFileResponse> responses = fileService.listImage(paramBoardId);
        return new ResponseEntity<>(new FileListResponse(responses), HttpStatus.OK);
    }
}


