package sangcci.springloggingtest.common.logtracer;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalTraceIdHolder implements TraceIdHolder {

    private final ThreadLocal<TraceId> traceIdThreadLocal;

    public ThreadLocalTraceIdHolder() {
        traceIdThreadLocal = new ThreadLocal<>();
    }

    @Override
    public TraceId getTraceId() {
        TraceId traceId = traceIdThreadLocal.get();
        if (traceId == null) {
            traceId = new TraceId();
            traceIdThreadLocal.set(traceId);
            return traceId;
        }
        return traceId;
    }
}
