package tufu.datastructure.queue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by hw on 2019/8/23.
 */
public class CircleQueue<E> implements Queue<E> {
    private Integer capacity;
    private E[] loadArr;
    private Integer DEFAULT = 3;
    /*
    * head 指向第一个元素
    * rear 指向最后一个元素再往后一个位置
    * */
    private int head, rear;

    public CircleQueue() {
        this.capacity = DEFAULT;
        this.loadArr = (E[])new Object[this.capacity];
        this.head = 0;
        this.rear = 0;
    }

    public CircleQueue(Integer capacity) {
        this.capacity = capacity!=0?capacity+1:DEFAULT;
        this.loadArr = (E[])new Object[this.capacity];
        this.head = 0;
        this.rear = 0;
    }

    public Boolean isEmpty() {
        return head == rear;
    }

    public Boolean isFull() {
        return (rear+1)%capacity == head;
    }
    /*
    * 先往队尾追加一个元素，再将 rear 往后移一个空位
    * */
    public void enqueue(E o) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        loadArr[rear++] = o;
        rear = rear%capacity;
    }
    /*
    * 先将队首的元素出队，再将 head 更新往后移动一个位置
    * */
    public E dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        E e = loadArr[head++];
        head = head%capacity;
        return e;
    }

    public Integer getSize() {
        return (rear+capacity-head)%capacity;
    }

    public void print() {
        for (int i = head; i < head+getSize(); i++) {
            Integer idx = i%capacity;
            System.out.println("load[" + i + "] = " + loadArr[idx]);
        }
    }
}
