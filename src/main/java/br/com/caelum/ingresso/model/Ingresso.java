package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Sessao sessao;

    @ManyToOne
    private Lugar lugar;

    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    private BigDecimal preco;

    /**
     *
     * @deprecated hibernate only
     */
    public Ingresso() {
    }

    public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
        this.sessao = sessao;
        this.tipoDeIngresso = tipoDeIngresso;
        this.lugar = lugar;
        this.preco = tipoDeIngresso.aplicaDesconto(sessao.getPreco());
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public BigDecimal getPreco() {
        return preco.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }

    public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
        this.tipoDeIngresso = tipoDeIngresso;
    }
}
