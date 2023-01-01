package m.Model.ServiceImp;

import m.Model.Entity.Orders;
import m.Model.Repository.OrderRespository;
import m.Model.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImp implements OrderService<Orders, Integer> {
    @Autowired
    private OrderRespository orderRespository;

    @Override
    public List<Orders> getAll() {
        return orderRespository.findAll();
    }

    @Override
    public Orders saveAndUpdate(Orders orders) {
        return orderRespository.save(orders);
    }

    @Override
    public void delete(Integer id) {
        orderRespository.deleteById(id);
    }

    @Override
    public Orders getById(Integer id) {
        return orderRespository.findById(id).get();
    }

    @Override
    public Orders getOrdersByUserMana_UserId(int userID) {
        return orderRespository.findOrder(userID);
    }
}
