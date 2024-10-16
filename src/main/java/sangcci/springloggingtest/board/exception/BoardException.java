package sangcci.springloggingtest.board.exception;

import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private final BoardErrorCode boardErrorCode;

    public BoardException(BoardErrorCode boardErrorCode) {
        super(boardErrorCode.getMessage());
        this.boardErrorCode = boardErrorCode;
    }
}
