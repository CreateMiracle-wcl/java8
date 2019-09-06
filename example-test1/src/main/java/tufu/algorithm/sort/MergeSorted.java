package tufu.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hw on 2019/9/6.
 * 归并排序
 */
public class MergeSorted {
    /**
     * 从上往下进行拆分
     * 从下往上进行合并（需要临时空间）
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
        split(arr, 0, max-1);
        end = System.currentTimeMillis();
        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
    }
    //
    public static void split(int[] arr, int lIdx, int rIdx) {
        if (lIdx>=rIdx)
            return;
        int midIdx = lIdx + (rIdx-lIdx)/2;
        split(arr, lIdx, midIdx);
        split(arr, midIdx+1, rIdx);
        merge(arr, lIdx, midIdx, rIdx);
    }

    public static void merge(int[] arr, int lIdx, int midIdx, int rIdx) {
        if (lIdx>=rIdx)
            return;
        int size = rIdx-lIdx+1;
        int[] tempArr = new int[size];
        int tempIdx = 0;
        // 1、将两个有序区域中元素进行 merge
        int leftIdx = lIdx;
        int rightIdx = midIdx+1;
        while (leftIdx<=midIdx&&rightIdx<=rIdx) {
            int leftV = arr[leftIdx];
            int rightV = arr[rightIdx];
            if (leftV>rightV) {
                tempArr[tempIdx++] = rightV;
                rightIdx++;
            } else if (leftIdx<rightIdx){
                tempArr[tempIdx++] = leftV;
                leftIdx++;
            } else {
                tempArr[tempIdx++] = leftV;
                tempArr[tempIdx++] = rightV;
                leftIdx++;
                rightIdx++;
            }
        }
        // 2、拷贝剩余的元素（左边区域或者右边区域有遗留待处理元素，注意：两个区域不可能同时存在）
        // 假设左边区域未被遍历完
        int startIdx = leftIdx;
        int endIdx = midIdx;
        if (rightIdx<=rIdx) {
            // 实际上右边区域未被遍历完
            startIdx = rightIdx;
            endIdx = rightIdx;
        }
        for (int i = startIdx; i <= endIdx ; i++) {
            tempArr[tempIdx++] = arr[i];
        }
        // 3、将临时数组中元素考回到原数组中
        for (int i = 0; i < size; i++) {
            int idx = lIdx+i;
            arr[idx] = tempArr[i];
        }
    }
}
