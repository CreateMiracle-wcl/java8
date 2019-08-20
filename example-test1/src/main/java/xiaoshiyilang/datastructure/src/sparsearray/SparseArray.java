package xiaoshiyilang.datastructure.src.sparsearray;

/**
 * @program: DateStructure
 * @description: 稀疏数组
 * @author: zhang.cheng
 * @create: 2019-08-20 15:56
 **/

public class SparseArray {

    public static void main(String[] args) {
        // 一、创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[5][7] = 2;

        for (int[] row : chessArray) {
            for (int val : row) {
                System.out.printf("%d\t", val);
            }
            System.out.println();
        }

        //二、二维数组转稀疏数组
        /**
         * 稀疏数组：
         * 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
         * 稀疏数组列固定为三列，行不定，由二维数组非0个数决定（行数= 非0个数 + 1）
         *
         * 第一行：二维数组行数 二维数组列数 非0个数
         * 第二行：非零个数第一个行数 非零个数第一个列数 非零个数值
         * 第三行：非零个数第二个行数 非零个数第二个列数 非零个数值
         * 。。。。。。
         *
         * 二维数组转稀疏数组步骤：
         * 1.先遍历二位数组，得到非零数据的个数（第一行第三个值）
         * 2.创建稀疏数组
         * 3.遍历二位数组，将非0数据放入sparseArr中
         */

        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        //给第一行赋值
        sparseArr[0][0] = chessArray.length;
        sparseArr[0][1] = chessArray.length;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0数据放入sparseArr中
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray[i][j];
                }
            }
        }

        System.out.println();
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();


        //三、稀疏数组转二维数组
        /**
         * 步骤：
         * 1.由稀疏数组第一行创建二位数组
         * 2.由其余行遍历取得数据，并赋值给二维数组
         */

        //1.由稀疏数组第一行创建二位数组
        int[][] newChessArray = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.由其余行遍历取得数据，并赋值给二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            newChessArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] rows : newChessArray) {
            for (int val : rows) {
                System.out.printf("%d\t", val);
            }
            System.out.println();
        }

    }
}
