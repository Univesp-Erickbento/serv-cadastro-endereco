package br.com.bento.serv_cadastro_endereco.domain.model.dto;

import br.com.bento.serv_cadastro_endereco.domain.model.enuns.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonAlias;

public record EnderecoDTO(
        Long pessoaId,
        @JsonAlias("logradouro") String rua,
        String numero,
        String complemento,
        String bairro,
        @JsonAlias("localidade")    String cidade,
        @JsonAlias("uf") String estado,
        String cep,
        String pais,
        String tipoDePessoa,
        TipoEndereco tipoDeEndereco) {
}
