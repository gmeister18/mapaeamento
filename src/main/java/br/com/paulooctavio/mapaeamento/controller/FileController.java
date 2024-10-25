package br.com.paulooctavio.mapaeamento.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;



@RestController
@RequestMapping("/files")
public class FileController {


    @Value("${file.base.path}")
    private String basePath;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path path = Paths.get(basePath + fileName);
            Resource resource = new UrlResource(path.toUri());
            
            if (resource.exists() || resource.isReadable()) {

                // get content type of file
                File file = new File(basePath + fileName);
                String contentType = Files.probeContentType(file.toPath());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);

                
            } else {
                System.out.println("File not found");
                System.out.println(basePath + fileName);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
