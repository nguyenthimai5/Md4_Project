//package m.Controller;
//
//import lombok.extern.slf4j.Slf4j;
//import m.Model.DTO.CartDTO;
//import m.Model.DTO.ProductCartDTO;
//import m.Model.Entity.Cart;
//import m.Model.Entity.ProductDetails;
//import m.Model.Entity.UserMana;
//import m.Model.ServiceImp.CartServiceImp;
//import m.Model.ServiceImp.UserServiceImp;
//import m.Security.CustomUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:8080")
//@RestController
//@RequestMapping("m/auth/CartController")
//public class CartController {
//    @Autowired
//    private CartServiceImp cartServiceImp;
//    @Autowired
//    private UserServiceImp userServiceImp;
//
//
//    @GetMapping
//    public List<CartDTO> getAllCart() {
//        List<Cart> cartList = cartServiceImp.getAll();
//        List<CartDTO> cartDTOList = new ArrayList<>();
////        for (Cart cart : cartList) {
////            List<Cart> cartListByUserId = cartServiceImp.getAllCartByUserId(cart.getUserId());
////            UserMana userMana = userServiceImp.findById(cart.getUserId());
////            List<ProductCartDTO> productCartDTOList = new ArrayList<>();
////            for (Cart ca : cartListByUserId) {
////                for (ProductDetails capr : ca.getProductDetailsList()) {
////                    ProductCartDTO productCartDTO = new ProductCartDTO(
////                            capr.getProduct().getImageProduct(),
////                            capr.getProduct().getProductName(),
////                            capr.getTopping().getToppingName(),
////                            capr.getSize().getSizeName(),
////                            capr.getPriceDt()
////                    );
////                    productCartDTOList.add(productCartDTO);
////                }
////                CartDTO cartDTO = new CartDTO(
////                        userMana.getUserName(),
////                        productCartDTOList
////                );
////                cartDTOList.add(cartDTO);
////
////            }
////
////        }
//        return cartDTOList;
//
//    }
//
//    @GetMapping("/cart")
//    public Cart createCart(@RequestBody Cart cart) {
//        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserMana users = userServiceImp.findByUserName(usersChangePass.getUsername());
//        cart.setUserId(users.getUserId());
//        return cartServiceImp.saveAndUpdate(cart);
//    }
//
//    @PostMapping("/addCart")
//    public Cart addCart(@RequestBody Cart cart){
//        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserMana users = userServiceImp.findByUserName(usersChangePass.getUsername());
//        for (Cart cartGet:cartServiceImp.getAll()) {
//            if (cartGet.getUserId()==users.getUserId()){
//            }
//        }
//        return null;
//    }
//
//}
