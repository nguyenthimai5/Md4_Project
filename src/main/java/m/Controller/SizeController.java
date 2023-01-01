package m.Controller;

import lombok.extern.slf4j.Slf4j;
import m.Model.Entity.Size;
import m.Model.ServiceImp.SizeServiceImp;
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
@RequestMapping("m/auth/SizeController")

public class SizeController {
    @Autowired
    private SizeServiceImp sizeServiceImp;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Size> getAll() {
        return sizeServiceImp.getAll();
    }
    @GetMapping("/{sizeId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Size getByIdSize(@PathVariable("sizeId")int sizeId) {
        return sizeServiceImp.getById(sizeId);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Size> displaySizeTrue() {
        return sizeServiceImp.displaySizeTrue();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Size createSize(@RequestBody Size size) {
        return sizeServiceImp.saveAndUpdate(size);
    }

    @PutMapping("/{sizeId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Size updateSize(@PathVariable("sizeId") int sizeId, @RequestBody Size size) {
        Size sizeUpdate = sizeServiceImp.getById(sizeId);
        sizeUpdate.setSizeName(size.getSizeName());
        sizeUpdate.setSizeStatus(size.isSizeStatus());
        return sizeServiceImp.saveAndUpdate(sizeUpdate);
    }

    @DeleteMapping("/{sizeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSize(@PathVariable("sizeId") int sizeId) {
        Size size = sizeServiceImp.getById(sizeId);
        if (size.getProductDetailsList().size()==0) {
            size.setSizeName(size.getSizeName());
            size.setSizeStatus(false);
            return ResponseEntity.ok(sizeServiceImp.saveAndUpdate(size));
        }else {
            return ResponseEntity.ok("Không thể xoá kích cỡ này!");

        }
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Size> searchByNameSize(@RequestParam("name") String name) {
        return sizeServiceImp.findBySizeNameContaining(name);
    }
    @GetMapping("/sortByNameSize")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Size>> sortSizeBySizeName(@RequestParam("direction")String direction){
        List<Size> sizeList=sizeServiceImp.sortSizeBySizeName(direction);
        return new ResponseEntity<>(sizeList, HttpStatus.OK);
    }
    @GetMapping("/getPaggingSize")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public  ResponseEntity<Map<String,Object>> getPaggingSize(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ){
        Pageable pageable= PageRequest.of(page, size);
        Page<Size> sizePage=sizeServiceImp.getPagging(pageable);
        Map<String,Object> dataSize=new HashMap<>();
        dataSize.put("size",sizePage.getContent());
        dataSize.put("total",sizePage.getSize());
        dataSize.put("totalItems",sizePage.getTotalElements());
        dataSize.put("totalPage",sizePage.getTotalPages());
        return new ResponseEntity<>(dataSize,HttpStatus.OK);
    }
}
