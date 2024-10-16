package sangcci.springloggingtest.common.querycounter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ConnectionProxy implements InvocationHandler {

    private final Object target;
    private final QueryCounter queryCounter;

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("prepareStatement")) {
            log.info("query log: {}", args[0]);
            queryCounter.increment();
        }

        if (method.getName().equals("close")) {
            if (queryCounter.getCount() >= 1) {
                log.info("query count: {}", queryCounter.getCount());
            }
        }

        return method.invoke(target, args);
    }
}
