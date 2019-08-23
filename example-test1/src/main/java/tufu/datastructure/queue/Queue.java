package tufu.datastructure.queue;

/**
 * Created by hw on 2019/8/23.
 */
public interface Queue<E> {
    public Boolean isEmpty();
    public void enqueue(E e) throws RuntimeException;
    public E dequeue() throws RuntimeException;
    public Integer getSize();
    public void print();
    public Boolean isFull();
}
