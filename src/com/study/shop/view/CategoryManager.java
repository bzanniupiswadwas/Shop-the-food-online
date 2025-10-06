package com.study.shop.view;

import com.study.shop.dao.CategoryDao;
import com.study.shop.po.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryManager {
    private static Scanner sc = new Scanner(System.in);
    private static CategoryDao dao = new CategoryDao();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println("---- 类目管理 ----");
            System.out.println("1. 新增类目");
            System.out.println("2. 查看类目");
            System.out.println("3. 删除类目（逻辑删除）");
            System.out.println("0. 返回/退出");
            System.out.print("请选择：");
            String opt = sc.next().trim();
            if ("1".equals(opt)) addCategory();
            else if ("2".equals(opt)) listCategory();
            else if ("3".equals(opt)) deleteCategory();
            else if ("0".equals(opt)) break;
            else System.out.println("无效选择");
        }
    }

    private static void addCategory() {
        System.out.print("输入类目名称：");
        sc.nextLine(); // clear
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("名称不能为空"); return; }
        Category c = new Category(name);
        int res = dao.add(c);
        if (res > 0) System.out.println("新增成功");
        else System.out.println("新增失败");
    }

    private static void listCategory() {
        List<Category> list = dao.findAll();
        if (list.isEmpty()) {
            System.out.println("暂无类目");
        } else {
            for (Category c : list) System.out.println(c);
        }
    }

    private static void deleteCategory() {
        listCategory();
        System.out.print("请输入要删除的类目ID：");
        int id = sc.nextInt();
        int res = dao.delete(id);
        if (res > 0) System.out.println("删除成功（逻辑删除）");
        else System.out.println("删除失败或ID不存在");
    }
}

