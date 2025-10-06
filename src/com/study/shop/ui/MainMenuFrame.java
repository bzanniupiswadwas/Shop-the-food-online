package com.study.shop.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("店主管理主界面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 20, 20));

        JButton btnCategory = new JButton("类目管理");
        JButton btnFood = new JButton("餐品管理");
        JButton btnExit = new JButton("退出");

        add(btnCategory);
        add(btnFood);
        add(btnExit);

        // 点击按钮后打开不同窗口
        btnCategory.addActionListener(e -> new CategoryFrame());
        btnFood.addActionListener(e -> new FoodFrame());
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}

