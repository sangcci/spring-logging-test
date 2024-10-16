package sangcci.springloggingtest.common.querycounter;

public class QueryCounter {

    private int count;

    void increment() {
        count++;
    }
    int getCount() {
        return count;
    }

    boolean isWarn() {
        return count >= 10;
    }
}
