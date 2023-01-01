package m.Model.ServiceImp;

import m.Model.Entity.OrderDetails;
import m.Model.Repository.OrderDetailsResponsitory;
import m.Model.Service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailsServiceImp implements OrderDetailsService<OrderDetails,Integer> {
  @Autowired
  private OrderDetailsResponsitory orderDetailsResponsitory;
    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsResponsitory.findAll();
    }

    @Override
    public OrderDetails saveAndUpdate(OrderDetails orderDetails) {
        return orderDetailsResponsitory.save(orderDetails);
    }

    @Override
    public void delete(Integer id) {
    orderDetailsResponsitory.deleteById(id);
    }

    @Override
    public OrderDetails getById(Integer id) {
        return orderDetailsResponsitory.findById(id).get();
    }

  @Override
  public List<OrderDetails> getAllOrderDetailswaitForConfirmation() {
    return orderDetailsResponsitory.getAllOrderDetailswaitForConfirmation();
  }

  @Override
  public List<OrderDetails> getAllOrderDetailSpreparingGoods() {
    return orderDetailsResponsitory.getAllOrderDetailSpreparingGoods();
  }

  @Override
  public List<OrderDetails> getAllOrderDetailsDelivering() {
    return orderDetailsResponsitory.getAllOrderDetailsDelivering();
  }

  @Override
  public List<OrderDetails> getAllOrderDetailsFinish() {
    return orderDetailsResponsitory.getAllOrderDetailsFinish();
  }

  @Override
  public List<OrderDetails> getAllOrderDetailScancelOrder() {
    return orderDetailsResponsitory.getAllOrderDetailScancelOrder();
  }

  @Override
  public List<OrderDetails> getAllOderDetailByOrder(int ordersId) {
    return orderDetailsResponsitory.getAllOderDetailByOrder(ordersId);
  }

}
