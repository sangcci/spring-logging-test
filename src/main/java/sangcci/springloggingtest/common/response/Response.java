package sangcci.springloggingtest.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Response<T> {

    private final int code;
    private final T msg;

    public static Response<String> error(int code, String msg) {
        return new Response<>(code, msg);
    }

    public static <T> Response<T> success(int code, T data) {
        return new Response<>(code, data);
    }

}
