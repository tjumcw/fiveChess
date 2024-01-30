package com.yyds.fivechess.frame;

import com.yyds.fivechess.algorithm.Constant;
import com.yyds.fivechess.algorithm.MyAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoardPanel extends JPanel {

    private static JButton[][] board;
    private static int[][] chessPoints;
    private static boolean isGameOver;

    public ChessBoardPanel() {

        setLayout(new GridLayout(15, 15));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 设置黑色边框，宽度为 2 像素
        setBackground(Color.WHITE); // 设置背景色为白色


        board = new JButton[15][15];
        chessPoints = new int[15][15];
        isGameOver = false;

        initChessBoard();
        setVisible(true);
    }

    /**
     * 开始游戏的方法
     * 启用游戏面板上的所有按钮
     */
    public static void startGame(boolean yourFirst) {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                board[row][col].setEnabled(true);
            }
        }
        if (!yourFirst) {
            board[7][7].setText("X");
            chessPoints[7][7] = Constant.AI_PLAYER;
        }
    }

    /**
     * 重置棋盘上的所有格子
     */
    public static void reset() {
        isGameOver = false;
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                board[row][col].setText("");
                chessPoints[row][col] = 0;
            }
        }
    }

    /**
     * 初始化棋盘
     */
    private void initChessBoard() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                final int r = row;
                final int c = col;
                board[row][col] = new JButton();
                chessPoints[row][col] = 0;
                board[row][col].setPreferredSize(new Dimension(40, 40)); // 设置按钮的大小为固定值，例如 40x40 像素
                board[row][col].setEnabled(false);
                board[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!isGameOver) {
                            if (board[r][c].getText().equals("")) {
                                board[r][c].setText("0");
                                chessPoints[r][c] = Constant.HUMAN_PLAYER;
                                if (checkWin(r, c, "0")) {
                                    isGameOver = true;
                                    JOptionPane.showMessageDialog(null, "人类赢了!");
                                    reset();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "无效移动");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "游戏结束，请重开");
                        }
                        robotGo();
                    }
                });
                add(board[row][col]);
            }
        }
    }

    public void robotGo() {
        if (!isGameOver) {
            for (int i = 0; i < chessPoints.length; i++) {
                for (int j = 0; j < chessPoints[0].length; j++) {
                    System.out.print(chessPoints[i][j] + " ");
                }
                System.out.println();
            }
            int[] point = MyAlgorithm.evalPoint(chessPoints);
            System.out.println("目前最优为 x:" + point[0] + ", y:" + point[1]);
            // 触发机器人落子
            int r = point[0];
            int c = point[1];
            if (chessPoints[r][c] == 0) {
                board[r][c].setText("X");
                chessPoints[r][c] = Constant.AI_PLAYER;
                for (int i = 0; i < chessPoints.length; i++) {
                    for (int j = 0; j < chessPoints[0].length; j++) {
                        System.out.print(chessPoints[i][j] + " ");
                    }
                    System.out.println();
                }
                if (checkWin(r, c, "X")) {
                    isGameOver = true;
                    JOptionPane.showMessageDialog(null, "机器赢了!");
                    reset();
                }
            } else {
                JOptionPane.showMessageDialog(null, "无效移动");
            }
        } else {
            JOptionPane.showMessageDialog(null, "游戏结束，请重开");
        }
    }

    /**
     * 检查是否获胜的方法
     * @param row 行索引
     * @param col 列索引
     * @param symbol 棋子符号
     * @return 如果获胜返回true，否则返回false
     */
    private boolean checkWin(int row, int col, String symbol) {

        // Check horizontally
        int count = 1;
        int i = col - 1;
        while (i >= 0 && board[row][i].getText().equals(symbol)) {
            count++;
            i--;
        }
        i = col + 1;
        while (i < 15 && board[row][i].getText().equals(symbol)) {
            count++;
            i++;
        }
        if (count >= 5) {
            return true;
        }

        // Check vertically
        count = 1;
        int j = row - 1;
        while (j >= 0 && board[j][col].getText().equals(symbol)) {
            count++;
            j--;
        }
        j = row + 1;
        while (j < 15 && board[j][col].getText().equals(symbol)) {
            count++;
            j++;
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonally - from top-left to bottom-right
        count = 1;
        i = col - 1;
        j = row - 1;
        while (i >= 0 && j >= 0 && board[j][i].getText().equals(symbol)) {
            count++;
            i--;
            j--;
        }
        i = col + 1;
        j = row + 1;
        while (i < 15 && j < 15 && board[j][i].getText().equals(symbol)) {
            count++;
            i++;
            j++;
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonally - from top-right to bottom-left
        count = 1;
        i = col + 1;
        j = row - 1;
        while (i < 15 && j >= 0 && board[j][i].getText().equals(symbol)) {
            count++;
            i++;
            j--;
        }
        i = col - 1;
        j = row + 1;
        while (i >= 0 && j < 15 && board[j][i].getText().equals(symbol)) {
            count++;
            i--;
            j++;
        }
        return count >= 5;
    }
}
