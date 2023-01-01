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
@Table(name = "topping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ToppingId")
    private int toppingId;
    @Column(name = "ToppingName")
    private String toppingName;
    @Column(name = "Image")
    private String imageTopping;
    @Column(name = "Descriptions")
    private String descriptions;
    @Column(name = "PriceTopping")
    private float priceTopping;
    @OneToMany(mappedBy = "productDtId",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductDetails> productDetailsList=new ArrayList<>();
    @Column(name = "ToppingStatus")
    private boolean toppingStatus;
}
