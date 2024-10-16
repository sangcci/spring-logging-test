package sangcci.springloggingtest.board.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sangcci.springloggingtest.board.exception.BoardErrorCode;
import sangcci.springloggingtest.board.exception.BoardException;
import sangcci.springloggingtest.common.response.Response;

@RestControllerAdvice
public class BoardControllerAdvice {

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<Response<String>> boardExceptionHandler(BoardException e) {
        BoardErrorCode boardErrorCode = e.getBoardErrorCode();

        HttpStatus httpStatus = boardErrorCode.getStatus();
        String message = boardErrorCode.getMessage();

        return ResponseEntity.status(httpStatus)
                .body(Response.error(httpStatus.value(), message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> exceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error"));
    }
}
