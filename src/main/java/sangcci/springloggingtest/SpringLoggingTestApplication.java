package sangcci.springloggingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringLoggingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLoggingTestApplication.class, args);
    }
}
