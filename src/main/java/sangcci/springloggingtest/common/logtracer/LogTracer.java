package sangcci.springloggingtest.common.logtracer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class LogTracer {

    private final TraceIdHolder traceIdHolder;

    public Long startTrace(String methodName, Long startTime) {
        // 1. trace id 생성
        TraceId traceId = traceIdHolder.getTraceId();

        // 2. logging
        log.info("[{}] {}", traceId.getId(), methodName);

        return startTime;
    }

    public void endTrace(String methodName, Long startTime) {
        // 1. traceId get
        TraceId traceId = traceIdHolder.getTraceId();

        long endTime = System.currentTimeMillis();
        log.info("[{}] {} | time: {} ms",
                traceId.getId(),
                methodName,
                endTime - startTime);
    }
}
