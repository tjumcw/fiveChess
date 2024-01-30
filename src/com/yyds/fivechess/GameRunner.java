package com.yyds.fivechess;

import com.yyds.fivechess.frame.MainFrame;

import javax.swing.*;

public class GameRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
