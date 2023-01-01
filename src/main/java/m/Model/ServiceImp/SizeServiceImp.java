package m.Model.ServiceImp;

import m.Model.Entity.Size;
import m.Model.Repository.SizeRespository;
import m.Model.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImp implements SizeService<Size, Integer> {
    @Autowired
    private SizeRespository sizeRespository;

    @Override
    public List<Size> getAll() {
        return sizeRespository.findAll();
    }

    @Override
    public Size saveAndUpdate(Size size) {
        return sizeRespository.save(size);
    }

    @Override
    public void delete(Integer id) {
        sizeRespository.deleteById(id);
    }

    @Override
    public Size getById(Integer id) {
        return sizeRespository.findById(id).get();
    }

    @Override
    public List<Size> findBySizeNameContaining(String name) {
        return sizeRespository.findBySizeNameContaining(name);
    }

    @Override
    public List<Size> sortSizeBySizeName(String direction) {
        if (direction.equals("asc")){
            return sizeRespository.findAll(Sort.by("sizeName").ascending());
        }else {
            return sizeRespository.findAll(Sort.by("sizeName").descending());
        }
    }

    @Override
    public Page<Size> getPagging(Pageable pageable) {
        return sizeRespository.findAll(pageable);
    }

    @Override
    public List<Size> displaySizeTrue() {
        return sizeRespository.displaySizeTrue();
    }
}
