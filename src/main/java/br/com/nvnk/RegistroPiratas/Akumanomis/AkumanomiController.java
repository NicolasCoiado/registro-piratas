package br.com.nvnk.RegistroPiratas.Akumanomis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cadastrar akuma no mi", description = "Cadastra uma akuma no mi.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Akuma no mi cadastrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Akuma no Mi",
                                    externalValue = "/openapi/examples/akumanomi-response.json"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Usuário não cadastrado."),
            @ApiResponse(responseCode = "404", description = "Akuma no mi não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    public ResponseEntity<?> criarAkumanomi(
            @Parameter(description = "Dados da akuma no mi.")
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
    @Operation(summary = "Listar akuma no mis", description = "Lista todas as akuma no mis cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Akuma no mis listadas.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Akuma no Mi",
                                    externalValue = "/openapi/examples/akumanomi-response.json"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Usuário não cadastrado."),
    })
    public ResponseEntity<List<AkumanomiDTO>> listarAkumanomis(){
        return ResponseEntity.ok(service.listarAkumanomis());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Descrever uma akuma no mi", description = "Descreve todas as propriedades de uma akuma no mi.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Akuma no mi descrita.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Akuma no Mi",
                                    externalValue = "/openapi/examples/akumanomi-response.json"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Akuma no mi não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    public ResponseEntity<?> descreverAkumanomi(
            @Parameter(description = "Id da akuma no mi.")
            @PathVariable Long id
    ) {
        try {
            return ResponseEntity.ok(service.descreverAkumanomi(id));
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (Exception erro) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um akuma no mi", description = "Atualiza uma akuma no mi.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Akuma no mi atualizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Akuma no Mi",
                                    externalValue = "/openapi/examples/akumanomi-response.json"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Usuário não cadastrado."),
            @ApiResponse(responseCode = "404", description = "Akuma no mi não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    public ResponseEntity<?> atualizarAkumanomi(
            @Parameter(description = "Id da akuma no mi.")
            @PathVariable Long id,
            @Parameter(description = "Dados da akuma no mi.")
            @RequestBody AkumanomiDTO akumanomi){
        try {
            return ResponseEntity.ok(service.atualizarAkumanomi(id, akumanomi));
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        }catch (IllegalStateException erro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Editar um akuma no mi", description = "Edita uma akuma no mi.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Akuma no mi editada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Akuma no Mi",
                                    externalValue = "/openapi/examples/akumanomi-response.json"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Usuário não cadastrado."),
            @ApiResponse(responseCode = "404", description = "Akuma no mi não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    public ResponseEntity<?> editarAkumanomi(
            @Parameter(description = "Id da akuma no mi.")
            @PathVariable Long id,
            @Parameter(description = "Dados da akuma no mi.")
            @RequestBody AkumanomiDTO atualizacoes){
        try {
            return ResponseEntity.ok(service.editarAkumanomi(id, atualizacoes));
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        } catch (IllegalStateException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.getMessage());
        } catch (Exception erro) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um akuma no mi", description = "Deleta uma akuma no mi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Akuma no mi deletada!"),
            @ApiResponse(responseCode = "404", description = "Akuma no mi não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    public ResponseEntity<String> deletarAkumanomi(
            @Parameter(description = "Id da akuma no mi.")
            @PathVariable Long id
    ){
        try {
            service.deletarAkumanomi(id);
            return ResponseEntity.ok("Akuma no mi deletada!");
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }
}
