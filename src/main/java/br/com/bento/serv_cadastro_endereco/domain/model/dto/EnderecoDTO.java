package br.com.bento.serv_cadastro_endereco.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDTO(
        Long pessoaId,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String localidade,
        String estado,
        String cep,
        String pais,
        String perfil,
        String tipoDeEndereco,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {}
