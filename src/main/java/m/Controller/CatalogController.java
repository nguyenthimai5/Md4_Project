package m.Controller;

import lombok.extern.slf4j.Slf4j;
import m.Model.Entity.Catalog;
import m.Model.ServiceImp.CatalogServiceImp;
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

@CrossOrigin(origins ="http://localhost:8080")
@RestController
@RequestMapping("m/auth/CatalogController")
public class CatalogController {
    @Autowired
    private CatalogServiceImp catalogServiceImp;
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Catalog> getAllCatalog(){
        return catalogServiceImp.getAll();
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Catalog> getAllCatalogByStatusTrue(){
        return catalogServiceImp.findAllCatalog();
    }

    @GetMapping("/{catalogId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public  Catalog getById(@PathVariable("catalogId") int catalogId){
        return catalogServiceImp.getById(catalogId);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Catalog createCatalog(@RequestBody Catalog catalog){
        return catalogServiceImp.saveAndUpdate(catalog);
    }
    @PutMapping("/{catalogId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Catalog updateCatalog(@PathVariable("catalogId") int catalogId,@RequestBody Catalog catalog){
        Catalog catalogUpdate=catalogServiceImp.getById(catalogId);
        catalogUpdate.setCatalogName(catalog.getCatalogName());
        catalogUpdate.setCatalogStatus(catalog.isCatalogStatus());
        return  catalogServiceImp.saveAndUpdate(catalogUpdate);
    }
    @GetMapping("/searchByName")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Catalog> catalogSearch(@RequestParam("name") String name){
        return catalogServiceImp.findByCatalogNameContaining(name);
    }

    @GetMapping("/searchById")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Catalog catalogSearchById(@RequestParam("id") int id){
        return catalogServiceImp.findByCatalogId(id);
    }

    @DeleteMapping("/{catalogId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteCatalog(@PathVariable("catalogId") int catalogId){
        Catalog catalog=catalogServiceImp.getById(catalogId);
        if (catalog.getProductList().size()==0){
            catalog.setCatalogName(catalog.getCatalogName());
            catalog.setCatalogStatus(false);
            return ResponseEntity.ok(catalogServiceImp.saveAndUpdate(catalog));
        }else {
            return ResponseEntity.ok("Không thể xoá danh mục này!");

        }
    }
    @GetMapping("/sortByName")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Catalog>> sortCatalogByName(@RequestParam("direction") String direction){
        List<Catalog> catalogList=catalogServiceImp.sortCatalogByCatalogName(direction);
        return new ResponseEntity<>(catalogList, HttpStatus.OK);
    }
    @GetMapping("/getPagging")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String,Object>> getPagging(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Catalog> catalogPage=catalogServiceImp.getPagging(pageable);
        Map<String,Object> data=new HashMap<>();
        data.put("catalog",catalogPage.getContent());
        data.put("total",catalogPage.getSize());
        data.put("totalItems",catalogPage.getTotalElements());
        data.put("totalPages",catalogPage.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
