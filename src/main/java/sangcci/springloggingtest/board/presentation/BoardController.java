package sangcci.springloggingtest.board.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sangcci.springloggingtest.board.application.BoardService;
import sangcci.springloggingtest.board.domain.Board;
import sangcci.springloggingtest.board.dto.BoardRequest;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<Board> getBoardById(
            @PathVariable Long id
    ) {
        Board board = boardService.getBoardById(id);
        return ResponseEntity.ok()
                .body(board);
    }

    @PostMapping("/api/boards")
    public ResponseEntity<Board> createBoards(
            @RequestBody List<BoardRequest> boardRequests
    ) {
        boardService.registBoards(boardRequests);
        return ResponseEntity.noContent()
                .build();
    }
}
