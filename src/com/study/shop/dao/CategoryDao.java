package com.study.shop.dao;

import com.study.shop.po.Category;
import com.study.shop.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public int add(Category category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into t_category(category_name) values(?)";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getCategoryName());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }

    public List<Category> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();
        String sql = "select * from t_category where is_deleted = 0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setIsDeleted(rs.getInt("is_deleted"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    public Category findById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from t_category where category_id = ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setIsDeleted(rs.getInt("is_deleted"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return null;
    }

    public int delete(int categoryId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update t_category set is_deleted = 1 where category_id = ?";
        int result = 0;
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, null);
        }
        return result;
    }
}
