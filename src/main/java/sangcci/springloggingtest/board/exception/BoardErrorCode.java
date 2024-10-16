package sangcci.springloggingtest.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum BoardErrorCode {

    NOT_ALLOW_CONTENT(HttpStatus.BAD_REQUEST, "부적절한 내용이 있습니다.");

    private final HttpStatus status;
    private final String message;
}
