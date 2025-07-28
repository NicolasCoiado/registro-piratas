package br.com.nvnk.RegistroPiratas.Tripulacoes;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tripulacoes")
public class TripulacaoController {
    @Autowired
    TripulacaoService tripulacaoService;

    public TripulacaoController(TripulacaoService tripulacaoService) {
        this.tripulacaoService = tripulacaoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTripulacao (
            @RequestBody TripulacaoDTO tripulacaoEnviada
    ){
        try {
            TripulacaoDTO tripulacaoSalva = tripulacaoService.cadastrarTripulacao(tripulacaoEnviada);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tripulação salva: \nID: "+tripulacaoSalva.getId()+"\nTítulo: "+tripulacaoSalva.getTitulo());
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTripulacoes (){
        try {
            return ResponseEntity.ok(tripulacaoService.listarTripulacoes());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> descreverTripulacao(@PathVariable Long id){
        try {
            return ResponseEntity.ok(tripulacaoService.descreverTripulacao(id));
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTripulacao(@PathVariable Long id, @RequestBody TripulacaoDTO novaTripulacao){
        try {
            return ResponseEntity.ok(tripulacaoService.atualizarTripulacao(id, novaTripulacao));
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(erro.getMessage());
        }catch (IllegalStateException erro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editarTripulacao(@PathVariable Long id, @RequestBody TripulacaoDTO edicoes){
        try {
            return ResponseEntity.ok(tripulacaoService.editarTripulacao(id, edicoes));
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(erro.getMessage());
        }catch (IllegalStateException erro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTripulacao(@PathVariable Long id){
        try {
            tripulacaoService.deletarTripulacao(id);
            return ResponseEntity.ok("Tripulação deletada com sucesso.");
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (Exception erro) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

}
