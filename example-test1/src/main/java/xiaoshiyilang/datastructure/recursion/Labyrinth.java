package xiaoshiyilang.datastructure.recursion;

/**
 * @program: example-test
 * @description:
 * @author: zhang.cheng
 * @create: 2019-09-05 17:45
 **/

public class Labyrinth {

    public static void main(String[] args) {
        //创建迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //第一行和最后一行为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;

        }
        //第一列和最后一列为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //第四行第二列第三列为墙
        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


}
