package m.Model.Repository;

import m.Model.Entity.Size;
import m.Model.Entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRespository extends JpaRepository<Topping,Integer> {
    List<Topping> findByToppingNameContaining(String name);

    @Query(value = "from Topping p  where p.toppingStatus=true")
    List<Topping> displayToppingTrue();
}
