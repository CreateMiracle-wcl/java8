package tufu.datastructure.stack;

import tufu.datastructure.queue.CircleQueue;
import tufu.datastructure.queue.Queue;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by hw on 2019/9/1.
 * 逆波兰计算器
 */
public class PolandNotation {
    public static void main(String[] args) {
        String testStr = "1+((2+3)*4)..;;-5";
        Integer length = testStr.length();
        ArrayList<String> arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            String w = testStr.charAt(i)+"";
            arrayList.add(w);
        }
        PolandNotation polandNotation = new PolandNotation(arrayList);
        polandNotation.execute();
        System.out.println("运算结果为:" + polandNotation.caculate());
    }
    private Stack<String> operatorStack;// 操作符
    private Queue<String> templateQueue;// 中间状态
    private ArrayList<String> rawArray;

    public PolandNotation(ArrayList rawArray) {
        this.rawArray = rawArray;
        this.operatorStack = new Stack<String>();
        this.templateQueue = new CircleQueue<String>(rawArray.size());
    }
    // 将中缀表达式处理为后缀表达式
    public void execute() {
        Integer size = rawArray.size();
        for (int i = 0; i < size; i++) {
            word(i);
        }
        Integer operatorSize =  operatorStack.size();
        for (int i = 0; i < operatorSize; i++) {
            templateQueue.enqueue(operatorStack.pop());
        }
        templateQueue.print();
    }
    // 优先写出中缀表达式每个字符对应的处理逻辑，idx 从 0 开始
    // 数组元素分为三种情况：
    // 1、括号
    //     (  直接压入 operatorStack 中
    //      ) 将 operatorStack 栈顶元素依次添加到 templateQueue 中，直到遇到 )
    // 2、运算符 operator，与 operatorStack 栈顶运算符进行优先级比较
    //      如果大于栈顶运算符，直接将 operator 压入 operatorStack 中
    //      如果不大于栈顶运算符，将栈顶运算符弹出并添加到 templateQueue 中，并继续遍历 operatorStack 栈顶运算符，
    //          如果依然不大于栈顶元素，则继续将栈顶运算符弹出并添加到 templateQueue 中，直到大于 operatorStack 栈顶元素，
    //          或者 operatorStack 为空，才将 operator 压入到 templateQueue 中。
    //    注意下：为何要保证 operator > templateQueue 栈顶元素时，才将 operator 压入到栈中，
    //           是为了保证弹出 templateQueue 中元素时，优先级大的运算符，被优先弹出。
    // 3、整数，直接添加到 templateQueue 中
    private void word(int idx) {
        if (idx>=rawArray.size())
            return;
        String item = rawArray.get(idx);
        //
        if (isOperator(item)) {
            if (this.operatorCompare(item)) {
                operatorStack.add(item);
            } else {
                String operator = operatorStack.pop();
                templateQueue.enqueue(operator);
                word(idx);// 这里留意下
            }
        } else if (item.equals("(")) {
              operatorStack.add(item);
        } else if (item.equals(")")) {
            Integer operatorSize =  operatorStack.size();
            for (int i = 0; i < operatorSize; i++) {
                String operator = operatorStack.pop();
                if (operator.equals("("))
                    break;
                templateQueue.enqueue(operator);
            }
        } else if (item.matches("\\d+")) {
            templateQueue.enqueue(item);
        }
    }

    private boolean isOperator(String string) {
        if (string.equals("+"))
            return true;
        if (string.equals("-"))
            return true;
        if (string.equals("*"))
            return true;
        if (string.equals("/"))
            return true;
        return false;
    }

    // 返回 true 表示 operator > pop 否则 operator <= pop
    private boolean operatorCompare(String operator) {
        if (operatorStack.size()==0)
            return true;
        String pop = operatorStack.peek();
        Operation operation = new Operation();
        int oV = operation.getValue(operator);
        int pV = operation.getValue(pop);
        return (oV-pV)>0?true:false;
    }

    public Integer caculate() {
        Stack<Integer> valueStack = new Stack();
        while (!templateQueue.isEmpty()) {
            String item = templateQueue.dequeue();
            if (isOperator(item)) {
                // 取出两个数字
                Integer v1 = (valueStack.pop());
                Integer v2 = (valueStack.pop());
                Integer result = - 1;
                char operation = item.charAt(0);
                switch (operation) {
                    case '+':
                        result = v2 + v1;
                        break;
                    case '-':
                        result = v2 - v1;
                        break;
                    case '*':
                        result = v2 * v1;
                        break;
                    case '/':
                        result = v2 / v1;
                        break;
                    default:
                        break;
                }
                if (result>=0)
                    valueStack.add(result);
            } else if (item.matches("\\d+")){
                valueStack.add(Integer.parseInt(item));
            }
        }
        return valueStack.pop();
    }

    //编写一个类 Operation 可以返回一个运算符 对应的优先级
    class Operation {
        private final int ADD = 1;
        private final int SUB = 1;
        private final int MUL = 2;
        private final int DIV = 2;
    //写一个方法，返回对应的优先级数字
        public int getValue(String operation) {
            int result = 0;
            switch (operation.charAt(0)) {
                case '+':
                    result = ADD;
                    break;
                case '-':
                    result = SUB;
                    break;
                case '*':
                    result = MUL;
                    break;
                case '/':
                    result = DIV;
                    break;
                default:
                    System.out.println("不存在该运算符");
                    break;
            }
            return result;
        }
    }
}

