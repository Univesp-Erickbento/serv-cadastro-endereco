package br.com.bento.serv_cadastro_endereco.controller;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import br.com.bento.serv_cadastro_endereco.service.impl.CadastrarEnderecoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/cep")
public class GerarCepController {

    @Autowired
    private CadastrarEnderecoServiceImpl cadastrarEnderecoServiceImpl;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> buscarPorCep(@PathVariable String cep) {
        EnderecoDTO endereco = cadastrarEnderecoServiceImpl.buscarCep(cep);
        return endereco != null ?
                new ResponseEntity<>(endereco, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
