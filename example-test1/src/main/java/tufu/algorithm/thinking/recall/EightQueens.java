package tufu.algorithm.thinking.recall;

/**
 * Created by hw on 2019/9/1.
 * 以下运用 递归编码方式 + 回溯思想（穷举所有情况） 对八皇后问题进行解决
 */
public class EightQueens {

    public static void main(String[] args)  {
        EightQueens queens = new EightQueens(8);
        queens.check(1);
    }
    private int size;
    private int loopCount;
    private int resolveCount;
    private int[] seats;//存储八个皇后的列位置，其索引对应行位置
    /* 注意：
    *     这里循环和递归都是串行执行的，因此：可以仅用一个数组 seats 分别打印出每种情况
    * */
    // private Boolean[][] record; 无法用备忘录去除重复的，因为判断是否重复的条件不能确定（根据前面所有阶段的状态来的）
    public EightQueens(int size) {
        this.size = size;
        this.seats = new int[size];
    }

    // 首先明确：
    // 1、退出递归条件：遍历到头
    // 2、可回溯的条件：同列、对角线上
    /*
    * 运用 递归与回溯 来安排指定皇后的位置放置
    * n 对应某个皇后
    * */
    public void check(int n) {
        if (n>size) {
            resolveCount++;
            print();
            return;
        }
        // 对每一列进行处理
        for (int i = 0; i < size; i++) {
            //
            seats[n-1] = i;
            loopCount++;
            //
            if (judge(n-1) == true) {
                check(n+1);
            }
        }
    }
    /*
    * judge 对第 n 行的列位置，进行合法性校验，即是否可回溯
    * n 对应在 seats 上是第 n 个元素
    * */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // seats[n-1] == seats[i] 同列
            // (n-i == Math.abs(seats[n-1]-seats[i])) 对象线(当前状态与之前状态间的关系)
            if (seats[n] == seats[i] || (n-i == Math.abs(seats[n]- seats[i]))) {
                return false;
            }
            //
            //不满足回溯条件，
        }
        return true;
    }

    public void print() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            buffer.append(seats[i] + " ");
        }
        System.out.println(buffer);
        System.out.println("共循环了"+loopCount+"次");
        System.out.println("共有"+resolveCount+"种方法");
    }
}
