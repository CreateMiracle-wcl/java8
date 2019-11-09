package xiaoshiyilang.datastructure.hashtab;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: example-test
 * @description: 哈希表demo
 * @author: zhang.cheng
 * @create: 2019-11-07 07:33
 **/

public class HashTabDemo {

    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        HashMap hashMap = new HashMap();

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }


}

/**
 * 创建HashTab管理链表（数组管理链表）
 */
class HashTab {
    /**
     * 链表数组
     */
    private EmplinkedList[] empLinkedListArray;
    /**
     * 表示有多少个链表
     */
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmplinkedList[size];
        //初始化每一个链表 否则会报空指针异常
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmplinkedList();
        }

    }

    /**
     * 添加元素
     *
     * @param emp
     * @return
     */
    public void add(Emp emp) {
        //1.通过id计算应该放到数组里哪条链表
        int empLinkedListNO = hashFun(emp.id);

        //2.添加到对应的链表
        empLinkedListArray[empLinkedListNO].add(emp);

    }

    /**
     * 遍历数组每一个数组的元素
     */
    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        //通过id定位到该id对应的元素属于哪个链表
        int empLinkedListNO = hashFun(id);
        //取出链表中对应的元素
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    /**
     * 编写散列函数，使用一个简单的取模
     *
     * @param id
     * @return
     */
    private int hashFun(int id) {
        return id % size;
    }

}

/**
 * 雇员列表，表示哈希表里每一个链表
 */
class EmplinkedList {
    /**
     * 头指针 链表的第一个元素（头指针不为空的链表）
     */
    private Emp head;

    /**
     * 向链表中添加元素 思路分析：
     * 1.判断链表头节点是否为空，为空则将添加的元素赋给头节点
     * 2.头结点不为空，则定义一个辅助指针，将头结点赋给辅助指针（开始时辅助指针为头结点）
     * 3.循环从头结点开始向后找，当头节点或某一个元素的下一个节点为空时则跳出循环
     * 4.将添加的元素赋值给当前元素的下一个节点
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历链表 思路分析：和新增思路相似
     *
     * @param no 数组下表（下标加一就是第几个链表）
     */
    public void list(int no) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }

        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();

    }

    /**
     * 通过id查询链表的元素
     */
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while (true) {
            //在该链表中找到元素
            if (curEmp.id == id) {
                break;
            }
            //没找到元素
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;

    }
}

/**
 * 雇员 哈希表里的数组的每个链表对应的每一个元素
 */
class Emp {

    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

