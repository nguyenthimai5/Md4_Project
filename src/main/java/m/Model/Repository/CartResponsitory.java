//package m.Model.Repository;
//
//import m.Model.Entity.Cart;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface CartResponsitory extends JpaRepository<Cart,Integer> {
//    @Query(value = "from Cart c where c.userId=:userId")
//    List<Cart> getAllCartByUserId(int userId);
//}
