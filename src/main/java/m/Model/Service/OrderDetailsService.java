package m.Model.Service;

import m.Model.Entity.OrderDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailsService<T,V> {
    List<T> getAll();
    T saveAndUpdate(T t);
    void delete(V id);
    T getById(V id);
    List<OrderDetails> getAllOrderDetailswaitForConfirmation();
    List<OrderDetails> getAllOrderDetailSpreparingGoods();
    List<OrderDetails> getAllOrderDetailsDelivering();
    List<OrderDetails> getAllOrderDetailsFinish();
    List<OrderDetails> getAllOrderDetailScancelOrder();

    List<OrderDetails> getAllOderDetailByOrder(int ordersId);
}
