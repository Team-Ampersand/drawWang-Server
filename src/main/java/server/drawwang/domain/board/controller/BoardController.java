package server.drawwang.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.drawwang.domain.board.entity.ToBoardResponse;
import server.drawwang.domain.board.entity.dto.request.BoardSubmitRequest;
import server.drawwang.domain.board.entity.dto.response.BoardListResponse;
import server.drawwang.domain.board.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> createBoard(@Validated @RequestPart(value = "request") BoardSubmitRequest request,
                                            @RequestPart(value = "image") MultipartFile file) {
        boardService.submitBoard(request, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BoardListResponse> listBoard() {
        List<ToBoardResponse> response = boardService.listBoard();
        return new ResponseEntity<>(new BoardListResponse(response), HttpStatus.OK);
    }

    @PatchMapping("/{boardId}/like")
    public ResponseEntity<Void> boaardLikes(@PathVariable Long boardId) {
        boardService.boardLike(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{boardId}/report")
    public ResponseEntity<Void> boardReport(@PathVariable Long boardId) {
        boardService.boardReport(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
