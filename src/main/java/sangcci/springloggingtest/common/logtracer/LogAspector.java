package sangcci.springloggingtest.common.logtracer;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
public class LogAspector {

    private final LogTracer logTracer;

    @Pointcut("execution(* sangcci.springloggingtest..*Controller.*(..)) "
            + "|| execution(* sangcci.springloggingtest..*Service.*(..)) "
            + "|| execution(* sangcci.springloggingtest..*Repository.*(..))"
    )
    private void all() {}

    @Around("all()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Long startTime = System.currentTimeMillis();
        boolean catchException = false;

        try {
            logTracer.startTrace(methodName);
            return joinPoint.proceed();
        } catch(Exception e) {
            logTracer.exceptionTrace(methodName, e);
            catchException = true;
            throw e;
        } finally {
            if (!catchException) {
                logTracer.endTrace(methodName, startTime);
            }
        }
    }
}
