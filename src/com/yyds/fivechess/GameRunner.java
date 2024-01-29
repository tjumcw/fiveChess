package com.yyds.fivechess;

import com.yyds.fivechess.frame.MainFrame;

import javax.swing.*;

/**
 * @author miaochangwei1
 * @Package : com.yyds.fivechess
 * @Description : TODO
 * @Create on : 2024/1/3 16:07
 **/
public class GameRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
