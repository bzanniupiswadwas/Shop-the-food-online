package com.study.shop.dao;

import com.study.shop.po.Order;
import com.study.shop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public int addOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_order(desk_number,phone,food_id,price,amount,total,state) values(?,?,?,?,?,?,1)";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getDeskNumber());
            pstmt.setString(2, order.getPhone());
            pstmt.setInt(3, order.getFoodId());
            pstmt.setInt(4, order.getPrice());
            pstmt.setInt(5, order.getAmount());
            pstmt.setInt(6, order.getTotal());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    public List<Order> findByPhone(String phone) {
        List<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from t_order where phone = ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("order_id"));
                o.setDeskNumber(rs.getInt("desk_number"));
                o.setPhone(rs.getString("phone"));
                o.setFoodId(rs.getInt("food_id"));
                o.setPrice(rs.getInt("price"));
                o.setAmount(rs.getInt("amount"));
                o.setTotal(rs.getInt("total"));
                o.setState(rs.getString("state"));
                o.setCreateTime(rs.getTimestamp("create_time"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }
}
