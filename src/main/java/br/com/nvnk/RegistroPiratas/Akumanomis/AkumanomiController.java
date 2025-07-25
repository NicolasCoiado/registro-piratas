package br.com.nvnk.RegistroPiratas.Akumanomis;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/akumanomis")
public class AkumanomiController {
    @Autowired
    private AkumanomiService service;

    @PostMapping
    @Operation(summary = "Cadastrar Akuma no mi", description = "Cadastra uma Akuma no mi.")
    public ResponseEntity<?> criarAkumanomi(
            @RequestBody AkumanomiDTO akumanomiEnviada
    ){
        try {
            AkumanomiDTO salvo = service.cadastrarAkumanomi(akumanomiEnviada);
            return ResponseEntity.ok(salvo);
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (IllegalStateException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        } catch (Exception erro) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }


    @GetMapping
    public ResponseEntity<List<AkumanomiDTO>> listarAkumanomis(){
        return ResponseEntity.ok(service.listarAkumanomis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> descreverAkumanomi(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.descreverAkumanomi(id));
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PutMapping("/{id}")
    public AkumanomiDTO editarAkumanomi(@PathVariable Long id, @RequestBody AkumanomiDTO akumanomi){
        AkumanomiDTO akumanomiEditada = service.editarAkumanomi(id, akumanomi);

        return akumanomiEditada;
    }

    //TODO: Criar o PATCH

    @DeleteMapping("/{id}")
    public String deletarAkumanomi(){
        return "Akumanomi";
    }
}
