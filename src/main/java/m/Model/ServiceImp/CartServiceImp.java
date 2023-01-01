//package m.Model.ServiceImp;
//
//import m.Model.Entity.Cart;
//import m.Model.Repository.CartResponsitory;
//import m.Model.Service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class CartServiceImp implements CartService<Cart,Integer> {
//@Autowired
//private CartResponsitory cartResponsitory;
//    @Override
//    public List<Cart> getAll() {
//        return cartResponsitory.findAll();
//    }
//
//    @Override
//    public Cart saveAndUpdate(Cart cart) {
//        return cartResponsitory.save(cart);
//    }
//
//    @Override
//    public void delete(Integer id) {
//     cartResponsitory.deleteById(id);
//    }
//
//    @Override
//    public Cart getById(Integer id) {
//        return cartResponsitory.findById(id).get();
//    }
//
//    @Override
//    public List<Cart> getAllCartByUserId(int userId) {
//        return cartResponsitory.getAllCartByUserId(userId);
//    }
//}
