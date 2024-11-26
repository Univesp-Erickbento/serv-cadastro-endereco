package br.com.bento.serv_cadastro_endereco.service.impl;

import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
import br.com.bento.serv_cadastro_endereco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> findAll() {
        return enderecoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnderecoDTO> findById(Long id) {
        return enderecoRepository.findById(id).map(this::convertToDTO);
    }

    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        Endereco endereco = convertToEntity(enderecoDTO);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        return convertToDTO(savedEndereco);
    }

    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }

    private EnderecoDTO convertToDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getPessoaId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getPais(),
                endereco.getTipoDePessoa(),
                endereco.getTipoDeEndereco()
        );
    }

    private Endereco convertToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setPessoaId(enderecoDTO.pessoaId());
        endereco.setRua(enderecoDTO.rua());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setCep(enderecoDTO.cep());
        endereco.setPais(enderecoDTO.pais());
        endereco.setTipoDePessoa(enderecoDTO.tipoDePessoa());
        endereco.setTipoEndereco(enderecoDTO.tipoDeEndereco());
        return endereco;
    }
}
