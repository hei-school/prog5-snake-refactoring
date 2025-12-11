package snake;

public class Score {
    private int value = 0;

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }

    public void reset() {
        value = 0;
    }
}
