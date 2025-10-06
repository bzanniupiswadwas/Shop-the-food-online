package com.study.shop.view;

import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("========= 点餐系统 =========");
            System.out.println("1. 店主端");
            System.out.println("2. 顾客端");
            System.out.println("0. 退出");
            System.out.print("请选择：");
            String opt = sc.next().trim();
            if ("1".equals(opt)) ShopownerView.main(null);
            else if ("2".equals(opt)) CustomerManager.main(null);
            else if ("0".equals(opt)) { System.out.println("退出系统"); break; }
            else System.out.println("无效选择");
        }
        sc.close();
    }
}
