package m.Model.ServiceImp;

import m.Model.Service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImp implements FileStorageService {
    private final Path PATH_ROOT= Paths.get("upload");
    @Override
    public void init() {
        try {
            Files.createDirectories(PATH_ROOT);
        } catch (IOException e) {
            throw new RuntimeException("Cound not initial folder");
        }
    }

    @Override
    public Path upLoadFile(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public Resource load(String fileName) {
        return null;
    }
}
