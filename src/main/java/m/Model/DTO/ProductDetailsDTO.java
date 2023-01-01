package m.Model.DTO;

import m.Model.Entity.OrderDetails;
import m.Model.Entity.Product;
import m.Model.Entity.Size;
import m.Model.Entity.Topping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsDTO {
    private String imageProduct;
    private String productName;
    private String sizeName;
    private String toppingName;
    private float priceDt;
    private int quantity;
    private String descriptions;

    public ProductDetailsDTO(String imageProduct, String productName, String sizeName, String toppingName, float priceDt, int quantity, String descriptions) {
        this.imageProduct = imageProduct;
        this.productName = productName;
        this.sizeName = sizeName;
        this.toppingName = toppingName;
        this.priceDt = priceDt;
        this.quantity = quantity;
        this.descriptions = descriptions;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public float getPriceDt() {
        return priceDt;
    }

    public void setPriceDt(float priceDt) {
        this.priceDt = priceDt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
