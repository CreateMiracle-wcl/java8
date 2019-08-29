package tufu.datastructure.tree.heap;

import java.util.Comparator;

/**
 * Created by hw on 2019/8/29.
 */
public class Heap {
    private int capacity;
    private Integer[] queue;// 默认小顶堆
    private int size;
    private final Comparator comparator;

    public Heap(int capacity, Comparator comparable) {
        this.capacity = capacity;
        this.comparator = comparable;
        this.queue = new Integer[this.capacity];
    }

    public Heap(int capacity, Integer[] queue, int size, Comparator comparator) {
        this.capacity = capacity;
        this.queue = queue;
        this.size = size;
        this.comparator = comparator;
        heapify();
    }

    /*
    * 从倒数第二层的非叶子节点开始，依次 siftDown 进行堆化（确保每个父节点都比其左右孩子还要小，直到根节点）
    * */
    private void heapify() {
        // (size >>> 1) - 1 为第一个非叶子节点
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, queue[i]);
    }

    private void grow() {
        int oldCapacity = size;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        Integer[] newQueue = new Integer[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        this.capacity = newCapacity;
    }

    public void add(Integer e) {
        if (size>=capacity)
            grow();
        queue[size++] = e;
        if (size>1) {
            siftUp(size-1, e);
        }
    }
    // 非根节点才进行处理
    private void siftUp(int k, Integer e) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            if (comparator.compare(queue[parent], queue[k])>0) {
                Integer parentV = queue[parent];
                queue[parent] = e;
                queue[k] = parentV;
                k = parent;
            } else {
                break;
            }
        }
    }

    public Integer poll() {
        if (size<=0)
            return -1;
        Integer pop = queue[0];
        size -= 1;
        queue[0] = queue[size];
        if (size>1) {
            siftDown(0, queue[0]);
        }
        return pop;
    }
    // 重点是：如何排除叶子节点
    private void siftDown(int k, Integer e) {
        int half = size >>> 1;        // half 的作用是：只允许非叶子节点进入（loop while a non-leaf）
        if (k>=half)
            return;
        int leftChild = (k << 1) + 1;
        int rightChild = leftChild + 1;
        if (rightChild<size && comparator.compare(queue[leftChild], queue[rightChild])>0) {
            // 注意这里的 rightChild < size ，因为 if（k>=half）没办法排除仅有一个子节点的非叶子节点
            queue[k] = queue[rightChild];
            queue[rightChild] = e;
            siftDown(rightChild, e);
        } else {
            queue[k] = queue[leftChild];
            queue[leftChild] = e;
            siftDown(leftChild, e);
        }
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
