package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public class DescontoParaEstudante implements Desconto {

    @Override
    public BigDecimal calculaDesconto(BigDecimal precoCheio) {
        return precoCheio.divide(new BigDecimal("2.0"));
    }
}
