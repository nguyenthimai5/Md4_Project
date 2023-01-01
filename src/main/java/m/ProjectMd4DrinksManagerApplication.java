package m;

import m.Model.ServiceImp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectMd4DrinksManagerApplication implements CommandLineRunner {
    @Autowired
    private FileStorageServiceImp fileStorageService;


    @Override
    public void run(String... args) throws Exception {
        fileStorageService.init();
    }
    public static void main(String[] args) {
        SpringApplication.run(ProjectMd4DrinksManagerApplication.class, args);
    }

}
