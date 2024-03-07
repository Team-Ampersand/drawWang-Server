package server.drawwang.domain.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.drawwang.domain.file.FileStore;
import server.drawwang.global.exception.CustomErrorCode;
import server.drawwang.global.exception.CustomException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class FileController {
    private final FileStore fileStore;

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) {
        try {
            Resource resource = new UrlResource("file:" + fileStore.getFullPath(filename));

            if(resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                byte[] imageBytes = resource.getInputStream().readAllBytes();

                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }
            else {
                throw new CustomException(CustomErrorCode.IMAGE_NOT_FOUND_ERROR);
            }
        } catch (Exception e) {
            throw new CustomException(CustomErrorCode.IMAGE_NOT_FOUND_ERROR);
        }
    }
}


