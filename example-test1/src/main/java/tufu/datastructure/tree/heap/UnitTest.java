package tufu.datastructure.tree.heap;

import java.util.Comparator;

/**
 * Created by hw on 2019/8/29.
 */
public class UnitTest {

    public static void main(String[] args)  {
        //baseOperation();
        initialize();
    }

    public static void baseOperation() {
        Heap heap = new Heap(10, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        heap.add(9);
        heap.add(7);
        heap.add(8);
        heap.add(67);
        heap.add(80);
        heap.add(-1);
        heap.add(9);
        heap.add(78);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }

    public static void initialize() {
        Integer[] queue = {10, 20, 8, 9, 33, 12};
        Heap heap = new Heap(queue.length, queue, queue.length, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
