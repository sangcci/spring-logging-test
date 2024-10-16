package sangcci.springloggingtest.common.logtracer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class LogTracer {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String ERR_PREFIX = "<X-";

    private final TraceStatusHolder traceStatusHolder;

    public void startTrace(String methodName) {
        // 1 - get traceStatus
        TraceStatus traceStatus = traceStatusHolder.getTraceStatus();

        // 2 - get id and level
        String traceId = traceStatus.getId();
        int level = traceStatus.getAndIncrement();

        // 3 - convert level to space
        String space = convertLevelToSpace(level);

        // 4 - logging
        log.info("[{}]{}|{} {}",
                traceId, space, START_PREFIX, methodName);
    }

    public void exceptionTrace(String methodName, Throwable throwable) {
        TraceStatus traceStatus = traceStatusHolder.getTraceStatus();

        String traceId = traceStatus.getId();
        int level = traceStatus.getAndDecrement();

        String space = convertLevelToSpace(level);

        log.info("[{}]{}|{} {}",
                traceId, space, ERR_PREFIX, methodName);

        log.info("e: {}", throwable.getMessage());
    }

    public void endTrace(String methodName, Long startTime) {
        // 1 - get traceStatus
        TraceStatus traceStatus = traceStatusHolder.getTraceStatus();

        // 2 - get id and level
        String traceId = traceStatus.getId();
        int level = traceStatus.getAndDecrement();

        // 3 - get end time
        long endTime = System.currentTimeMillis();

        // 4 - convert level to space
        String space = convertLevelToSpace(level);

        log.info("[{}]{}|{} {} | time: {} ms",
                traceId, space, COMPLETE_PREFIX, methodName, endTime - startTime);
    }

    private String convertLevelToSpace(int level) {
        return "|  ".repeat(Math.max(0, level));
    }
}
