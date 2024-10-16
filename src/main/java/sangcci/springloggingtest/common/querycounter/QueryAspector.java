package sangcci.springloggingtest.common.querycounter;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
public class QueryAspector {

    @Pointcut("execution(* javax.sql.DataSource.getConnection(..))")
    private void connection() {}

    @Around("connection()")
    public Object counting(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1 - getConnection()
        Connection connection = (Connection) joinPoint.proceed();

        // 2 - query counter 생성
        QueryCounter queryCounter = new QueryCounter();

        // 3 - Connection Proxy 생성 및 반환
        return Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Connection.class},
                new ConnectionProxy(connection, queryCounter)
        );
    }
}
