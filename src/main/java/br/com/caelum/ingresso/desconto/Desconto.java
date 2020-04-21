package br.com.caelum.ingresso.desconto;

import java.math.BigDecimal;

public interface Desconto {

    BigDecimal calculaDesconto(BigDecimal precoCheio);

}

