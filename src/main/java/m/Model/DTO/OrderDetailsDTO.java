package m.Model.DTO;

import m.Model.Entity.Orders;
import m.Model.Entity.ProductDetails;
import m.Model.Entity.UserMana;

import javax.persistence.*;
import java.time.LocalDate;

public class OrderDetailsDTO {
    private String productDtId;
    private String userName;
    private String address;
    private String phone;
    private String email;
    private LocalDate createDateOr;
    private float totalAmount;
    private int quantity;
    private String orderStatus;

    public OrderDetailsDTO(String productDtId, String userName, String address, String phone, String email, LocalDate createDateOr, float totalAmount, int quantity, String orderStatus) {
        this.productDtId = productDtId;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.createDateOr = createDateOr;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }

    public String getProductDtId() {
        return productDtId;
    }

    public void setProductDtId(String productDtId) {
        this.productDtId = productDtId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getCreateDateOr() {
        return createDateOr;
    }

    public void setCreateDateOr(LocalDate createDateOr) {
        this.createDateOr = createDateOr;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
