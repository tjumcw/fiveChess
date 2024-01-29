package com.yyds.fivechess.algorithm;

/**
 * @author miaochangwei1
 * @Package : com.yyds.fivechess.algorithm
 * @Description : TODO
 * @Create on : 2024/1/3 15:36
 **/
public class MyAlgorithm {
    private static final int BOARD_SIZE = 15;
    private static final int EMPTY = 0;

    /**
     * 计算得分
     * @param my 我的得分
     * @param his 对手的得分
     * @return 得分
     */
    public static int score(int my, int his) {
        if (my > 5) return 200000;
        if (my == 5 && his == 0) return 200000;
        if (my == 5 && his == 1) return 200000;
        if (my == 5 && his == 2) return 200000;
        if (my == 4 && his == 1) return 3000;
        if (my == 4 && his == 0) return 50000;
        if (my == 4 && his == 2) return 1000;
        if (my == 3 && his == 0) return 3000;
        if (my == 3 && his == 1) return 1000;
        if (my == 3 && his == 2) return 500;
        if (my == 2 && his == 0) return 500;
        if (my == 2 && his == 1) return 200;
        if (my == 2 && his == 2) return 100;
        if (my == 1 && his == 0) return 100;
        if (my == 1 && his == 1) return 50;
        if (my == 1 && his == 2) return 30;
        return 0;
    }

    /**
     * 计算给定位置(x, y)处的得分
     * @param x 横坐标
     * @param y 纵坐标
     * @param chess 棋子类型
     * @param board 棋盘二维数组
     * @return 得分
     */
    public static int getXScore(int x, int y, int chess, int[][] board) {
        int my = 1;
        int his = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (chess == board[i][y]) {
                my++;
            } else if (board[i][y] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        for (int i = x + 1; i < board.length; i++) {
            if (chess == board[i][y]) {
                my++;
            } else if (board[i][y] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        return score(my, his);
    }

    /**
     * 获取指定位置上方和下方的得分
     * @param x x坐标
     * @param y y坐标
     * @param chess 棋子类型
     * @param board 棋盘数组
     * @return 得分
     */
    private static int getYScore(int x, int y, int chess, int[][] board) {
        int my = 1;
        int his = 0;
        for (int i = y - 1; i >= 0; i--) {
            if (chess == board[x][i]) {
                my++;
            } else if (board[x][i] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        for (int i = y + 1; i < board.length; i++) {
            if (chess == board[x][i]) {
                my++;
            } else if (board[x][i] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        return score(my, his);
    }

    /**
     * 计算斜线得分2
     * @param x 横坐标
     * @param y 纵坐标
     * @param chess 棋子
     * @param board 棋盘数组
     * @return 得分
     */
    private static int getSkewScore2(int x, int y, int chess, int[][] board) {
        int my = 1;
        int his = 0;
        for (int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--) {
            if (chess == board[i][j]) {
                my++;
            } else if (board[i][j] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++) {
            if (chess == board[i][j]) {
                my++;
            } else if (board[i][j] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        return score(my, his);
    }

    /**
     * 计算斜线得分的方法
     * @param x 横坐标
     * @param y 纵坐标
     * @param chess 棋子类型
     * @param board 棋盘数组
     * @return 得分
     */
    private static int getSkewScore1(int x, int y, int chess, int[][] board) {
        int my = 1;
        int his = 0;
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess == board[i][j]) {
                my++;
            } else if (board[i][j] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        for (int i = x + 1, j = y + 1; j < board.length && i < board.length; i++, j++) {
            if (chess == board[i][j]) {
                my++;
            } else if (board[i][j] == 0) {
                break;
            } else {
                his++;
                break;
            }
        }
        return score(my, his);
    }


    /**
     * 获取给定坐标点在棋盘上的得分。
     * @param x 横坐标
     * @param y 纵坐标
     * @param board 棋盘
     * @return 给定坐标点的得分
     * @throws IllegalArgumentException 如果坐标点超出了棋盘范围
     */
    public static int getScore(int x, int y, int[][] board) {
        int numX1 = getXScore(x, y, 1, board);
        int numX2 = getXScore(x, y, 2, board);
        int numY1 = getYScore(x, y, 1, board);
        int numY2 = getYScore(x, y, 2, board);
        int skew1 = getSkewScore1(x, y, 1, board);
        int skew2 = getSkewScore1(x, y, 2, board);
        int skew3 = getSkewScore2(x, y, 1, board);
        int skew4 = getSkewScore2(x, y, 2, board);
        if (numX2 >= 200000 || numY2 >= 200000 || skew2 >= 200000 || skew4 >= 200000) {
            return Integer.MAX_VALUE;
        }
        if (numX1 >= 200000 || numY1 >= 200000 || skew1 >= 200000 || skew3 >= 200000) {
            return Integer.MAX_VALUE;
        }
        int xScore = getXScore(x, y, 1, board) + getXScore(x, y, 2, board);
        int yScore = getYScore(x, y, 1, board) + getYScore(x, y, 2, board);
        int skewScore1 = getSkewScore1(x, y, 1, board) + getSkewScore1(x, y, 2, board);
        int skewScore2 = getSkewScore2(x, y, 1, board) + getSkewScore2(x, y, 2, board);
        return xScore + yScore + skewScore1 + skewScore2;
    }

    public static int[] evalPoint(int[][] board) {
        int[] res = new int[2];
        int max = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != EMPTY) {
                    continue;
                }
                int num = getScore(i, j, board);
                if (num == 200000) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
                if (num > max) {
                    max = num;
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
}
