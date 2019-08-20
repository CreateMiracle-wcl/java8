package sparsearray;

/**
 * @program: DateStructure
 * @description: 稀疏数组
 * @author: zhang.cheng
 * @create: 2019-08-20 15:56
 **/

public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        for (int[] row : chessArray) {
            for (int val : row) {
                System.out.printf("%d\t", val);
            }
            System.out.println();
        }

        //二维数组转稀疏数组
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        System.out.println("chessArray.length" + chessArray.length);
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println(sum);

        //2.创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        //给第一行赋值
        sparseArr[0][1] = chessArray.length;
        sparseArr[0][2] = chessArray.length;
        sparseArr[0][1] = sum;



        


    }
}
