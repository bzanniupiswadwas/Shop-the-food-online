package com.study.shop.util;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = JdbcUtil.getConn();
        if (conn != null) {
            System.out.println("✅ 数据库连接成功！");
            System.out.println(conn);
        } else {
            System.out.println("❌ 数据库连接失败！");
        }
    }
}
