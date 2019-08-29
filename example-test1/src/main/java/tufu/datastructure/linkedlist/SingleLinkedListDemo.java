package tufu.datastructure.linkedlist;
import tufu.datastructure.linkedlist.single.HeroNode;
import tufu.datastructure.linkedlist.single.SingleLinkedList;
import tufu.datastructure.queue.CircleQueue;
import tufu.datastructure.queue.Queue;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by hw on 2019/8/28.
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //增
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        // 问题
        //reversedFindByIdx(singleLinkedList, 4);
        //reversedLinkedList(singleLinkedList);
        reversedPrintLinkedList(singleLinkedList);
        /*
        // 删
        singleLinkedList.delete(4);
        // 改
        hero3 = new HeroNode(10, "无用", "智多星");
        singleLinkedList.update(hero3);
        //
        System.out.println(singleLinkedList);
        */
    }

    // 查找单链表中的倒数第 k 个结点 【新浪面试题】
    public static void reversedFindByIdx(SingleLinkedList linkedList, int idx) {
        System.out.println(linkedList);
        if (idx<=0 || idx>linkedList.length)
            return;
        Iterator iterator = linkedList.iterator();
        Queue queue = new CircleQueue(idx);
        while (iterator.hasNext()) {
            HeroNode node = (HeroNode)iterator.next();
            if (queue.isFull()) {
                queue.dequeue();
            }
            queue.enqueue(node);
        }
        System.out.println("===============");
        queue.print();
        System.out.println("===============");
        System.out.println(queue.dequeue());
    }

    // 单链表的反转【腾讯面试题，有点难度】
    public static void reversedLinkedList(SingleLinkedList linkedList) {
        System.out.println(linkedList);
        Iterator iterator = linkedList.iterator();
        HeroNode lastNode = null;
        HeroNode curNode = (HeroNode)iterator.next();
        while (curNode != null) {
            HeroNode nextNode = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode =  nextNode;
        }
        linkedList.firstNode = lastNode;
        System.out.println("===============");
        System.out.println(linkedList);
    }

    // 从尾到头打印单链表【百度，要求方式1:反向遍历。方式2:Stack栈】
    public static void reversedPrintLinkedList(SingleLinkedList linkedList) {
        System.out.println(linkedList);
        Iterator iterator = linkedList.iterator();
        Stack stack = new Stack();
        while (iterator.hasNext()) {
            HeroNode node = (HeroNode)iterator.next();
            stack.add(node);
        }
        System.out.println("===============");
        while (stack.size()!=0) {
            System.out.println(stack.pop());
        }
    }
}
