package sangcci.springloggingtest.logtracer;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import sangcci.springloggingtest.board.presentation.BoardController;

public class PathTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    @Test
    void pointcut_type_match_test() throws NoSuchMethodException {
        pointcut.setExpression("execution(* sangcci.springloggingtest..*Controller.*(..))");

        Method getBoardById = BoardController.class.getMethod("getBoardById", Long.class);
        assertThat(pointcut.matches(getBoardById, BoardController.class)).isTrue();
    }

    @Test
    void within_type_match_test() {
        pointcut.setExpression("within(sangcci.springloggingtest..*Controller)");

        assertThat(pointcut.matches(BoardController.class)).isTrue();
    }
}
