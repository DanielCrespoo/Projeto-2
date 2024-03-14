package br.com.ifb.redesocial.controladoras;

import br.com.ifb.redesocial.controladoras.auth.AuthenticationResponse;
import br.com.ifb.redesocial.dtos.CriarPerfilDTO;
import br.com.ifb.redesocial.servicos.PerfilServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PerfilControladora {

    private final PerfilServico perfilServico;

    public PerfilControladora(PerfilServico perfilServico) {
        this.perfilServico = perfilServico;
    }

    @PostMapping(value = "/perfis")
    public ResponseEntity<String> criar(@RequestBody CriarPerfilDTO criarPerfilDTO) {
        perfilServico.criar(criarPerfilDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Perfil criado com sucesso");
    }

    @GetMapping(value = "/perfis")
    public ResponseEntity<List<Map<String, String>>> buscarPorId() {
        return ResponseEntity.ok().body(perfilServico.buscarTodos());
    }

    @GetMapping(value = "/perfis/{id}")
    public ResponseEntity<Map<String, String>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(perfilServico.buscarPorId(id));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok().body(perfilServico.authenticate(request));
    }
}
