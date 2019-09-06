package tufu.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hw on 2019/9/6.
 * 快排：原地不稳定的排序算法
 */
public class QuickSorted {
    /**
     * 1、找到临界点元素
     * 2、遍历同层元素（需要临时空间），根据临界点将同层区域划分为两个区域（大于临界点和小于临界点）
     * 3、从上往下进行拆分
     * */
    public static void main(String[] args)  {
        int max = 80000;
        int[] arr = new int[max];
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            arr[i] = random.nextInt();
        }
        long start,end;
        start = System.currentTimeMillis();
        split(arr, 0, max-1);
        end = System.currentTimeMillis();
        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
    }

    public static void split(int[] arr, int lIdx, int rIdx) {
        if (lIdx>=rIdx)
            return;
        int point = partition(arr, lIdx, rIdx);
        split(arr, lIdx, point-1);
        split(arr, point+1, rIdx);
    }

    private static int partition(int[] arr, int lIdx, int rIdx) {
        int piovt = arr[rIdx];
        int i = lIdx;// i 指向的是 大于等于 piovt 的区域 的第一个元素
        for (int j = lIdx; j < rIdx; j++) {
            if (arr[j]<piovt) {// 注意，这里是 小于 piovt，才进行交换
                swap(arr, i, j);
                i+=1;
            }
        }
        if (i != rIdx) {
            swap(arr, i, rIdx);
        }
        return i;
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
