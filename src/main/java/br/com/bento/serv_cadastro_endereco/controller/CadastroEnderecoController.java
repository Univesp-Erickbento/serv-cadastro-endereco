package br.com.bento.serv_cadastro_endereco.controller;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import br.com.bento.serv_cadastro_endereco.service.impl.CadastrarEnderecoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/endereco")
public class CadastroEnderecoController {

    @Autowired
    private CadastrarEnderecoServiceImpl cadastrarEnderecoServiceImpl;

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = cadastrarEnderecoServiceImpl.salvar(enderecoDTO);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id) {
        EnderecoDTO endereco = cadastrarEnderecoServiceImpl.buscarPorId(id);
        return endereco != null ?
                new ResponseEntity<>(endereco, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = cadastrarEnderecoServiceImpl.listarTodos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        Endereco enderecoAtualizado = cadastrarEnderecoServiceImpl.atualizar(id, enderecoDTO);
        return enderecoAtualizado != null ?
                new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = cadastrarEnderecoServiceImpl.deletar(id);
        return deletado ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Map<String, List<Endereco>>> buscarPorPessoaId(@PathVariable Long pessoaId) {
        Map<String, List<Endereco>> enderecos = cadastrarEnderecoServiceImpl.buscarPorPessoaIdAgrupadoPorTipo(pessoaId);
        return enderecos.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
}
