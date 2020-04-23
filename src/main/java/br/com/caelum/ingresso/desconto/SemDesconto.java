package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

    @Override
    public BigDecimal calculaDesconto(BigDecimal precoCheio) {
        return precoCheio;
    }

    @Override
    public String textParaSerApresentadoParaOCliente() {
        return "Inteira";
    }
}
