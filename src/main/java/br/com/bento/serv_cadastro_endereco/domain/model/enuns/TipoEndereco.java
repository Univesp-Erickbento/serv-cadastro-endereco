package br.com.bento.serv_cadastro_endereco.domain.model.enuns;

public enum TipoEndereco {
    PRINCIPAL(0, "ENDEREÇO PRINCIPAL"), ENDERECO2(1, "ENDEREÇO SECUNDARIO"), ENDERECO3(2, "TERCEIRO ENDEREÇO");

    private Integer codigo;
    private String descricao;

    private TipoEndereco(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEndereco toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TipoEndereco x : TipoEndereco.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de endereço inválido");
    }
}
