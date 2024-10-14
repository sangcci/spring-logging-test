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

    public Long startTrace(String methodName, Long startTime) {
        // 1 - get traceStatus
        TraceStatus traceStatus = traceStatusHolder.getTraceStatus();

        // 2 - get id and level
        String traceId = traceStatus.getId();
        int level = traceStatus.getAndIncrement();

        // 3 - convert level to space
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < level; i++) {
            space.append("|  ");
        }
        space.append("|").append(START_PREFIX);

        // 4 - logging
        log.info("[{}]{} {}", traceId, space, methodName);

        return startTime;
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
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < level; i++) {
            space.append("|  ");
        }
        space.append("|").append(COMPLETE_PREFIX);

        log.info("[{}]{} {} | time: {} ms",
                traceId,
                space,
                methodName,
                endTime - startTime);
    }
}
