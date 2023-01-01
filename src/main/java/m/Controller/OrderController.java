package m.Controller;

import m.Model.DTO.OrderDTO;
import m.Model.Entity.OrderDetails;
import m.Model.Entity.Orders;
import m.Model.Entity.ProductDetails;
import m.Model.Entity.UserMana;
import m.Model.ServiceImp.OrderDetailsServiceImp;
import m.Model.ServiceImp.OrderServiceImp;
import m.Model.ServiceImp.UserServiceImp;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("m/auth/OrderController")
public class OrderController {
    @Autowired
    private OrderServiceImp orderServiceImp;
    @Autowired
    private OrderDetailsServiceImp orderDetailsServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/getAll")
    public List<OrderDTO> getAll(){
        List<Orders> ordersList=orderServiceImp.getAll();
        List<OrderDTO> orderDTOList=new ArrayList<>();
        List<String> statusOrder=new ArrayList<>();
        for (Orders or:ordersList) {
            List<OrderDetails> orderDetailsList=orderDetailsServiceImp.getAllOderDetailByOrder(or.getOrdersId());
            float price=0;
            int quantity=0;
            int paid=0;

            String status="";
            String statusDisplay="";
            for (OrderDetails ord:orderDetailsList) {
                price += ord.getProductDetails().getPriceDt();
                quantity=ord.getQuantity();
                if (ord.getOrderStatus()==3){
                    status="Đang giao hàng";
                    statusOrder.add(status);
                }
                if (ord.getOrderStatus()==4){
                    paid+=(ord.getProductDetails().getPriceDt()*ord.getQuantity());

                }
            }
            if (statusOrder.size()==orderDetailsList.size()){
                statusDisplay="Đang giao hàng";
            }else {
                statusDisplay="Đang chuẩn bị hàng";
            }

            price*=quantity;
            OrderDTO orderDTO=new OrderDTO(
                    or.getUserMana().getUserName(),
                    (price+ or.getTotalAmount()-paid),
                    statusDisplay

            );
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@RequestBody Orders orders) {
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserMana users = userServiceImp.findByUserName(usersChangePass.getUsername());
        List<Orders> orders1 = orderServiceImp.getAll();
        String mess = "";
        if (orders1.size()!=0){
            for (Orders getOrder : orders1) {
                if (getOrder.getUserMana().getUserId()==users.getUserId()){
                    Orders ordersOld = orderServiceImp.getOrdersByUserMana_UserId(users.getUserId());
                   if (ordersOld.getCreateDate()==getOrder.getCreateDate()){
                       ordersOld.setOrdersId(ordersOld.getOrdersId());
                       ordersOld.setUserMana(users);
                       ordersOld.setTotalAmount(14);
                       ordersOld.setUserID(users.getUserId());
                       ordersOld.setCreateDate(ordersOld.getCreateDate());
                       orderServiceImp.saveAndUpdate(ordersOld);
                       mess = "Mua đi ngại gì ^-^";
                   }else {
                       orders.setUserMana(users);
                       orders.setTotalAmount(14);
                       orders.setUserID(users.getUserId());
                       orders.setCreateDate(LocalDate.now());
                       orderServiceImp.saveAndUpdate(orders);
                       mess = "Lâu lắm rồi mới thấy bạn "+users.getUserName()+" quay lại!";
                   }
                }else {
                    orders.setUserMana(users);
                    orders.setTotalAmount(14);
                    orders.setUserID(users.getUserId());
                    orders.setCreateDate(LocalDate.now());
                    orderServiceImp.saveAndUpdate(orders);
                    mess = "Chúc "+users.getUserName()+" có những trải nghiệm vui vẻ ở Cửa Hàng!";
                }
            }
        }else {
            orders.setUserMana(users);
            orders.setTotalAmount(14);
            orders.setCreateDate(LocalDate.now());
            orders.setUserID(users.getUserId());
            orderServiceImp.saveAndUpdate(orders);
            mess = "Cảm ơn bạn "+users.getUserName()+" đã tin tưởng ở cửa hàng chúng tôi!";
        }

         return ResponseEntity.ok(mess);
    }

//    @DeleteMapping("/delete")
//    private Orders orders(){
//        List<OrderDetails> getAllOrderDetailsByOrder=orderDetailsServiceImp.getAllOderDetailByOrder()
//        for (Orders orders:orderServiceImp.getAll()) {
//
//        }
//    }

}
