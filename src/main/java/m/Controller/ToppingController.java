package m.Controller;

import lombok.extern.slf4j.Slf4j;
import m.Model.Entity.Size;
import m.Model.Entity.Topping;
import m.Model.ServiceImp.ToppingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("m/auth/ToppingController")
public class ToppingController {
    @Autowired
    private ToppingServiceImp toppingServiceImp;
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Topping> getAllTopping(){
        return toppingServiceImp.getAll();
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Topping> getAllToppingTrue(){
        return toppingServiceImp.displayToppingTrue();
    }
    @GetMapping("/{toppingId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Topping getById(@PathVariable("toppingId") int toppingId){
        return toppingServiceImp.getById(toppingId);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Topping createTopping(@RequestBody Topping topping){
        return toppingServiceImp.saveAndUpdate(topping);
    }
    @PutMapping("/{toppingId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Topping updateTopping(@PathVariable("toppingId") int toppingId,@RequestBody Topping topping){
     Topping toppingUpdate=toppingServiceImp.getById(toppingId);
     toppingUpdate.setToppingName(topping.getToppingName());
     toppingUpdate.setImageTopping(topping.getImageTopping());
     toppingUpdate.setToppingStatus(topping.isToppingStatus());
     return toppingServiceImp.saveAndUpdate(toppingUpdate);
    }
    @DeleteMapping("/{toppingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTopping(@PathVariable("toppingId") int toppingId){
        Topping topping=toppingServiceImp.getById(toppingId);
        if (topping.getProductDetailsList().size()==0){
            topping.setToppingName(topping.getToppingName());
            topping.setImageTopping(topping.getImageTopping());
            topping.setToppingStatus(false);
           return ResponseEntity.ok(toppingServiceImp.saveAndUpdate(topping));
        }else {
            return  ResponseEntity.ok("Không thể xoá Topping này!");
        }
    }
    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Topping> searchByName(@RequestParam("name") String name){
        return toppingServiceImp.findByToppingNameContaining(name);
    }

    @GetMapping("/sortToppingByToppingName")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Topping>> sortToppingByToppingName(@RequestParam("direction")String direction){
        List<Topping> toppingList=toppingServiceImp.sortToppingByToppingName(direction);
        return new ResponseEntity<>(toppingList, HttpStatus.OK);
    }
    @GetMapping("/getPagging")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public  ResponseEntity<Map<String,Object>> getPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ){
        Pageable pageable= PageRequest.of(page, size);
        Page<Topping> toppingPage=toppingServiceImp.getPagging(pageable);
        Map<String,Object> dataSize=new HashMap<>();
        dataSize.put("topping",toppingPage.getContent());
        dataSize.put("total",toppingPage.getSize());
        dataSize.put("totalItems",toppingPage.getTotalElements());
        dataSize.put("totalPage",toppingPage.getTotalPages());
        return new ResponseEntity<>(dataSize,HttpStatus.OK);
    }
}