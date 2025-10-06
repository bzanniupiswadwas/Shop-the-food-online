package com.study.shop.ui;

import com.study.shop.dao.CategoryDao;
import com.study.shop.po.Category;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryFrame extends JFrame {
    private CategoryDao dao = new CategoryDao();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> list;

    public CategoryFrame() {
        setTitle("类目管理");
        setSize(400, 400);
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

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "输入类目名称：");
            if (name != null && !name.trim().isEmpty()) {
                dao.add(new Category(name.trim()));
                refreshList();
            }
        });

        delBtn.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index >= 0) {
                String selected = listModel.getElementAt(index);
                int id = Integer.parseInt(selected.split(" ")[0].replace("ID:", ""));
                dao.delete(id);
                refreshList();
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的项！");
            }
        });

        setVisible(true);
    }

    private void refreshList() {
        listModel.clear();
        List<Category> categories = dao.findAll();
        for (Category c : categories) {
            listModel.addElement("ID:" + c.getCategoryId() + " 名称:" + c.getCategoryName());
        }
    }
}

