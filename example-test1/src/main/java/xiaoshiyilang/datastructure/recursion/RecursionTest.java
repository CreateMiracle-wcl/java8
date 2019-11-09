package xiaoshiyilang.datastructure.recursion;

/**
 * @program: example-test
 * @description:
 * @author: zhang.cheng
 * @create: 2019-09-05 14:57
 **/

public class RecursionTest {

    public static void main(String[] args) {

//        test(4);

        int factorial = factorial(5);
        System.out.println(factorial);

    }


    //打印问题.
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            // 1 * 2 * 3
            return factorial(n - 1) * n;
        }
    }
}
