package sangcci.springloggingtest.common.logtracer;

import java.util.UUID;

public class TraceStatus {

    private final UUID id;
    private int level;

    public TraceStatus() {
        this.id = UUID.randomUUID();
        this.level = 0;
    }

    public String getId() {
        return id.toString().split("-")[0];
    }

    public int getAndIncrement() {
        return level++;
    }

    public int getAndDecrement() {
        return --level;
    }
}
