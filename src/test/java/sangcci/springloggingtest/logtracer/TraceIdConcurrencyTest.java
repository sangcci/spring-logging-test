package sangcci.springloggingtest.logtracer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import sangcci.springloggingtest.common.logtracer.ThreadLocalTraceStatusHolder;
import sangcci.springloggingtest.common.logtracer.TraceStatus;
import sangcci.springloggingtest.common.logtracer.TraceStatusHolder;

public class TraceIdConcurrencyTest {

    private final TraceStatusHolder traceStatusHolder = new ThreadLocalTraceStatusHolder();

    @Test
    void id_concurrency_test() throws ExecutionException, InterruptedException {
        // given
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // when
        Set<String> traceIds = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            String traceId = executorService.submit(() -> {
                TraceStatus traceStatus = traceStatusHolder.getTraceStatus();
                return traceStatus.getId();
            }).get();

            traceIds.add(traceId);
        }

        // then
        Assertions.assertThat(traceIds).hasSize(5);
    }
}
