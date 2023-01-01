package m.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "oderDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailsId")
    private int orderDetailsId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Orders orders;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productDtId",referencedColumnName = "ProductDtId")
    private ProductDetails productDetails;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "userId",referencedColumnName = "UserId")
//    private UserMana userMana;
    @Column(name = "CreateDateOr")
    private LocalDate createDateOr;
    @Column(name = "TotalAmount")
    private float totalAmount;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "OderStatus")
    private int orderStatus;
}
