package br.com.nvnk.RegistroPiratas.Piratas;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/piratas")
public class PirataController {
    @Autowired
    PirataService pirataService;

    @PostMapping
    public ResponseEntity<?> cadastrarPirata (@RequestBody PirataDTO pirata){
        try {
            PirataDTO pirataSalvo = pirataService.cadastrarPirata(pirata);
            return ResponseEntity.status(HttpStatus.CREATED).body(pirataSalvo);
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        }catch (Exception erro){
            System.out.println(erro);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPirata (){
        try {
            return ResponseEntity.ok(pirataService.listarPiratas());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> descreverPirata (@PathVariable Long id){
        try {
            return ResponseEntity.ok(pirataService.descreverPirata(id));
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(erro.getMessage());
        }catch (Exception erro){
            System.out.println(erro);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPirata(@PathVariable Long id, @RequestBody PirataDTO pirataComAtt) {
        try {
            PirataDTO atualizado = pirataService.atualizarPirata(id, pirataComAtt);
            return ResponseEntity.ok(atualizado);
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (IllegalStateException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        } catch (Exception erro) {
            erro.printStackTrace(); // opcional, mas útil em dev
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editarPirata(@PathVariable Long id, @RequestBody PirataDTO edicoes) {
        try {
            PirataDTO atualizado = pirataService.editarPirata(id, edicoes);
            return ResponseEntity.ok(atualizado);
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (IllegalStateException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        } catch (Exception erro) {
            erro.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPirata(@PathVariable Long id) {
        try {
            pirataService.deletarPirata(id);
            return ResponseEntity.ok("Pirata deletado com sucesso.");
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (Exception erro) {
            erro.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }
}
