package tufu.datastructure.linkedlist.single;

import java.util.Iterator;

/**
 * Created by hw on 2019/8/28.
 */
public class SingleLinkedList implements Iterable{
    // 结点个数
    public int length;
    // 头结点
    public HeroNode firstNode;
    // 尾结点
    private HeroNode lastNode;

    public SingleLinkedList() {}

    public void add(HeroNode node) {
        if (node == null)
            return;
        if (firstNode==null) {
            firstNode = node;
        } else {
            lastNode.next = node;
        }
        lastNode = node;
        length += 1;
    }

    public HeroNode delete(int no) {
        if (length<=0)
            return null;
        HeroNode lastCurrent = null;
        HeroNode current = firstNode;
        // 先找到要删除的位置
        while (current != null && current.no != no) {
            lastCurrent = current;
            current = current.next;
        }
        if (current == firstNode) {
            // 首节点，需要调整
            firstNode = firstNode.next;
        } else if (current == firstNode){
            // 尾结点，需要调整
            lastCurrent.next = null;
            lastNode = lastCurrent;
        } else if (current!=null){
            lastCurrent.next = current.next;
        }
        return current;
    }

    public HeroNode update(HeroNode node) {
        if (length<=0)
            return null;
        HeroNode current = firstNode;
        // 先找到要更新的位置
        while (current != null && current.no != node.no) {
            current = current.next;
        }
        if (current != null) {
            current.name = node.name;
            current.nickname = node.nickname;
        }
        return current;
    }

    @Override
    public String toString() {
        String result = "";
        HeroNode current = firstNode;
        // 先找到要更新的位置
        while (current != null) {
            result += current + "\n";
            current = current.next;
        }
        return result;
    }


    public Iterator iterator() {
        return new Iterator() {
            private HeroNode current = firstNode;
            public boolean hasNext() {
                return current != null;
            }

            public Object next() {
                HeroNode node = current;
                current = current.next;
                return node;
            }

            public void remove() {

            }
        };
    }
}

