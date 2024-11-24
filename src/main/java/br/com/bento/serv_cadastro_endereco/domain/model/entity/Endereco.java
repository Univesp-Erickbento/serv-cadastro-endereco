package br.com.bento.serv_cadastro_endereco.domain.model.entity;

import br.com.bento.serv_cadastro_endereco.domain.model.dto.PessoaDTO;
import br.com.bento.serv_cadastro_endereco.domain.model.enuns.TipoEndereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "enderecos")
public class Endereco {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;  // Use apenas o identificador de Pessoa

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais = "Brasil";
    private String tipoDePessoa;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoDeEndereco;


    // Getters e Setters, incluindo o tipoEndereco
    public TipoEndereco getTipoEndereco() {
        return tipoDeEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoDeEndereco = tipoEndereco;
    }

}
