package tufu.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hw on 2019/9/6.
 * 冒泡排序
 */
public class BubbleSorted {
    /**
     * 将整个区域分为已排序区域（存储整个数据源中最小的数据集合）0...i 和未排序区域 i....n-1
     * 步骤
     * 1、确定好已排序区域最后元素的初始索引
     * 2、比较操作（循环遍历未排序区域，每次比较相邻元素将较小的元素放到前面）更新已排序区域最后元素的索引
     * 3、循环遍历
     * */
    public static void main(String[] args)  {
        int max = 10;
        int[] arr = new int[max];
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            arr[i] = random.nextInt();
        }
        //loopTraverse(arr);
        recursiveTraverse(arr, 0);
    }

    public static void recursiveTraverse(int[] arr, int sortedIdx) {
        if (sortedIdx == arr.length-1) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int j = arr.length-1; j > sortedIdx; j--) {
            if (arr[j]<arr[j-1]) {
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
            }
        }
        recursiveTraverse(arr, sortedIdx+1);
    }

    public static void loopTraverse(int[] arr) {
        int sortedIdx = 0;
        // 外层 for i 循环，仅控制循环的次数
        for (int i = 1; i < arr.length-1; i++) {
            for (int j = arr.length-1; j > sortedIdx; j--) {
                if (arr[j]<arr[j-1]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
            sortedIdx++;
        }
        System.out.println(Arrays.toString(arr));
    }
}
