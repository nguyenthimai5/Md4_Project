package m.Model.Service;

import m.Model.Entity.ProductDetails;

import java.util.List;

public interface ProductDetailsService<T,V>{
    List<T> getAll();
    T saveAndUpdate(T t);
    void delete(V id);
    T getById(V id);
    List<T> findByProductProductId(int productId);
    List<T> findByPriceDtContaining(float priceDetails);
    List<T> findBySizeSizeName(String sizeName);
    List<T> findByToppingToppingName(String toppingName);
    List<T> displayProductDetailsTrue();
}
