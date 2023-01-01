//package m.Model.Entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "cart")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "CartId")
//    private int cartId;
//    @Column(name = "user",unique = true)
//    private int userId;
////    @OneToOne(mappedBy = "cart")
////    private UserMana userMana;
//    //    @OneToMany(mappedBy = "cart")
////    private List<ProductDetails> productDetails=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "Cart_PrDt", joinColumns = @JoinColumn(name = "cartId"),
//            inverseJoinColumns = @JoinColumn(name = "productDtId"))
//    private List<ProductDetails> productDetailsList = new ArrayList<>();
//
//}
