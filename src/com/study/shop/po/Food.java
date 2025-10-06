package com.study.shop.po;

public class Food {
    private Integer foodId;
    private String foodName;
    private Integer categoryId;
    private Integer price;
    private String description;
    private Integer isDeleted;

    // 用于界面显示
    private String categoryName;

    public Integer getFoodId() { return foodId; }
    public void setFoodId(Integer foodId) { this.foodId = foodId; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    @Override
    public String toString() {
        return String.format("ID:%d 名称:%s 类目:%s 单价:%d 简介:%s", foodId, foodName,
                (categoryName==null? String.valueOf(categoryId):categoryName),
                price, (description==null? "":description));
    }
}
