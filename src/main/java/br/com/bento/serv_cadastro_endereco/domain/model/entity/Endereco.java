package br.com.bento.serv_cadastro_endereco.domain.model.entity;

import br.com.bento.serv_cadastro_endereco.domain.model.enuns.TipoEndereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "tb_enderecos")
public class Endereco {

    private static final long serialVersionUID = 1l;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private Long pessoaId;  // Use apenas o identificador de Pessoa
    private String logradouro;
    private String numero;
    private String complemento; // Ex: Apto, Sala, etc.
    private String bairro;
    private String localidade;
    private String estado;
    private String cep;
    private String pais = "Brasil";
    private String perfil;
  //  @Enumerated(EnumType.STRING) // Mapeando o enum como STRING
    private String tipoDeEndereco;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
