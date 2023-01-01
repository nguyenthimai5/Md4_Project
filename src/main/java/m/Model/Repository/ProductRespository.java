package m.Model.Repository;

import m.Model.Entity.Catalog;
import m.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameContaining(String name);

    List<Product> findByCatalog_CatalogId(int catalogId);

    @Query(value = "from Catalog c where c.catalogId=:productId")
    Catalog findCatalogByIdProduct(@Param("productId") int productId);

    @Query(value = "from Product p  where p.productStatus=true")
    List<Product> displayCatalogTrue();
}
