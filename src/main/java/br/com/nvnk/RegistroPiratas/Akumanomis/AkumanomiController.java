package br.com.nvnk.RegistroPiratas.Akumanomis;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/akumanomis")
public class AkumanomiController {
    @Autowired
    private AkumanomiService service;

    @PostMapping
    @Operation(summary = "Cadastrar Akuma no mi", description = "Cadastra uma Akuma no mi.")
    public ResponseEntity<String> criarAkumanomi(
            @RequestBody AkumanomiDTO akumanomiEnviada
    ){
        AkumanomiDTO akumanomiDTO = service.cadastrarAkumanomi(akumanomiEnviada);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Akuma no mi cadastrada: "+akumanomiDTO.getNome());
    }
    //TODO: IMPLEMENTAR ERROS PERSONALIZADOS
    // - Exemplo: Usuário envia um tipo de akumanomi desconhecido.

    @GetMapping
    public String listarAkumanomis(){
            return "Akumanomi";
    }

    @GetMapping("/{id}")
    public String descreverAkumanomi(){
        return "Akumanomi";
    }

    @PutMapping("/{id}")
    public String editarAkumanomi(){
        return "Akumanomi";
    }
    //TODO: Trocar por PATCH

    @DeleteMapping("/{id}")
    public String deletarAkumanomi(){
        return "Akumanomi";
    }
}
