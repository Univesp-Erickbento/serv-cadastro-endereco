package br.com.bento.serv_cadastro_endereco.controller;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.service.impl.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        List<EnderecoDTO> enderecos = enderecoService.findAll();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        Optional<EnderecoDTO> endereco = enderecoService.findById(id);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO savedEndereco = enderecoService.save(enderecoDTO);
        return ResponseEntity.ok(savedEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        Optional<EnderecoDTO> existingEndereco = enderecoService.findById(id);
        if (existingEndereco.isPresent()) {
            EnderecoDTO updatedEndereco = enderecoService.save(enderecoDTO);
            return ResponseEntity.ok(updatedEndereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (enderecoService.findById(id).isPresent()) {
            enderecoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
