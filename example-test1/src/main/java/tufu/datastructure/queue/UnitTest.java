package tufu.datastructure.queue;

import java.util.Scanner;

/**
 * Created by hw on 2019/8/23.
 */
public class UnitTest {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        CircleQueue<String> queue =  new CircleQueue<String>();
        Boolean runloop = true;
        while (runloop) {
            System.out.println("command：a 入队，d 出队，e 退出");
            String command = scanner.next();
            char w = command.charAt(0);
            try {
                switch(w){
                    case 'a' : {
                        System.out.println("请输入入队元素：");
                        command = scanner.next();
                        queue.enqueue(command);
                        queue.print();
                    }
                    break;
                    case 'd' : {
                        String  e = queue.dequeue();
                        System.out.println("出队元素为："+ e);
                        queue.print();
                    }
                    break;
                    case 'e' : {
                        runloop = false;
                    }
                    break;
                    default :
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
