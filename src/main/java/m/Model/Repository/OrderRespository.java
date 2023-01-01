package m.Model.Repository;

import m.Model.Entity.OrderDetails;
import m.Model.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRespository extends JpaRepository<Orders,Integer> {
    @Query(value = "from Orders o where o.userID=:userID")

    Orders findOrder(@Param("userID") int userID);

}
