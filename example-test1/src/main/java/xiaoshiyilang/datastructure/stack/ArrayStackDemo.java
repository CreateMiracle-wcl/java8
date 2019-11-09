package xiaoshiyilang.datastructure.stack;

import java.util.Scanner;

/**
 * @program: example-test
 * @description: 数组模拟栈实现
 * @author: zhang.cheng
 * @create: 2019-08-28 21:53
 **/

public class ArrayStackDemo {

    public static void main(String[] args) {

        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        //控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    try {
                        stack.list();
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    try {
                        System.out.println("请输入一个数");
                        int value = scanner.nextInt();
                        stack.push(value);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }
}


class ArrayStack {

    private int maxSize;

    private int[] stack;

    /**
     * 栈顶 初始化为-1
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈是否满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {

        //是否满
        if (isFull()) {
            throw new RuntimeException("栈已满。。。。。。");
        }

        top++;
        stack[top] = value;
    }

    public int pop() {

        if (isEmpty()) {
            throw new RuntimeException("栈空。。。。。。");
        }

        int value = stack[top];
        top--;
        return value;
    }

    public void list() {

        if (isEmpty()) {
            throw new RuntimeException("栈空。。。。。。");
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);

        }
    }
}
