package br.com.bento.serv_cadastro_endereco.service.impl;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import br.com.bento.serv_cadastro_endereco.repository.GerarCepRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CadastrarEnderecoServiceImpl {

    @Autowired
    private GerarCepRepository gerarCepRepository;

    public List<Endereco> listarTodos() {
        return gerarCepRepository.findAll();
    }

    public Endereco salvar(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setPessoaId(dto.pessoaId());
        novoEndereco.setLogradouro(dto.logradouro());
        novoEndereco.setNumero(dto.numero());
        novoEndereco.setComplemento(dto.complemento());
        novoEndereco.setBairro(dto.bairro());
        novoEndereco.setLocalidade(dto.localidade());
        novoEndereco.setEstado(dto.estado());
        novoEndereco.setCep(dto.cep());
        novoEndereco.setPais(dto.pais() != null ? dto.pais() : "Brasil");
        novoEndereco.setPerfil(dto.perfil());
        novoEndereco.setTipoDeEndereco(dto.tipoDeEndereco());

        return gerarCepRepository.save(novoEndereco);
    }

    public EnderecoDTO buscarPorId(Long id) {
        Optional<Endereco> optionalEndereco = gerarCepRepository.findById(id);
        if (optionalEndereco.isEmpty()) {
            return null;
        }

        Endereco e = optionalEndereco.get();
        return new EnderecoDTO(
                e.getPessoaId(),
                e.getLogradouro(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getLocalidade(),
                e.getEstado(),
                e.getCep(),
                e.getPais(),
                e.getPerfil(),
                e.getTipoDeEndereco(),
                e.getDataCriacao(),
                e.getDataAtualizacao()
        );
    }

    public Endereco atualizar(Long id, EnderecoDTO dto) {
        Optional<Endereco> optionalEndereco = gerarCepRepository.findById(id);
        if (optionalEndereco.isEmpty()) {
            return null;
        }

        Endereco endereco = optionalEndereco.get();
        endereco.setPessoaId(dto.pessoaId());
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setLocalidade(dto.localidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        endereco.setPais(dto.pais() != null ? dto.pais() : "Brasil");
        endereco.setPerfil(dto.perfil());
        endereco.setTipoDeEndereco(dto.tipoDeEndereco());

        return gerarCepRepository.save(endereco);
    }

    public boolean deletar(Long id) {
        if (!gerarCepRepository.existsById(id)) {
            return false;
        }
        gerarCepRepository.deleteById(id);
        return true;
    }

    public EnderecoDTO buscarCep(String cep) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();

                ObjectMapper mapper = new ObjectMapper();
                EnderecoDTO endereco = mapper.readValue(json, EnderecoDTO.class);

                return endereco;
            } else {
                throw new RuntimeException("Não consegui obter o endereço a partir desse CEP: " + cep);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o CEP: " + cep, e);
        }
    }

    /**
     * Retorna todos os endereços de uma pessoa agrupados por tipo de endereço.
     */
    public Map<String, List<Endereco>> buscarPorPessoaIdAgrupadoPorTipo(Long pessoaId) {
        List<Endereco> enderecos = gerarCepRepository.findByPessoaId(pessoaId);
        return enderecos.stream()
                .collect(Collectors.groupingBy(end -> {
                    String tipo = end.getTipoDeEndereco();
                    return (tipo == null || tipo.isBlank()) ? "Indefinido" : tipo;
                }));
    }
}
