package sangcci.springloggingtest.common.logtracer;

public class TraceLevel {

    private int level;

    public TraceLevel() {
        this.level = 0;
    }

    public int getAndIncrement() {
        return ++level;
    }

    public int getAndDecrement() {
        return --level;
    }
}
