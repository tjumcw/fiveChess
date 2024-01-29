package com.yyds.fivechess.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author miaochangwei1
 * @Package : com.yyds.fivechess.frame
 * @Description : TODO
 * @Create on : 2024/1/3 11:41
 **/
public class PlayerPanel extends JPanel {

    private JLabel userLabel;

    private JLabel winRateLabel;

    private JButton selfStartBtn, yourStartBtn, resetBtn;

    public void addEventListener() {
        selfStartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessBoardPanel.startGame(true);
            }
        });

        yourStartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessBoardPanel.startGame(false);
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessBoardPanel.reset();
            }
        });
    }

    public PlayerPanel() {
        setLayout(new GridLayout(5, 1));

        // 初始化用户信息
        userLabel = new JLabel("棋手: ");
        add(userLabel);

        // 初始化胜率
        winRateLabel = new JLabel("胜率: ");
        add(winRateLabel);

        // 初始化按钮
        selfStartBtn = new JButton("先手开始");
        add(selfStartBtn);
        yourStartBtn = new JButton("后手开始");
        add(yourStartBtn);
        resetBtn = new JButton("重置");
        add(resetBtn);

        addEventListener();

        setVisible(true);
    }
}
