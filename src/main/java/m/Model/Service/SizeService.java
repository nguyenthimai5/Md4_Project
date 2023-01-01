package m.Model.Service;

import m.Model.Entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SizeService<T,V>{
    List<T> getAll();
    T saveAndUpdate(T t);
    void delete(V id);
    T getById(V id);
    List<T> findBySizeNameContaining(String name);
    List<T> sortSizeBySizeName(String direction);
    Page<T> getPagging(Pageable pageable);
    List<Size> displaySizeTrue();
}
