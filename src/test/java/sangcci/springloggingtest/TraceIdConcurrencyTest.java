package sangcci.springloggingtest;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sangcci.springloggingtest.common.logtracer.ThreadLocalTraceIdHolder;
import sangcci.springloggingtest.common.logtracer.TraceId;
import sangcci.springloggingtest.common.logtracer.TraceIdHolder;

public class TraceIdConcurrencyTest {

    private final TraceIdHolder traceIdHolder = new ThreadLocalTraceIdHolder();;

    @Test
    void id_concurrency_test() throws ExecutionException, InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // when
        Set<TraceId> traceIds = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            TraceId traceId = executorService.submit(traceIdHolder::getTraceId)
                    .get();
            traceIds.add(traceId);
        }

        // then
        Assertions.assertThat(traceIds).hasSize(5);
    }
}
