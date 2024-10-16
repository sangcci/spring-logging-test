package sangcci.springloggingtest.board.dto;

import sangcci.springloggingtest.board.domain.Board;

public record BoardRequest(
        String title,
        String content
) {

    public Board toEntity() {
        return new Board(title, content);
    }
}
