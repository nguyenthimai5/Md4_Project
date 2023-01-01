package m.Model.Service;

import m.Model.Entity.Catalog;
import m.Model.Entity.Product;

import java.util.List;

public interface ProductService<T, V> {
    List<T> getAll();

    T saveAndUpdate(T t);

    void delete(V id);

    T getById(V id);

    List<T> findByProductNameContaining(String name);

    List<T> findByCatalog_CatalogId(int catalogId);

    Catalog findCatalogByIdProduct(int productId);

    List<T> displayCatalogTrue();
}
