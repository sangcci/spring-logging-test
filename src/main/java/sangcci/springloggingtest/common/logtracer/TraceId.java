package sangcci.springloggingtest.common.logtracer;

import java.util.UUID;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TraceId {

    private final UUID id;

    public TraceId() {
        this.id = UUID.randomUUID();
    }

    public String getId() {
        return id.toString().split("-")[0];
    }
}
