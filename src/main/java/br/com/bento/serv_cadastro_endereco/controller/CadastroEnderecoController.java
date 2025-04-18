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
@RequestMapping("/api/endereco")
public class CadastroEnderecoController {

    @Autowired
    private CadastrarEnderecoServiceImpl cadastrarEnderecoServiceImpl;

    // Cadastrar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = cadastrarEnderecoServiceImpl.salvar(enderecoDTO);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    // Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id) {
        EnderecoDTO endereco = cadastrarEnderecoServiceImpl.buscarPorId(id);
        return endereco != null ?
                new ResponseEntity<>(endereco, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Listar todos os endereços
    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = cadastrarEnderecoServiceImpl.listarTodos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    // Atualizar um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        Endereco enderecoAtualizado = cadastrarEnderecoServiceImpl.atualizar(id, enderecoDTO);
        return enderecoAtualizado != null ?
                new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Deletar um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = cadastrarEnderecoServiceImpl.deletar(id);
        return deletado ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
