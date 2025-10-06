package com.study.shop.view;

import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.FoodDao;
import com.study.shop.po.Category;
import com.study.shop.po.Food;

import java.util.List;
import java.util.Scanner;

public class FoodManager {
    private static Scanner sc = new Scanner(System.in);
    private static FoodDao foodDao = new FoodDao();
    private static CategoryDao categoryDao = new CategoryDao();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println("---- 餐品管理 ----");
            System.out.println("1. 新增餐品");
            System.out.println("2. 查看所有餐品");
            System.out.println("3. 根据关键字查询餐品");
            System.out.println("4. 删除餐品（逻辑删除）");
            System.out.println("0. 返回/退出");
            System.out.print("请选择：");
            String opt = sc.next().trim();
            if ("1".equals(opt)) addFood();
            else if ("2".equals(opt)) listFoods();
            else if ("3".equals(opt)) findByKey();
            else if ("4".equals(opt)) deleteFood();
            else if ("0".equals(opt)) break;
            else System.out.println("无效选择");
        }
    }

    private static void addFood() {
        System.out.println("先选择类目：");
        List<Category> categories = categoryDao.findAll();
        for (Category c : categories) System.out.println(c);
        System.out.print("请输入类目ID：");
        int catId = sc.nextInt();
        sc.nextLine(); // clear
        System.out.print("餐品名称：");
        String name = sc.nextLine().trim();
        System.out.print("单价（整数）：");
        int price = Integer.parseInt(sc.nextLine().trim());
        System.out.print("简介：");
        String desc = sc.nextLine().trim();

        Food f = new Food();
        f.setCategoryId(catId);
        f.setFoodName(name);
        f.setPrice(price);
        f.setDescription(desc);
        int res = foodDao.add(f);
        if (res > 0) System.out.println("新增餐品成功");
        else System.out.println("新增失败");
    }

    private static void listFoods() {
        List<Food> list = foodDao.findAll();
        if (list.isEmpty()) System.out.println("暂无餐品");
        else {
            for (Food f : list) System.out.println(f);
        }
    }

    private static void findByKey() {
        System.out.print("请输入关键字：");
        sc.nextLine();
        String key = sc.nextLine().trim();
        List<Food> list = foodDao.findByKey(key);
        if (list.isEmpty()) System.out.println("无结果");
        else for (Food f : list) System.out.println(f);
    }

    private static void deleteFood() {
        listFoods();
        System.out.print("请输入要删除的餐品ID：");
        int id = sc.nextInt();
        int res = foodDao.delete(id);
        if (res > 0) System.out.println("删除成功（逻辑删除）");
        else System.out.println("删除失败或ID不存在");
    }
}

