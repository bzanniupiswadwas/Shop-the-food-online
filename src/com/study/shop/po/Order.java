package com.study.shop.po;

import java.util.Date;

public class Order {
    private Integer orderId;
    private Integer deskNumber;
    private String phone;
    private Integer foodId;
    private Integer price;
    private Integer amount;
    private Integer total;
    private Date createTime;
    private String state;

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getDeskNumber() { return deskNumber; }
    public void setDeskNumber(Integer deskNumber) { this.deskNumber = deskNumber; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Integer getFoodId() { return foodId; }
    public void setFoodId(Integer foodId) { this.foodId = foodId; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return String.format("订单ID:%d 桌号:%d 手机:%s 餐品ID:%d 单价:%d 数量:%d 总价:%d 时间:%s 状态:%s",
                orderId, deskNumber, phone, foodId, price, amount, total,
                (createTime==null? "":createTime.toString()), state);
    }
}