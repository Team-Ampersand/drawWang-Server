package server.drawwang.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Void> createBoard(@Validated @RequestBody BoardSubmitRequest request) {
        boardService.submitBoard(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BoardListResponse> listBoard() {
        List<ToBoardResponse> response = boardService.listBoard();

        return new ResponseEntity<>(new BoardListResponse(response), HttpStatus.OK);
    }
}
