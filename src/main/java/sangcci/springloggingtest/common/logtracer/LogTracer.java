package sangcci.springloggingtest.common.logtracer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class LogTracer {

    public Long startTrace(String methodName) {
        // 1. trace id 생성


        // start time 생성
        Long startTime = System.currentTimeMillis();

        // 2. logging
        log.info("{}", methodName);

        return startTime;
    }

    public void endTrace(String methodName, Long startTime) {
        // 1. traceId get
        long endTime = System.currentTimeMillis();
        log.info("{} | time: {} ms", methodName, endTime - startTime);
    }
}
