package other;

/**
 * the class charge to count balls blocks and score.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     * @param counter charge to count balls blocks and score.
     */
    public Counter(int counter) {
        this.counter = counter;
    }
    /**
     * @param number add it to current count.
     */
    public void increase(int number) {
        counter += number;
    }
    /**
     * @param number subtract it from current count.
     */
    public void decrease(int number) {
        counter -= number;
    }
    /**
     * @return current count.
     */
    public int getValue() {
        return counter;
    }

}
