package m.Model.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
    void init();
    Path upLoadFile(MultipartFile multipartFile);
    Resource load(String fileName);
}
