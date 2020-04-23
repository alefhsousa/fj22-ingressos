package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.desconto.Desconto;
import br.com.caelum.ingresso.desconto.DescontoParaEstudante;
import br.com.caelum.ingresso.desconto.SemDesconto;

import java.math.BigDecimal;

public enum  TipoDeIngresso {
    ESTUDANTE(new DescontoParaEstudante()),
    INTEIRA(new SemDesconto());

    private Desconto desconto;

    TipoDeIngresso(Desconto desconto) {
        this.desconto = desconto;
    }

    public BigDecimal aplicaDesconto(BigDecimal valor) {
        return this.desconto.calculaDesconto(valor);
    }

    public String getDescricao() {
        return this.desconto.textParaSerApresentadoParaOCliente();
    }
}
