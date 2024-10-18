package br.com.paulooctavio.mapaeamento.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulooctavio.mapaeamento.model.Funcionario;
import br.com.paulooctavio.mapaeamento.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    // private FuncionarioService funcionarioService;
    private FuncionarioRepository repository;
    @Value("${file.base.path}")
    private String basePath;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/teste")
    public String teste() {
        return "Teste";
    }

    @GetMapping("/pesquisa")
    // buscar html no path static
    public ResponseEntity<Resource> getHtml() throws MalformedURLException {
        Path path = Paths.get("src/main/resources/static/index.html");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .header("Content-Type", "text/html; charset=UTF-8")
            .body(resource);
        
    }


    //get image from path
    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Integer id) throws MalformedURLException {
        Funcionario funcionario = repository.findById(id).get();
        if (funcionario == null || funcionario.getFoto() == null || funcionario.getFoto().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // verificar se a imagem existe na pasta
        File file = new File(basePath + funcionario.getFoto());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(basePath + funcionario.getFoto());

        // Return ResponseEntity with PDF content type
        Resource resource = new UrlResource(path.toUri());
        // Return ResponseEntity with image content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
        


    }

    
    
    

}
