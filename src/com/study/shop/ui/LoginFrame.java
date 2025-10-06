package com.study.shop.ui;

import com.study.shop.dao.ShopownerDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField phoneField;
    private JPasswordField pwdField;
    private ShopownerDao dao = new ShopownerDao();

    public LoginFrame() {
        setTitle("店主登录");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("手机号："));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("密码："));
        pwdField = new JPasswordField();
        add(pwdField);

        JButton loginBtn = new JButton("登录");
        JButton exitBtn = new JButton("退出");
        add(loginBtn);
        add(exitBtn);

        // 登录按钮事件
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText().trim();
                String pwd = new String(pwdField.getPassword());
                if (dao.login(phone, pwd)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功！");
                    dispose(); // 关闭登录窗口
                    new MainMenuFrame(); // 打开主菜单
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "手机号或密码错误！");
                }
            }
        });

        // 退出按钮事件
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}

