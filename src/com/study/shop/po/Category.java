package com.study.shop.po;

public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer isDeleted;

    public Category() {}

    public Category(String categoryName) { this.categoryName = categoryName; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return String.format("ID:%d  名称:%s", categoryId, categoryName);
    }
}
