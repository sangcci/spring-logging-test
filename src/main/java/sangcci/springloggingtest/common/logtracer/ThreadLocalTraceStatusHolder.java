package sangcci.springloggingtest.common.logtracer;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalTraceStatusHolder implements TraceStatusHolder {

    private final ThreadLocal<TraceStatus> traceStatusThreadLocal;

    public ThreadLocalTraceStatusHolder() {
        traceStatusThreadLocal = new ThreadLocal<>();
    }

    @Override
    public TraceStatus getTraceStatus() {
        TraceStatus traceStatus = traceStatusThreadLocal.get();
        if (traceStatus == null) {
            traceStatus = new TraceStatus();
            traceStatusThreadLocal.set(traceStatus);
            return traceStatus;
        }
        return traceStatus;
    }
}
