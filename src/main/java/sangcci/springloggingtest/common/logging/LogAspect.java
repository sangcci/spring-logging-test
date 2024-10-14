package sangcci.springloggingtest.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* sangcci.springloggingtest..*Controller.*(..))")
    private void controller() {}

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    private void transactional() {}

    @Pointcut("execution(* sangcci.springloggingtest..application.*.*(..))")
    private void application() {}

    //@Before("controller()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Controller Logging");
        log.info(joinPoint.toShortString());
    }

    //@After("controller()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Service Logging");
        log.info(joinPoint.toShortString());
    }

    @AfterThrowing(value = "application()", throwing = "e")
    public void logThrow(JoinPoint joinPoint, RuntimeException e) throws Exception{
        log.info("error: {}", e.getClass());
        log.info("Throw Logging");
    }

    @Around("application()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long end = System.currentTimeMillis();
            long timeinMs = end - start;
            log.info("{} | time = {}ms", joinPoint.getSignature().getDeclaringTypeName(), timeinMs);
        }
    }
}
