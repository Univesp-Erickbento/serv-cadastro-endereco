package br.com.bento.serv_cadastro_endereco.domain.model.dto;

public record EnderecoDTO(String cep, String logradouro,
                          String complemento, String bairro,
                          String localidade, String uf) {
}
