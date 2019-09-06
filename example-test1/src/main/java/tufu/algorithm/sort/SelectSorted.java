package tufu.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hw on 2019/9/6.
 * 选择排序
 */
public class SelectSorted {
    /**
     * 将整个区域分为已排序区域（存储整个数据源中最小的数据集合）0...i 和未排序区域 i....n-1
     * 步骤
     * 1、确定好已排序区域最后元素的初始索引
     * 2、比较操作（从未排序区域选择出最小值）更新已排序区域最后元素的索引
     * 3、循环遍历
     * */
    public static void main(String[] args)  {
        int max = 8000;
        int[] arr = new int[max];
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            arr[i] = random.nextInt();
        }
        long start,end;
        start = System.currentTimeMillis();
        recursiveTraverse(arr, 0);
        end = System.currentTimeMillis();
        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
    }

    public static void recursiveTraverse(int[] arr, int sortedIdx) {
        if (sortedIdx == arr.length - 1) {
            return;
        }
        int firstIdx =  sortedIdx+1;// 未排序区域的第一个元素索引
        int minIdx = firstIdx;// 记录未排序区域的最小元素索引
        for (int i = minIdx+1; i < arr.length; i++) {
            if (arr[i]<arr[minIdx]) {
                // 更新未排序区域的最小元素索引
                minIdx = i;
            }
        }
        if (firstIdx != minIdx) {
            swap(arr, firstIdx, minIdx);
        }
        recursiveTraverse(arr, firstIdx);
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
