package tufu.algorithm.thinking.greed;

import java.util.*;

/**
 * Created by hw on 2019/8/29.
 * 用贪心算法实现霍夫曼编码
 * 霍夫曼编码，不仅会考察文件中有多少个不同字符，还会考察每个字符出现的频率，
 * 根据频率的不同，选择不同长度的编码，来进一步增加压缩的效率。
 * 根据贪心的思想：把出现频率出现多的字符，用稍微短一些的编码；
 *               出现频率比较少的字符，用稍微长一些的编码。
 *
 * 如何根据字符出现频率的不同，给不同的字符进行不同长度的编码呢...
 */
public class HuffmanCoding {

    static class Node {
        public String key;
        public Integer frequency;

        public Node(String key, Integer frequency) {
            this.key = key;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", frequency=" + frequency +
                    '}';
        }
    }

    private static String testData = "abababcabcacdeadbfced";

    public static void main(String[] args)  {
        Integer length = testData.length();
        Map<String, Integer> map = new HashMap(length);
        for (int i = 0; i < length; i++) {
            char cur = testData.charAt(i);
            String key = ""+cur;
            Integer frequency = 1;
            if (map.containsKey(key)) {
                frequency = (Integer) map.get(key);
                frequency += 1;
            }
            map.put(key, frequency);
        }
        //
        PriorityQueue<Node> priorityQueue = new PriorityQueue(map.size(), new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                return o1.frequency-o2.frequency;
            }
        });
        // 频率
        for(String key : map.keySet()){
            priorityQueue.add(new Node(key, map.get(key)));
        }
        //
        for (int i = 0; i < map.size(); i++) {
            Node node = priorityQueue.poll();
            System.out.println(node);
        }
    }
}
