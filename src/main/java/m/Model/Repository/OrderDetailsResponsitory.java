package m.Model.Repository;

import m.Model.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsResponsitory extends JpaRepository<OrderDetails,Integer> {
    @Query(value = "from OrderDetails o where o.orders.ordersId=:ordersId")
    List<OrderDetails> getAllOderDetailByOrder(@Param("ordersId") int ordersId);
    @Query(value = "from OrderDetails o where o.orderStatus=1 ")
    List<OrderDetails> getAllOrderDetailswaitForConfirmation();
    @Query(value = "from OrderDetails o where o.orderStatus=2 ")
    List<OrderDetails> getAllOrderDetailSpreparingGoods();

    @Query(value = "from OrderDetails o where o.orderStatus=3")
    List<OrderDetails> getAllOrderDetailsDelivering();

    @Query(value = "from OrderDetails o where o.orderStatus=4 ")
    List<OrderDetails> getAllOrderDetailsFinish();

    @Query(value = "from OrderDetails o where o.orderStatus>=5")
    List<OrderDetails> getAllOrderDetailScancelOrder();


}

