package br.com.bento.serv_cadastro_endereco.controller;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import br.com.bento.serv_cadastro_endereco.service.impl.GerarCepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class CadastroEnderecoController {

    @Autowired
    private GerarCepServiceImpl gerarCepServiceImpl;

//    @GetMapping
//    public ResponseEntity<List<Endereco>> listarTodos() {
//        return new ResponseEntity<>(gerarCepServiceImpl.listarTodos(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{cep}")
//    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable String cep) {
//        EnderecoDTO endereco = gerarCepServiceImpl.buscarCep(cep);
//        if (endereco != null) {
//            return new ResponseEntity<>(endereco, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody EnderecoDTO endereco) {

        return new ResponseEntity<>(gerarCepServiceImpl.salvar(endereco), HttpStatus.CREATED);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
//        Endereco funcionarioAtualizado = gerarCepServiceImpl.atualizar(id, endereco);
//        if (funcionarioAtualizado != null) {
//            return new ResponseEntity<>(funcionarioAtualizado, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable Long id) {
//        gerarCepServiceImpl.deletar(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
