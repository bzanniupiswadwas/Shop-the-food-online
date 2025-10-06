package com.study.shop.ui;

import com.study.shop.dao.FoodDao;
import com.study.shop.po.Food;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FoodFrame extends JFrame {
    private FoodDao dao = new FoodDao();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> list;

    public FoodFrame() {
        setTitle("餐品管理");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        list = new JList<>(listModel);
        refreshList();

        JButton addBtn = new JButton("新增");
        JButton delBtn = new JButton("删除");

        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(delBtn);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addFood());
        delBtn.addActionListener(e -> deleteFood());

        setVisible(true);
    }

    private void refreshList() {
        listModel.clear();
        List<Food> foods = dao.findAll();
        for (Food f : foods) {
            listModel.addElement("ID:" + f.getFoodId() + " 名称:" + f.getFoodName() + " 价格:" + f.getPrice());
        }
    }

    private void addFood() {
        String name = JOptionPane.showInputDialog(this, "输入餐品名称：");
        if (name == null || name.trim().isEmpty()) return;
        String priceStr = JOptionPane.showInputDialog(this, "输入价格（整数）：");
        int price = Integer.parseInt(priceStr);
        Food f = new Food();
        f.setFoodName(name.trim());
        f.setPrice(price);
        f.setCategoryId(1); // 暂时写死类目ID，可改进
        f.setDescription("无");
        dao.add(f);
        refreshList();
    }

    private void deleteFood() {
        int index = list.getSelectedIndex();
        if (index >= 0) {
            String selected = listModel.getElementAt(index);
            int id = Integer.parseInt(selected.split(" ")[0].replace("ID:", ""));
            dao.delete(id);
            refreshList();
        } else {
            JOptionPane.showMessageDialog(this, "请选择要删除的项！");
        }
    }
}

