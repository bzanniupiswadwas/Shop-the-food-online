package com.study.shop.ui;

import com.study.shop.dao.FoodDao;
import com.study.shop.dao.OrderDao;
import com.study.shop.po.Food;
import com.study.shop.po.Order;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFrame extends JFrame {
    private FoodDao foodDao = new FoodDao();
    private OrderDao orderDao = new OrderDao();

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> foodList;
    private JTextField txtDesk;
    private JTextField txtPhone;
    private JTextField txtAmount;
    private JLabel lblTotal;

    private List<Food> allFoods = new ArrayList<>();

    public CustomerFrame() {
        setTitle("顾客点餐系统");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ========== 顶部信息栏 ==========
        JPanel infoPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("顾客信息"));
        txtDesk = new JTextField();
        txtPhone = new JTextField();
        txtAmount = new JTextField("1");
        infoPanel.add(new JLabel("桌号："));
        infoPanel.add(txtDesk);
        infoPanel.add(new JLabel("手机号："));
        infoPanel.add(txtPhone);
        infoPanel.add(new JLabel("数量："));
        infoPanel.add(txtAmount);

        // ========== 中部菜品列表 ==========
        JPanel foodPanel = new JPanel(new BorderLayout());
        foodPanel.setBorder(BorderFactory.createTitledBorder("菜单"));
        foodList = new JList<>(listModel);
        refreshFoodList();
        foodPanel.add(new JScrollPane(foodList), BorderLayout.CENTER);

        // ========== 底部操作按钮 ==========
        JPanel bottomPanel = new JPanel();
        JButton btnOrder = new JButton("下单");
        JButton btnRefresh = new JButton("刷新菜单");
        lblTotal = new JLabel("合计：0 元");

        bottomPanel.add(btnOrder);
        bottomPanel.add(btnRefresh);
        bottomPanel.add(lblTotal);

        add(infoPanel, BorderLayout.NORTH);
        add(foodPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // ========= 按钮事件 ==========
        btnRefresh.addActionListener(e -> refreshFoodList());
        btnOrder.addActionListener(e -> makeOrder());

        setVisible(true);
    }

    // 更新菜单
    private void refreshFoodList() {
        listModel.clear();
        allFoods = foodDao.findAll();
        for (Food f : allFoods) {
            listModel.addElement("ID:" + f.getFoodId() + "  " + f.getFoodName() + "（¥" + f.getPrice() + "）");
        }
    }

    // 下单逻辑
    private void makeOrder() {
        int index = foodList.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "请先选择餐品！");
            return;
        }

        String deskStr = txtDesk.getText().trim();
        String phone = txtPhone.getText().trim();
        String amountStr = txtAmount.getText().trim();

        if (deskStr.isEmpty() || phone.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息！");
            return;
        }

        try {
            int desk = Integer.parseInt(deskStr);
            int amount = Integer.parseInt(amountStr);
            Food selectedFood = allFoods.get(index);

            int total = selectedFood.getPrice() * amount;

            Order order = new Order();
            order.setDeskNumber(desk);
            order.setPhone(phone);
            order.setFoodId(selectedFood.getFoodId());
            order.setPrice(selectedFood.getPrice());
            order.setAmount(amount);
            order.setTotal(total);

            int res = orderDao.addOrder(order);
            if (res > 0) {
                lblTotal.setText("合计：" + total + " 元");
                JOptionPane.showMessageDialog(this, "下单成功！合计：" + total + " 元");
            } else {
                JOptionPane.showMessageDialog(this, "下单失败，请重试！");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的桌号和数量！");
        }
    }

    // 程序入口（可单独运行）
    public static void main(String[] args) {
        new CustomerFrame();
    }
}

