package com.study.shop.ui;

import com.study.shop.dao.OrderDao;
import com.study.shop.po.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerOrderFrame extends JFrame {
    private OrderDao orderDao = new OrderDao();
    private JTextField txtPhone;
    private JTable table;
    private DefaultTableModel tableModel;

    public CustomerOrderFrame() {
        setTitle("顾客订单查询");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== 顶部输入栏 =====
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("请输入手机号："));
        txtPhone = new JTextField(15);
        JButton btnSearch = new JButton("查询");
        JButton btnClear = new JButton("清空");
        topPanel.add(txtPhone);
        topPanel.add(btnSearch);
        topPanel.add(btnClear);
        add(topPanel, BorderLayout.NORTH);

        // ===== 表格区域 =====
        String[] columnNames = {"订单ID", "桌号", "手机号", "餐品ID", "单价", "数量", "总价", "时间", "状态"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== 按钮事件 =====
        btnSearch.addActionListener(e -> searchOrders());
        btnClear.addActionListener(e -> clearTable());

        setVisible(true);
    }

    private void searchOrders() {
        String phone = txtPhone.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入手机号！");
            return;
        }

        List<Order> list = orderDao.findByPhone(phone);
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "该手机号暂无订单！");
            clearTable();
            return;
        }

        // 清空旧数据
        clearTable();

        for (Order o : list) {
            Object[] row = {
                    o.getOrderId(),
                    o.getDeskNumber(),
                    o.getPhone(),
                    o.getFoodId(),
                    o.getPrice(),
                    o.getAmount(),
                    o.getTotal(),
                    (o.getCreateTime() != null ? o.getCreateTime().toString() : ""),
                    o.getState()
            };
            tableModel.addRow(row);
        }
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        new CustomerOrderFrame();
    }
}

