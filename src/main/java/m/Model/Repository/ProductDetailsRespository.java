package m.Model.Repository;

import m.Model.Entity.Product;
import m.Model.Entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRespository extends JpaRepository<ProductDetails,Integer> {
    List<ProductDetails> findByProductProductId(int productId);
    List<ProductDetails> findByPriceDtContaining(float priceDetails);
    List<ProductDetails> findBySizeSizeName(String sizeName);
    List<ProductDetails> findByToppingToppingName(String toppingName);
    @Query(value = "from ProductDetails p  where p.productDetailStatus=true")
    List<ProductDetails> displayProductDetailsTrue();

}
