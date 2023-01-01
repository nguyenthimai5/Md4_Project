package m.Controller;

import m.Model.DTO.OrderDetailsDTO;
import m.Model.Entity.OrderDetails;
import m.Model.Entity.Orders;
import m.Model.Entity.ProductDetails;
import m.Model.Entity.UserMana;
import m.Model.ServiceImp.OrderDetailsServiceImp;
import m.Model.ServiceImp.OrderServiceImp;
import m.Model.ServiceImp.ProductDetailsServiceImp;
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
@RequestMapping("/m/auth/OrderDetailsController")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsServiceImp orderDetailsServiceImp;
    @Autowired
    private ProductDetailsServiceImp productDetailsServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private OrderServiceImp orderServiceImp;


    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetails> getAllOder() {
        return orderDetailsServiceImp.getAll();
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetailsDTO> getAllOrderDetailsDTO() {
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsServiceImp.getAll();
        for (OrderDetails or : orderDetails) {
            String status = "";
            if (or.getOrderStatus() == 1) {
                status = "Chờ xác nhận";
            } else if (or.getOrderStatus() == 2) {
                status = "Đang chuẩn bị hàng";
            } else if (or.getOrderStatus() == 3) {
                status = "Đang giao hàng";
            } else if (or.getOrderStatus() == 4) {
                status = "Giao hàng thành công";
            } else {
                status = "Đơn hàng đã huỷ";
            }
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    or.getProductDetails().getProduct().getProductName(),
                    or.getOrders().getUserMana().getUserName(),
                    or.getOrders().getUserMana().getAddress(),
                    or.getOrders().getUserMana().getPhone(),
                    or.getOrders().getUserMana().getEmail(),
                    or.getCreateDateOr(),
                    (or.getProductDetails().getPriceDt() * or.getQuantity()),
                    or.getQuantity(),
                    status
            );
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        return orderDetailsDTOList;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@RequestBody OrderDetails orderDetails, @RequestParam("productDtId") int productDtId) {
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserMana users = userServiceImp.findByUserName(usersChangePass.getUsername());
        for (Orders or:orderServiceImp.getAll()) {
            if (or.getUserMana()==users){
                orderDetails.setOrders(or);
            }
        }
        ProductDetails productDetails = productDetailsServiceImp.getById(productDtId);
        orderDetails.setCreateDateOr(LocalDate.now());
        orderDetails.setOrderStatus(1);
        orderDetails.setTotalAmount((productDetails.getPriceDt())*(orderDetails.getQuantity()));
        orderDetails.setProductDetails(productDetails);
       orderDetailsServiceImp.saveAndUpdate(orderDetails);
       return ResponseEntity.ok("Bạn "+users.getUserName()+" đã đặt hàng thành công!");
    }

    @PutMapping("/{orderDetailsId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateDetails(@RequestBody OrderDetails orderDetails, @PathVariable("orderDetailsId") int orderDetailsId) {
        OrderDetails orderDetailsUp = orderDetailsServiceImp.getById(orderDetailsId);
        orderDetailsUp.setCreateDateOr(LocalDate.now());
        orderDetailsUp.setOrderStatus(orderDetails.getOrderStatus());
        orderDetailsServiceImp.saveAndUpdate(orderDetailsUp);
        return ResponseEntity.ok("Cập nhật trạng thái đơn hàng thành công!");
    }
    @GetMapping("/getAllOrderDetailswaitForConfirmation")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetailsDTO> getAllOrderDetailswaitForConfirmation(){
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsServiceImp.getAllOrderDetailswaitForConfirmation();
        for (OrderDetails or : orderDetails) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    or.getProductDetails().getProduct().getProductName(),
                    or.getOrders().getUserMana().getUserName(),
                    or.getOrders().getUserMana().getAddress(),
                    or.getOrders().getUserMana().getPhone(),
                    or.getOrders().getUserMana().getEmail(),
                    or.getCreateDateOr(),
                    (or.getProductDetails().getPriceDt() * or.getQuantity()),
                    or.getQuantity(),
                    "Chờ xác nhận"
            );
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        return orderDetailsDTOList;
    }

    @GetMapping("/getAllOrderDetailSpreparingGoods")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetailsDTO> getAllOrderDetailSpreparingGoods(){
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsServiceImp.getAllOrderDetailSpreparingGoods();
        for (OrderDetails or : orderDetails) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    or.getProductDetails().getProduct().getProductName(),
                    or.getOrders().getUserMana().getUserName(),
                    or.getOrders().getUserMana().getAddress(),
                    or.getOrders().getUserMana().getPhone(),
                    or.getOrders().getUserMana().getEmail(),
                    or.getCreateDateOr(),
                    (or.getProductDetails().getPriceDt() * or.getQuantity()),
                    or.getQuantity(),
                    "Đang chuẩn bị hàng"
            );
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        return orderDetailsDTOList;
    }

    @GetMapping("/getAllOrderDetailsDelivering")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetailsDTO> getAllOrderDetailsDelivering(){
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsServiceImp.getAllOrderDetailsDelivering();
        for (OrderDetails or : orderDetails) {

            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    or.getProductDetails().getProduct().getProductName(),
                    or.getOrders().getUserMana().getUserName(),
                    or.getOrders().getUserMana().getAddress(),
                    or.getOrders().getUserMana().getPhone(),
                    or.getOrders().getUserMana().getEmail(),
                    or.getCreateDateOr(),
                    (or.getProductDetails().getPriceDt() * or.getQuantity()),
                    or.getQuantity(),
                    "Đang giao hàng"
            );
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        return orderDetailsDTOList;
    }

    @GetMapping("/getAllOrderDetailsFinish")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetailsDTO> getAllOrderDetailsFinish(){
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsServiceImp.getAllOrderDetailsFinish();
        for (OrderDetails or : orderDetails) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    or.getProductDetails().getProduct().getProductName(),
                    or.getOrders().getUserMana().getUserName(),
                    or.getOrders().getUserMana().getAddress(),
                    or.getOrders().getUserMana().getPhone(),
                    or.getOrders().getUserMana().getEmail(),
                    or.getCreateDateOr(),
                    (or.getProductDetails().getPriceDt() * or.getQuantity()),
                    or.getQuantity(),
                    "Giao hàng thành công"
            );
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        return orderDetailsDTOList;
    }

    @GetMapping("/getAllOrderDetailScancelOrder")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderDetailsDTO> getAllOrderDetailScancelOrder(){
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsServiceImp.getAllOrderDetailScancelOrder();
        for (OrderDetails or : orderDetails) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    or.getProductDetails().getProduct().getProductName(),
                    or.getOrders().getUserMana().getUserName(),
                    or.getOrders().getUserMana().getAddress(),
                    or.getOrders().getUserMana().getPhone(),
                    or.getOrders().getUserMana().getEmail(),
                    or.getCreateDateOr(),
                    (or.getProductDetails().getPriceDt() * or.getQuantity()),
                    or.getQuantity(),
                    "Đơn hàng đã huỷ!"
            );
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        return orderDetailsDTOList;
    }

}
