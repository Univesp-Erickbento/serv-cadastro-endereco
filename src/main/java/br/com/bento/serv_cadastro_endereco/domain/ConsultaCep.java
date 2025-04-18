package br.com.bento.serv_cadastro_endereco.domain;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep {

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
