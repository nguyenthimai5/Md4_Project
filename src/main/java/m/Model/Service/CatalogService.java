package m.Model.Service;

import m.Model.Entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService<T,V>{
    List<T> getAll();
    T saveAndUpdate(T t);
    void delete(V id);
    T getById(V id);
    List<T> findByCatalogNameContaining(String name);
    T findByCatalogId(int id);
    List<T> sortCatalogByCatalogName(String direction);
    Page<T> getPagging(Pageable pageable);
    List<T> findAllCatalog();

}
