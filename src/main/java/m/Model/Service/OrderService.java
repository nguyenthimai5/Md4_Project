package m.Model.Service;

import m.Model.Entity.OrderDetails;
import m.Model.Entity.Orders;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderService<T,V> {
    List<T> getAll();
    T saveAndUpdate(T t);
    void delete(V id);
    T getById(V id);
    Orders getOrdersByUserMana_UserId(int userID);

}
