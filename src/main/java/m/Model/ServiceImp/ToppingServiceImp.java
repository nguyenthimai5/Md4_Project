package m.Model.ServiceImp;

import m.Model.Entity.Topping;
import m.Model.Repository.ToppingRespository;
import m.Model.Service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingServiceImp implements ToppingService<Topping, Integer> {
    @Autowired
    private ToppingRespository toppingRespository;

    @Override
    public List<Topping> getAll() {
        return toppingRespository.findAll();
    }

    @Override
    public Topping saveAndUpdate(Topping topping) {
        return toppingRespository.save(topping);
    }

    @Override
    public void delete(Integer id) {
        toppingRespository.deleteById(id);
    }

    @Override
    public Topping getById(Integer id) {
        return toppingRespository.findById(id).get();
    }

    @Override
    public List<Topping> sortToppingByToppingName(String direction) {
        if (direction.equals("asc")){
            return toppingRespository.findAll(Sort.by("toppingName").ascending());
        }else {
            return toppingRespository.findAll(Sort.by("toppingName").descending());
        }
    }

    @Override
    public Page<Topping> getPagging(Pageable pageable) {
        return toppingRespository.findAll(pageable);
    }

    @Override
    public List<Topping> displayToppingTrue() {
        return toppingRespository.displayToppingTrue();
    }

    @Override
    public List<Topping> findByToppingNameContaining(String name) {
        return toppingRespository.findByToppingNameContaining(name);
    }
}
