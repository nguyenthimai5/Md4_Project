package m.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductDtId")
    private int productDtId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId",referencedColumnName = "productId")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId",referencedColumnName = "sizeId")
    private Size size;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toppingId",referencedColumnName = "toppingId")
    private Topping topping;
    @OneToMany(mappedBy = "productDetails",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetails> orderDetails=new ArrayList<>();
    @Column(name = "PriceDetails")
    private float priceDt;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Descriptions")
    private String descriptions;
    @Column(name = "ProductDetailStatus")
    private boolean productDetailStatus;
}
