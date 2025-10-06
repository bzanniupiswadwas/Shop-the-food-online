package com.study.shop.view;

import com.study.shop.dao.ShopownerDao;

import java.util.Scanner;

public class ShopownerView {
    private static Scanner sc = new Scanner(System.in);
    private static ShopownerDao dao = new ShopownerDao();

    public static void main(String[] args) {
        while (true) {
            System.out.println("---- 店主登录 ----");
            System.out.print("手机号：");
            String phone = sc.next();
            System.out.print("密码：");
            String pwd = sc.next();
            boolean ok = dao.login(phone, pwd);
            if (ok) {
                System.out.println("登录成功");
                ownerMenu();
                break;
            } else {
                System.out.println("登录失败，手机号或密码错误，重试？ y/n");
                String c = sc.next();
                if (!"y".equalsIgnoreCase(c)) break;
            }
        }
    }

    private static void ownerMenu() {
        while (true) {
            System.out.println("---- 店主管理 ----");
            System.out.println("1. 类目管理");
            System.out.println("2. 餐品管理");
            System.out.println("0. 注销");
            System.out.print("请选择：");
            String opt = sc.next().trim();
            if ("1".equals(opt)) CategoryManager.menu();
            else if ("2".equals(opt)) FoodManager.menu();
            else if ("0".equals(opt)) break;
            else System.out.println("无效选择");
        }
    }
}

