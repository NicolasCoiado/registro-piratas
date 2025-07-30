package br.com.nvnk.RegistroPiratas.Piratas;

import br.com.nvnk.RegistroPiratas.Akumanomis.AkumanomiDTO;
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

@RestController
@RequestMapping("/piratas")
public class PirataController {
    @Autowired
    PirataService pirataService;

    @PostMapping
    @Operation(summary = "Cadastrar pirata", description = "Cadastra um novo pirata.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pirata cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Pirata",
                                    externalValue = "/openapi/examples/pirata-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Akuma no mi não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Akuma no mi não cadastrada."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Akuma no mi já possui um usuário.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Akuma no mi já possui um usuário."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Esta tripulação já possui um capitão.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Esta tripulação já possui um capitão."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro inesperado."
                            )
                    )
            ),
    })
    public ResponseEntity<?> cadastrarPirata (
            @Parameter(description = "Dados do pirata.")
            @RequestBody PirataDTO pirata){
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
    @Operation(summary = "Listar piratas", description = "Lista todos os piratas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Piratas listados.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Pirata",
                                    externalValue = "/openapi/examples/pirata-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro inesperado."
                            )
                    )
            ),
    })
    public ResponseEntity<?> listarPirata (){
        try {
            return ResponseEntity.ok(pirataService.listarPiratas());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Descrever pirata", description = "Descreve todas as propriedades de um pirata.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pirata cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Pirata",
                                    externalValue = "/openapi/examples/pirata-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Pirata não cadastrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Pirata não cadastrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro inesperado."
                            )
                    )
            ),
    })
    public ResponseEntity<?> descreverPirata (
            @Parameter(description = "Id do pirata.")
            @PathVariable Long id
    ){
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
    @Operation(summary = "Atualizar pirata", description = "Atualiza um pirata.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pirata cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Pirata",
                                    externalValue = "/openapi/examples/pirata-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Akuma no mi não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Akuma no mi não cadastrada."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Akuma no mi já possui um usuário.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Akuma no mi já possui um usuário."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Esta tripulação já possui um capitão.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Esta tripulação já possui um capitão."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pirata não cadastrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Pirata não cadastrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro inesperado."
                            )
                    )
            ),
    })
    public ResponseEntity<?> atualizarPirata(
            @Parameter(description = "Id do pirata.")
            @PathVariable Long id,
            @Parameter(description = "Novos dados do pirata.")
            @RequestBody PirataDTO pirataComAtt
    ) {
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
    @Operation(summary = "Editar pirata", description = "Edita um pirata.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pirata cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Pirata",
                                    externalValue = "/openapi/examples/pirata-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Akuma no mi não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Akuma no mi não cadastrada."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Akuma no mi já possui um usuário.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Akuma no mi já possui um usuário."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Esta tripulação já possui um capitão.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Esta tripulação já possui um capitão."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pirata não cadastrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Pirata não cadastrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro inesperado."
                            )
                    )
            ),
    })
    public ResponseEntity<?> editarPirata(
            @Parameter(description = "Id do pirata.")
            @PathVariable Long id,
            @Parameter(description = "Novos dados do pirata.")
            @RequestBody PirataDTO edicoes
    ) {
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
    @Operation(summary = "Deletar pirata", description = "Deleta um pirata.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pirata deletado com sucesso."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pirata não encontrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Pirata não encontrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro inesperado."
                            )
                    )
            ),
    })
    public ResponseEntity<?> deletarPirata(
            @Parameter(description = "Id do pirata.")
            @PathVariable Long id
    ) {
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
