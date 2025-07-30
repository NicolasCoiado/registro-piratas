package br.com.nvnk.RegistroPiratas.Tripulacoes;

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
@RequestMapping("/tripulacoes")
public class TripulacaoController {
    @Autowired
    TripulacaoService tripulacaoService;

    public TripulacaoController(TripulacaoService tripulacaoService) {
        this.tripulacaoService = tripulacaoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar tripulação",description = "Cadastra uma nova tripulação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tripulação salva.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                {
                                    "Tripulação salva": "Id: 1, Título: Chapéus de Palha"
                                }
                                """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Capitão não encontrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Capitão não encontrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Membro não cadastrado.",
                    content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(value = """
                            {
                                "Membro não cadastrado": "Id: 22"
                            }
                            """
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
            )
    })
    public ResponseEntity<?> cadastrarTripulacao (
            @Parameter(description = "Dados da tripulação.")
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
    @Operation(summary = "Listar tripulações",description = "Lista todas as tripulações cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tripulações listadas.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Tripulação",
                                    externalValue = "/openapi/examples/tripulacao-response.json"
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
            )
    })
    public ResponseEntity<?> listarTripulacoes (){
        try {
            return ResponseEntity.ok(tripulacaoService.listarTripulacoes());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @GetMapping ("/{id}")
    @Operation(summary = "Descrever tripulação",description = "Descreve uma tripulação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tripulação salva.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Tripulação",
                                    externalValue = "/openapi/examples/tripulacao-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Membro não cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "Membro não encontrado": "Id: 22"
                            }
                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Capitão não encontrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Capitão não encontrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
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
            )
    })
    public ResponseEntity<?> descreverTripulacao(
            @Parameter(description = "Id da tripulação.")
            @PathVariable Long id
    ){
        try {
            return ResponseEntity.ok(tripulacaoService.descreverTripulacao(id));
        }catch (EntityNotFoundException erro){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.getMessage());
        }catch (Exception erro){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado.");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tripulação",description = "Atualizar uma tripulação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tripulação atualizada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Tripulação",
                                    externalValue = "/openapi/examples/tripulacao-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Capitão não encontrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Capitão não encontrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Membro não cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "Membro não encontrado": "Id: 22"
                            }
                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
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
            )
    })
    public ResponseEntity<?> atualizarTripulacao(
            @Parameter(description = "Id da tripulação.")
            @PathVariable Long id,
            @Parameter(description = "Atualizações da tripulação.")
            @RequestBody TripulacaoDTO novaTripulacao){
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
    @Operation(summary = "Editar tripulação",description = "Editar uma tripulação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tripulação editada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Tripulação",
                                    externalValue = "/openapi/examples/tripulacao-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Capitão não encontrado.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Capitão não encontrado."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Membro não cadastrado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                            {
                                "Membro não encontrado": "Id: 22"
                            }
                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
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
            )
    })
    public ResponseEntity<?> editarTripulacao(
            @Parameter(description = "Id da tripulação.")
            @PathVariable Long id,
            @Parameter(description = "Edições da tripulação.")
            @RequestBody TripulacaoDTO edicoes){
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
    @Operation(summary = "Editar tripulação",description = "Editar uma tripulação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tripulação editada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AkumanomiDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo dos dados retornados.",
                                    summary = "Tripulação",
                                    externalValue = "/openapi/examples/tripulacao-response.json"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tripulação não cadastrada.",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Tripulação não cadastrada."
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
            )
    })
    public ResponseEntity<?> deletarTripulacao(
            @Parameter(description = "Id da tripulação.")
            @PathVariable Long id
    ){
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
