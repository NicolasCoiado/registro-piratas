package br.com.nvnk.RegistroPiratas.Alcunhas;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alcunhas")
public class AlcunhaController {
    @Autowired
    AlcunhaService service;

    @PostMapping
    public ResponseEntity<?> cadastrarAlcunha (@RequestBody AlcunhaDTO alcunha){
        try {
            AlcunhaDTO alcunhaSalva = service.cadastrarAlcunha(alcunha);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Alcunha cadastrada: "+alcunha.getTitulo());
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }
}
