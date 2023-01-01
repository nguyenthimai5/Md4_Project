package m.Model.Repository;

import m.Model.Entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRespository extends JpaRepository<Catalog, Integer> {
    List<Catalog> findByCatalogNameContaining(String name);

    Catalog findByCatalogId(int id);

    @Query(value = "from Catalog c  where c.catalogStatus=true")
    List<Catalog> findAllCatalog();
}
