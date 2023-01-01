package m.Model.ServiceImp;

import m.Model.Entity.Catalog;
import m.Model.Entity.Product;
import m.Model.Repository.ProductRespository;
import m.Model.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService<Product, Integer> {
    @Autowired
    private ProductRespository productRespository;

    @Override
    public List<Product> getAll() {
        return productRespository.findAll();
    }

    @Override
    public Product saveAndUpdate(Product product) {
        return productRespository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRespository.deleteById(id);
    }

    @Override
    public Product getById(Integer id) {
        return productRespository.findById(id).get();
    }

    @Override
    public List<Product> findByProductNameContaining(String name) {
        return productRespository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findByCatalog_CatalogId(int catalogId) {
        return productRespository.findByCatalog_CatalogId(catalogId);
    }

    @Override
    public Catalog findCatalogByIdProduct(int productId) {
        return productRespository.findCatalogByIdProduct(productId);
    }

    @Override
    public List<Product> displayCatalogTrue() {
        return productRespository.displayCatalogTrue();
    }
}
