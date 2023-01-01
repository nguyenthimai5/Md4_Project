package m.Model.ServiceImp;

import m.Model.Entity.ProductDetails;
import m.Model.Repository.ProductDetailsRespository;
import m.Model.Service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsServiceImp implements ProductDetailsService<ProductDetails, Integer> {
    @Autowired
    private ProductDetailsRespository productDetailsRespository;

    @Override
    public List<ProductDetails> getAll() {
        return productDetailsRespository.findAll();
    }

    @Override
    public ProductDetails saveAndUpdate(ProductDetails productDetails) {
        return productDetailsRespository.save(productDetails);
    }

    @Override
    public void delete(Integer id) {
        productDetailsRespository.deleteById(id);
    }

    @Override
    public ProductDetails getById(Integer id) {
        return productDetailsRespository.findById(id).get();
    }

    @Override
    public List<ProductDetails> findByProductProductId(int productId) {
        return productDetailsRespository.findByProductProductId(productId);
    }

    @Override
    public List<ProductDetails> findByPriceDtContaining(float priceDetails) {
        return productDetailsRespository.findByPriceDtContaining(priceDetails);
    }

    @Override
    public List<ProductDetails> findBySizeSizeName(String sizeName) {
        return productDetailsRespository.findBySizeSizeName(sizeName);
    }

    @Override
    public List<ProductDetails> findByToppingToppingName(String toppingName) {
        return productDetailsRespository.findByToppingToppingName(toppingName);
    }

    @Override
    public List<ProductDetails> displayProductDetailsTrue() {
        return productDetailsRespository.displayProductDetailsTrue();
    }
}
