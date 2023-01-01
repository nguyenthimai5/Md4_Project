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
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private int productId;
    @Column(name = "ProductName")
    private String productName;
    @Column(name = "PriceProduct")
    private float priceProduct;
    @Column(name = "ImageProduct")
    private String imageProduct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogId",referencedColumnName = "catalogId")
    private Catalog catalog;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductDetails> productDetailsList=new ArrayList<>();
    @Column(name = "ProductStatus")
    private boolean productStatus;
}
