package com.study.shop.view;

import com.study.shop.dao.FoodDao;
import com.study.shop.dao.OrderDao;
import com.study.shop.po.Food;
import com.study.shop.po.Order;

import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    public static Scanner sc = new Scanner(System.in);
    public static FoodDao foodDao = new FoodDao();
    public static OrderDao orderDao = new OrderDao();

    public static void main(String[] args) {
        customerMenu();
    }

    public static void customerMenu(){
        while (true) {
            System.out.println("-----------顾客界面----------");
            System.out.println("\t1. 点餐");
            System.out.println("\t2. 查询自己的点餐记录（按手机号）");
            System.out.println("\t0. 退出系统");
            System.out.print("请选择业务：");
            String option = sc.next().trim();
            switch (option){
                case "1":
                    orderFood();
                    break;
                case "2":
                    findRecord();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("您的选择有误，请重新选择！");
            }
        }
    }

    private static void orderFood() {
        System.out.println("---------------  点餐  ------------");
        List<Food> list = foodDao.findAll();
        for (Food f : list) System.out.println(f);
        System.out.println("1. 点餐  2. 退出");
        System.out.print("请选择：");
        int option1 = sc.nextInt();
        if(option1 == 2) return;
        System.out.print("请输入餐品编号：");
        int foodId = sc.nextInt();
        System.out.print("请输入桌号：");
        int desk = sc.nextInt();
        System.out.print("请输入手机号：");
        String phone = sc.next();
        System.out.print("请输入数量：");
        int amount = sc.nextInt();

        Food chosen = null;
        for (Food f : list) if (f.getFoodId() == foodId) chosen = f;
        if (chosen == null) {
            System.out.println("没有该餐品");
            return;
        }
        int total = chosen.getPrice() * amount;
        Order order = new Order();
        order.setDeskNumber(desk);
        order.setPhone(phone);
        order.setFoodId(foodId);
        order.setPrice(chosen.getPrice());
        order.setAmount(amount);
        order.setTotal(total);
        int res = orderDao.addOrder(order);
        if (res > 0) System.out.println("下单成功，合计：" + total);
        else System.out.println("下单失败");
    }

    private static void findRecord(){
        System.out.print("请输入手机号查询：");
        String phone = sc.next();
        List<Order> list = orderDao.findByPhone(phone);
        if (list.isEmpty()) System.out.println("无历史订单");
        else for (Order o : list) System.out.println(o);
    }
}

