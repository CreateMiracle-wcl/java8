package tufu.algorithm.thinking.recall;

/**
 * Created by hw on 2019/9/1.
 * 迷宫问题，思路：
 * 1、初始化一个二维数组，用 0 表示未走过的节点，1 表示墙/挡板，2 表示该节点是通的，3 表示该节点是不通的，4 遍历到状态待定
 * 2、如何判断当前节点是否为通的？
 *  2.1、制定针对该节点上下左右方向上进行遍历的次序
 *  2.2、根据指定的遍历次序对该节点上下左右四个方向上的节点判断是否是通的
 */
public class Maze {

    public static void main(String[] args)  {
        Maze maze = new Maze(10);
        maze.other();
        maze.print();
        maze.stepWalk(1, 1);
        System.out.println("路线图为：");
        maze.print();
    }

    private int size;
    private int[][] map;

    public Maze(int size) {
        this.size = size;
        this.map = new int[size][size];
        // 墙的初始化
        for (int i = 0; i < size; i++) {
            // 上下
            this.map[0][i] = 1;
            this.map[size-1][i] = 1;
            // 左右
            this.map[i][0] = 1;
            this.map[i][size-1] = 1;
        }
    }

    public void other() {
        // 其它挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        map[1][2] = 1;
    }

    // 按照 下 -> 右 -> 上 -> 左 策略走
    public boolean stepWalk(int iStep, int jStep) {
        if (iStep==size-2 && jStep==size-2) {
            map[iStep][jStep] = 2;
            return true;
        }
        if (map[iStep][jStep] == 0) {
            // 注意：需要将 map[iStep][jStep] = 4 写在递归前面
            //      以表示该节点遍历过，至于状态的话，还得根据递归的结果才能知道
            map[iStep][jStep] = 4;
            // 上、右、下、左，四个方向是回溯进行的，即一个方向走不通，回溯到该节点进行下一个方向判断
            // 注意一点：这里调用的递归函数是递归到底，才能有返回值的.
            //          也就意味着：递归函数的返回值，可以作为该方向的状态，而不是仅意味着下一个节点本身的状态。
            // 此外，还说明一点：递归可以看做是从后往前传递状态，第一个得到状态的是最后一个调用函数（而递归本身调用是从从前往后的）。
            if (stepWalk(iStep+1, jStep) == true ||
                            stepWalk(iStep, jStep+1) == true ||
                            stepWalk(iStep-1, jStep) == true ||
                            stepWalk(iStep, jStep-1) == true
                    ) {
                map[iStep][jStep] = 2;
                return true;
            } else {
                map[iStep][jStep] = 3;
                return false;
            }
        } else {
            // 走过的节点就不应该在走了，返回 false，表示该方向上是不通的
            return false;
        }
    }

    public void print() {
        // 输出地图 System.out.println("地图的情况");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
