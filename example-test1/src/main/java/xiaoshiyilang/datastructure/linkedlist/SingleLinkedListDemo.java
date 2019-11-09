package xiaoshiyilang.datastructure.linkedlist;

/**
 * @program: example-test
 * @description: 单向链表
 * @author: zhang.cheng
 * @create: 2019-08-23 09:02
 **/

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

        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);

        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();
    }


}

class SingleLinkedList {
    /**
     * 先初始化一个头节点, 头节点不要动, 不存放具体的数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的next 指向 新的节点
     */

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后, 将temp后移(最后一个next为null)
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    /**
     * 遍历链表
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头结点不能动，用一个临时变量
        HeroNode temp = head.next;

        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //后移查找下一个节点
            temp = temp.next;
        }


    }
}

/**
 * 定义节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;

    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}