package br.com.ifb.redesocial.servico;

import br.com.ifb.redesocial.dto.CriarPerfilDTO;
import br.com.ifb.redesocial.entidade.Perfil;
import br.com.ifb.redesocial.repositorios.PerfilRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerfilServico {

    private final PerfilRepositorio perfilRepositorio;

    public PerfilServico(PerfilRepositorio perfilRepositorio) {
        this.perfilRepositorio = perfilRepositorio;
    }

    public void criar(CriarPerfilDTO criarPerfilDTO) {

        Perfil perfil = new Perfil();

        perfil.setNome(criarPerfilDTO.getNome());
        perfil.setTelefone(criarPerfilDTO.getTelefone());
        perfil.setEmpresa(criarPerfilDTO.getEmpresa());
        perfil.setEmail(criarPerfilDTO.getEmail());
        perfil.setSenha(criarPerfilDTO.getSenha());

        perfilRepositorio.save(perfil);

    }

    public List<Map<String, String>> buscarTodos() {

        List<Perfil> perfis = perfilRepositorio.findAll();

        return perfis.stream().map(perfil ->
                Map.of("id", String.valueOf(perfil.getId()), "nome", perfil.getNome(), "email", perfil.getEmail()))
                .toList();

    }

    public Map<String, String> buscarPorId(Long id) {

        Perfil perfil = perfilRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado com o id: " + id));


        Map<String, String> perfilEncontrado = new HashMap<>();
        perfilEncontrado.put("id", String.valueOf(perfil.getId()));
        perfilEncontrado.put("nome", perfil.getNome());
        perfilEncontrado.put("email", perfil.getEmail());

        return perfilEncontrado;


    }


}
