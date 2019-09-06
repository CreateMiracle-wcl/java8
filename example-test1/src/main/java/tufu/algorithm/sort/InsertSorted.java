package tufu.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hw on 2019/9/6.
 * 插入排序
 */
public class InsertSorted {
    /**
     * 将整个区域分为已排序区域 0...i 和未排序区域 i....n-1
     * 步骤
     * 1、确定好已排序区域最后元素的初始索引
     * 2、比较操作（将未排序区域第一个元素插入到已排序区域的适当位置）更新已排序区域最后元素的索引
     * 3、循环遍历
     * */
    public static void main(String[] args)  {
        int max = 10;
        int[] arr = new int[max];
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            arr[i] = random.nextInt();
        }
        recursiveTraverse(arr, 0);
    }

    public static void recursiveTraverse(int[] arr, int sortedIdx) {
        if (sortedIdx == arr.length-1) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        // 将未排序区域的第一个元素放入到已排序区域中，进行一次冒泡，
        int waitedInsertedIdx = sortedIdx+1;
        for (int i = sortedIdx; i >=0; i--) {
            if (arr[i]>arr[i+1]) {
                swap(arr, i, i+1);
            } else {
                // 未排序区域的第一个元素冒泡到适当位置，直接跳出循环
                break;
            }
        }
        recursiveTraverse(arr, waitedInsertedIdx);
    }


    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
