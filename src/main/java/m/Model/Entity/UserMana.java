package m.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "userMana")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserMana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;
    @Column(name = "UserName", unique = true, nullable = false)
    private String userName;
    @Column(name = "PassWordUs", nullable = false)
    private String passWordUs;
    @Column(name = "Email", unique = true, nullable = false)
    private String email;
    @Column(name = "Phone", nullable = false)
    private String phone;
    @Column(name = "CreateDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate = LocalDate.now();
//    @OneToOne
//    @JoinColumn(name = "cartId")
//    private Cart cart;
    @Column(name = "Address", nullable = false)
    private String address;
    @Column(name = "UserStatus")
    private boolean userStatus;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Users_Roles", joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "RolesId"))
    private Set<Roles> listRoles = new HashSet<>();
    //    @OneToMany(mappedBy = "userMana")
//    private List<OrderDetails> orderDetails=new ArrayList<>();
    @OneToMany(mappedBy = "userMana")
    private List<Orders> orders = new ArrayList<>();
}
