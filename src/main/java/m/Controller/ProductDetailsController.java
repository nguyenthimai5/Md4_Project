package m.Controller;

import lombok.extern.slf4j.Slf4j;
import m.Model.DTO.ProductDetailsDTO;
import m.Model.Entity.*;
import m.Model.ServiceImp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("m/auth/ProductDetailsController")
public class ProductDetailsController {
    @Autowired
    private ProductDetailsServiceImp productDetailsServiceImp;

    @Autowired
    private SizeServiceImp sizeServiceImp;
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private ToppingServiceImp toppingServiceImp;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<ProductDetails> getAllProductDetails() {
        return productDetailsServiceImp.getAll();
    }

//    @GetMapping
//    public List<ProductDetails> displayProductDetailsTrue() {
//        return productDetailsServiceImp.displayProductDetailsTrue();
//    }

    @GetMapping
    public List<ProductDetailsDTO> displayProductDetailsDTOTrue() {
        List<ProductDetailsDTO> productDetailsDTOS=new ArrayList<>();
        List<ProductDetails> productDetails=productDetailsServiceImp.displayProductDetailsTrue();
        for (ProductDetails pr:productDetails) {
            ProductDetailsDTO productDetailsDTO=new ProductDetailsDTO(
                   pr.getProduct().getImageProduct(),
                    pr.getProduct().getProductName(),
                    pr.getSize().getSizeName(),
                    pr.getTopping().getToppingName(),
                    (pr.getProduct().getPriceProduct()+pr.getTopping().getPriceTopping()+10),
                    pr.getQuantity(),
                    pr.getDescriptions()
            );
            productDetailsDTOS.add(productDetailsDTO);
        }
        return productDetailsDTOS;
    }

    @GetMapping("/{productDtId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ProductDetails getByIdProductDetails(@PathVariable("productDtId") int productDtId) {
        return productDetailsServiceImp.getById(productDtId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProductDetails(@RequestBody ProductDetails productDetails,
                                               @RequestParam("productId") int productId,
                                               @RequestParam("sizeId") int sizeId,
                                               @RequestParam("toppingId") int toppingId) {
        Product product=productServiceImp.getById(productId);
        Size size=sizeServiceImp.getById(sizeId);
        Topping topping=toppingServiceImp.getById(toppingId);
        if (product.isProductStatus()&&size.isSizeStatus()&&topping.isToppingStatus()){
            productDetails.setProduct(product);
            productDetails.setSize(size);
            productDetails.setTopping(topping);
            return ResponseEntity.ok(productDetailsServiceImp.saveAndUpdate(productDetails));
        }else {
            return ResponseEntity.ok("Một trong những sản phẩm được chọn không tồn tại!!");

        }



    }

//    @PutMapping("/{productDtId}")
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ProductDetails updateProductDetails(@PathVariable("productDtId") int productDtId, @RequestBody ProductDetails productDetails) {
//        ProductDetails productDetailsUpdate = productDetailsServiceImp.getById(productDtId);
//        productDetailsUpdate.setPriceDt(productDetails.getPriceDt());
//        productDetailsUpdate.setProductDetailStatus(productDetails.isProductDetailStatus());
//        if (productDetailsUpdate.getImageList() == null) {
//            productDetailsUpdate.setImageList(productDetailsUpdate.getImageList());
//        } else {
//            productDetailsUpdate.setImageList(productDetails.getImageList());
//        }
//        if (productDetailsUpdate.getProduct() == null) {
//            productDetailsUpdate.setProduct(productDetailsUpdate.getProduct());
//        } else {
//            productDetailsUpdate.setProduct(productDetails.getProduct());
//        }
//        if (productDetailsUpdate.getSize() == null) {
//            productDetailsUpdate.setSize(productDetailsUpdate.getSize());
//        } else {
//            productDetailsUpdate.setSize(productDetails.getSize());
//        }
//        if (productDetailsUpdate.getTopping() == null) {
//            productDetailsUpdate.setTopping(productDetailsUpdate.getTopping());
//        } else {
//            productDetailsUpdate.setTopping(productDetails.getTopping());
//        }
//        return productDetailsServiceImp.saveAndUpdate(productDetailsUpdate);
//        return null;
//    }

    @DeleteMapping("/{productDtId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProDt(@PathVariable("productDtId") int productDtId) {
        productDetailsServiceImp.delete(productDtId);
    }

    @GetMapping("/searchByProductProductId")
    public List<ProductDetails> findByProductProductId(@RequestParam("productId") int productId) {
        return productDetailsServiceImp.findByProductProductId(productId);
    }

    @GetMapping("/searchByPriceDtContaining")
    public List<ProductDetails> findByPriceDtContaining(@RequestParam("priceDetails") int priceDetails) {
        return findByPriceDtContaining(priceDetails);
    }

    @GetMapping("/searchBySizeSizeName")
    public List<ProductDetails> findBySizeSizeName(@RequestParam("sizeName") String sizeName) {
        return productDetailsServiceImp.findBySizeSizeName(sizeName);
    }

    @GetMapping("/searchByToppingToppingName")
    public List<ProductDetails> findByToppingToppingName(@RequestParam("toppingName") String toppingName) {
        return productDetailsServiceImp.findByToppingToppingName(toppingName);
    }

}
