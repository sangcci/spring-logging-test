package sangcci.springloggingtest.board.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sangcci.springloggingtest.board.application.BoardService;
import sangcci.springloggingtest.board.domain.Board;

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
}
