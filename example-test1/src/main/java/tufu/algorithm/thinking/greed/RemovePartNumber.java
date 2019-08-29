package tufu.algorithm.thinking.greed;

import tufu.datastructure.tree.heap.Heap;

import java.util.Comparator;

/**
 * Created by hw on 2019/8/29.
 * 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
 */
public class RemovePartNumber {

    class Node {
        Integer value;
        public Integer frequency;

        public Node(Integer value, Integer frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", frequency=" + frequency +
                    '}';
        }
    }
    private Integer number;
    private Integer count;

    public RemovePartNumber(Integer number, Integer count) {
        this.number = number;
        this.count = count;
    }

    public boolean excute() {
        Heap heap = new Heap(count, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        String rawString = number.toString();
        StringBuffer buffer = new StringBuffer(rawString);
        for (int i = 0; i < count; i++) {
            String item = rawString.substring(i, i+1);
            heap.add(new Integer(item));
        }
        // 移除的对象为
        for (int i = 0; i < count; i++) {
            Integer e = heap.poll();
            System.out.println(e);
        }
        return true;
    }

    public static void main(String[] args)  {
        Integer count = 4;
        RemovePartNumber partNumber = new RemovePartNumber(67965902, count);
        partNumber.excute();
    }
}
