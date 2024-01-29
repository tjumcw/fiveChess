package com.yyds.fivechess.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author miaochangwei1
 * @Package : com.yyds.fivechess
 * @Description : TODO
 * @Create on : 2024/1/3 11:40
 **/
public class MainFrame extends JFrame {

    private ChessBoardPanel chessBoardPanel;

    private PlayerPanel playerPanel;

    public MainFrame() {
        chessBoardPanel = new ChessBoardPanel();
        playerPanel = new PlayerPanel();

        setLayout(new GridBagLayout());


        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        add(chessBoardPanel);
        add(Box.createHorizontalGlue()); // 让组件自动填充剩余空间
        add(playerPanel);

        // 设置窗口属性
        setTitle("五子棋");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
