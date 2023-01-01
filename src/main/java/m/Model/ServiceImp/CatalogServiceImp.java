package m.Model.ServiceImp;

import m.Model.Entity.Catalog;

import m.Model.Repository.CatalogRespository;
import m.Model.Service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImp implements CatalogService<Catalog, Integer> {
    @Autowired
    private CatalogRespository catalogRespository;


    @Override
    public List<Catalog> getAll() {
        return catalogRespository.findAll();
    }

    @Override
    public Catalog saveAndUpdate(Catalog catalog) {
        return catalogRespository.save(catalog);
    }

    @Override
    public void delete(Integer id) {
        catalogRespository.deleteById(id);
    }

    @Override
    public Catalog getById(Integer id) {
        return catalogRespository.findById(id).get();
    }

    @Override
    public List<Catalog> findByCatalogNameContaining(String name) {
        return catalogRespository.findByCatalogNameContaining(name);
    }

    @Override
    public Catalog findByCatalogId(int id) {
        return catalogRespository.findByCatalogId(id);
    }

    @Override
    public List<Catalog> sortCatalogByCatalogName(String direction) {
      if (direction.equals("asc")){
          return catalogRespository.findAll(Sort.by("catalogName").ascending());
      }else {
          return catalogRespository.findAll(Sort.by("catalogName").descending());
      }

    }

    @Override
    public Page<Catalog> getPagging(Pageable pageable) {
        return catalogRespository.findAll(pageable);
    }

    @Override
    public List<Catalog> findAllCatalog() {
        return catalogRespository.findAllCatalog();
    }


}
