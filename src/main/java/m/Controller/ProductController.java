package m.Controller;

import lombok.extern.slf4j.Slf4j;
import m.Model.Entity.Catalog;
import m.Model.Entity.Product;
import m.Model.ServiceImp.CatalogServiceImp;
import m.Model.ServiceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("m/auth/ProductController")
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private CatalogServiceImp catalogServiceImp;


    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Product> getAllProducts() {
        return productServiceImp.getAll();
    }

    @GetMapping()
    public List<Product> displayProductTrue() {
        return productServiceImp.displayCatalogTrue();
    }

    @GetMapping("/{productId}")
    public Product getByIdProduct(@PathVariable("productId") int productId) {
        return productServiceImp.getById(productId);

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody Product product, @RequestParam("catalogId") int catalogId) {
        Catalog catalog = catalogServiceImp.getById(catalogId);
        if (catalog.isCatalogStatus()) {
            product.setCatalog(catalog);
            return ResponseEntity.ok(productServiceImp.saveAndUpdate(product));
        } else {
            return  ResponseEntity.ok("Danh mục được chọn không tồn tại!");

        }
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Product updateProduct(@PathVariable("productId") int productId, @RequestBody Product product) {
        Product productUpdate = productServiceImp.getById(productId);
        productUpdate.setImageProduct(product.getImageProduct());
        productUpdate.setProductName(product.getProductName());
        productUpdate.setPriceProduct(product.getPriceProduct());
        if (product.getCatalog() == null) {
            productUpdate.setCatalog(catalogServiceImp.getById(productUpdate.getCatalog().getCatalogId()));
        } else {
            if (productUpdate.getCatalog().isCatalogStatus()) {
                productUpdate.setCatalog(catalogServiceImp.getById(product.getCatalog().getCatalogId()));
            }
        }
        productUpdate.setProductStatus(product.isProductStatus());
        return productServiceImp.saveAndUpdate(productUpdate);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") int productId) {
        Product product = productServiceImp.getById(productId);
        if (product.getProductDetailsList().size() == 0) {
            product.setImageProduct(product.getImageProduct());
            product.setProductName(product.getProductName());
            product.setPriceProduct(product.getPriceProduct());
            product.setCatalog(product.getCatalog());
            product.setProductStatus(false);
            return ResponseEntity.ok(productServiceImp.saveAndUpdate(product)) ;
        } else {
            return ResponseEntity.ok("Không thể xoá sản phẩm này!");
        }
    }


    @GetMapping("/searchByName")
    public List<Product> searchByName(@RequestParam("name") String name) {
        return productServiceImp.findByProductNameContaining(name);
    }

    @GetMapping("/searchByCatalog")
    public List<Product> searchByCatalog(@RequestParam("catalogId") int catalogId) {
        return productServiceImp.findByCatalog_CatalogId(catalogId);
    }

}
