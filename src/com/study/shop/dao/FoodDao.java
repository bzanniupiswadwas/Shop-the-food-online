package com.study.shop.dao;

import com.study.shop.po.Food;
import com.study.shop.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {

    public int add(Food food) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_food(food_name,category_id,price,description) values(?,?,?,?)";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setInt(3, food.getPrice());
            pstmt.setString(4, food.getDescription());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    public List<Food> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "select t1.*, t2.category_name from t_food t1, t_category t2 where t1.category_id = t2.category_id and t1.is_deleted = 0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food f = new Food();
                f.setFoodId(rs.getInt("food_id"));
                f.setFoodName(rs.getString("food_name"));
                f.setCategoryId(rs.getInt("category_id"));
                f.setPrice(rs.getInt("price"));
                f.setDescription(rs.getString("description"));
                f.setIsDeleted(rs.getInt("is_deleted"));
                f.setCategoryName(rs.getString("category_name"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    public List<Food> findByKey(String key) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "select t1.*, t2.category_name from t_food t1, t_category t2 where t1.category_id = t2.category_id and t1.is_deleted = 0 and t1.food_name like ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + key + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food f = new Food();
                f.setFoodId(rs.getInt("food_id"));
                f.setFoodName(rs.getString("food_name"));
                f.setCategoryId(rs.getInt("category_id"));
                f.setPrice(rs.getInt("price"));
                f.setDescription(rs.getString("description"));
                f.setIsDeleted(rs.getInt("is_deleted"));
                f.setCategoryName(rs.getString("category_name"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    public int delete(int foodId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_food set is_deleted = 1 where food_id = ?";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }
}
