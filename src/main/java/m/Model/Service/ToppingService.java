package m.Model.Service;

import m.Model.Entity.Topping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ToppingService<T,V>{
    List<T> getAll();
    T saveAndUpdate(T t);
    void delete(V id);
    T getById(V id);
    List<T> sortToppingByToppingName(String direction);
    Page<T> getPagging(Pageable pageable);
    List<T> displayToppingTrue();
    List<T> findByToppingNameContaining(String name);
}
