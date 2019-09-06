package tufu.algorithm.thinking.dynamicplan;

/**
 * Created by hw on 2019/9/2.
 * 01 背包：effective、function
 * 完全背包：fullBackpack
 */
public class Backpack {

    public static void main(String[] args)  {
        zeroOneTest();
        allTest();
    }

    public static void zeroOneTest() {
        Integer[] weight = {10, 60, 80, 40, 30, 60};
        Integer[] worth = {8, 10, 9, 5, 3, 10};
        Backpack backpack = new Backpack(100, 5, weight, worth);
        backpack.effective();
        backpack.function();
    }

    public static void allTest() {
        Integer[] weight = {71, 69, 1};
        Integer[] worth = {100, 1, 2};
        Backpack backpack = new Backpack(70, 3, weight, worth);
        backpack.fullBackpack();
    }
    // 将物品放入背包分阶段进行
    // 每一阶段针对一个物品进行选择，要么放入、要么不放入
    //
    private Integer MAX;// 背包的最大承重量
    private Integer size;// 待放入物品的个数
    private Integer[] weight;// 待放入物品的重量
    private Integer[] worth;// 待放入物品的价值

    public Backpack(Integer MAX, Integer size, Integer[] weight, Integer[] worth) {
        this.MAX = MAX;
        this.size = size;
        this.weight = weight;
        this.worth = worth;
    }
    // 多阶段使用同一个一维状态数组
    public void effective() {
        // states(状态表) 用于记录当前阶段所有可达状态（重量），
        // 因为可达状态总个数不超过背包最大承受量（MAX），所以 states 的容量被初始化为大于 MAX
        // 又因为存在重量为 0 的情况，即不放入任何物品，因此 states 的容量为 MAX+1
        Integer[] states = new Integer[MAX+1];
        for (int i = 0; i <= MAX ; i++) {
            states[i] = -1;
        }
        states[0] = 0;
        for (int i = 0; i < size; i++) {
            // 这里忽略 内层 for j 循环看：
            // 问题被划分为 size 个阶段，每个阶段：
            //      将第 i 物品放入背包中的状态(不放入背包中的状态不需要考虑，因为这种情况不会对状态表有任何更改)，
            //      即：更新状态表(就是 内层的 for j 循环，j 表示状态(重量))。
            /*
            *   问题：
            *   1、为何从后向前更新状态表？
            *     需注意：
            *          states[currentW] = Math.max(states[currentW], worth[i]+states[j])
            *     该状态转移表达式描述并不是最优解（最优解是从最后阶段的状态表中遍历比较取出），
            *     而是根据上一阶段的状态如何来更新状态表
            * */

            for (int j = MAX-weight[i]; j >= 0 ; j--) {
                if (states[j]>=0) {
                    /*
                     * 这里之所以要 states[j]>=0 是因为
                     * 1、只更新可达状态的表项，即：>=0 是可达状态的 flag
                     * 2、states 被初始化为 -1
                     * */
                    Integer currentW = j+weight[i];
                    // 这里只影响状态表的更新，而不对循环遍历产生影响
                    // 将当前阶段的状态表项更新为最大值
                    states[currentW] = Math.max(states[currentW], worth[i]+states[j]);
                }
            }
        }
        // 找出最大值
        Integer maxValue = 0;
        for (int i = 1; i <= MAX; i++) {
            if (states[i]>maxValue)
                maxValue = states[i];
        }
        System.out.println("最大放入物品的价值为："+maxValue);
    }

    // 利用二维状态数组，让每个阶段单独拥有一个一维状态数组
    public void function() {
        Integer[][] states = new Integer[size][MAX+1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= MAX; j++) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= MAX) {
            states[0][weight[0]] = worth[0];
        }
        // 注意：每个阶段的状态表是依据上一阶段的状态表，再根据当前阶段放不放入物品进行刷新的
        for (int i = 1; i < size; i++) {
            // 不放入，先执行不放入，根据 i-1 阶段的状态刷新第 i 阶段的状态
            for (int j = 0; j <= MAX ; j++) {
                // 是否可达
                if (states[i-1][j]>=0) {
                    states[i][j] = states[i-1][j];
                }
            }
            // 放入，根据 第 weight[i] 的值来刷新第 i 阶段的状态
            for (int j = MAX-weight[i]; j >= 0 ; j--) {
                // 是否可达
                if (states[i-1][j]>=0) {
                    Integer oValue = states[i][j+weight[i]];// 前阶段存入的值
                    // 现阶段要更新的值
                    Integer value = states[i-1][j] + worth[i];
                    if (value>oValue)
                        states[i][j+weight[i]] = value;
                }
            }
        }
        // 找出最大值
        Integer maxValue = 0;
        for (int i = 1; i <= MAX; i++) {
            if (states[size-1][i]>maxValue)
                maxValue = states[size-1][i];
        }
        System.out.println("最大放入物品的价值为："+maxValue);
    }

    //完全背包
    //完全背包和01背包十分相像， 区别就是完全背包中的每种物品有无限件。由之前的选或者不选转变成了选或者不选、选的话要选几件
    public void fullBackpack() {
        Integer[] states = new Integer[MAX+1];
        for (int i = 0; i <= MAX ; i++) {
            states[i] = -1;
        }
        states[0] = 0;
        for (int i = 0; i < size; i++) {
            // 注意：这里是与 01 背包问题刚好相反的
            // 这样每个阶段可以遍历出依据前阶段状态放入 1...n 个该阶段物品状态（这也是为何 01 背包是从后向前刷新状态的）
            for (int j = 0; j <= MAX-weight[i]; j++) {
                if (states[j]>=0) {
                    Integer currentW = j+weight[i];
                    states[currentW] = Math.max(states[currentW], worth[i]+states[j]);
                }
            }
        }
        // 找出最大值
        Integer maxValue = 0;
        for (int i = 1; i <= MAX; i++) {
            if (states[i]>maxValue)
                maxValue = states[i];
        }
        System.out.println("最大放入物品的价值为："+maxValue);
    }
}
