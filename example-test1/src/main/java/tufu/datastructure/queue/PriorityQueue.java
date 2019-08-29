package tufu.datastructure.queue;

/**
 * Created by hw on 2019/8/29.
 */
public class PriorityQueue implements Queue {
    private CircleQueue queue;

    public PriorityQueue(Integer capacity) {
        this.queue = new CircleQueue(capacity);
    }

    public Boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(Object o) throws RuntimeException {

    }

    public Object dequeue() throws RuntimeException {
        return null;
    }

    public Integer getSize() {
        return null;
    }

    public void print() {

    }

    public Boolean isFull() {
        return null;
    }
}
