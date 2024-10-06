package br.com.bento.serv_cadastro_endereco.service.impl;

import br.com.bento.serv_cadastro_endereco.domain.model.constante.TipoDePessoa;
import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import br.com.bento.serv_cadastro_endereco.domain.model.enuns.TipoEndereco;
import br.com.bento.serv_cadastro_endereco.repository.GerarCepRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GerarCepServiceImpl {


    @Autowired
    private GerarCepRepository gerarCepRepository;

    public List<Endereco> listarTodos() {
        return gerarCepRepository.findAll();
    }

    public Endereco salvar(EnderecoDTO endereco) {
        TipoDePessoa tipoDePessoa = new TipoDePessoa();
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep(endereco.cep());
        novoEndereco.setRua(endereco.logradouro());
        novoEndereco.setBairro(endereco.bairro());
        novoEndereco.setCidade(endereco.localidade());
        novoEndereco.setEstado(endereco.uf());
        novoEndereco.setPessoaId(1);
        novoEndereco.setComplemento("");
        novoEndereco.setPais("Basil");
        novoEndereco.setNumero("35");
        novoEndereco.setTipoDePessoa("cliente");
        novoEndereco.setTipoEndereco(TipoEndereco.ENDERECO2);

        return gerarCepRepository.save(novoEndereco);
    }

    public EnderecoDTO buscarCep(String cep) {

     var endereco = buscaEndereco(cep);

        return endereco;
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        if (gerarCepRepository.existsById(id)) {
        //    endereco.setId(id);
            return gerarCepRepository.save(endereco);
        } else {
            return null;
        }
    }

    public void deletar(Long id) {
        gerarCepRepository.deleteById(id);
    }

    public EnderecoDTO buscaEndereco(String cep) {
            URI endereco = URI.create("https://viacep.com.br/ws/" + cep + "/json/");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();

            try {
                HttpResponse<String> response = HttpClient
                        .newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), EnderecoDTO.class);
            } catch (Exception e) {
                throw new RuntimeException("Não consegui obter o endereço a partir desse CEP.");
            }

        }



}
