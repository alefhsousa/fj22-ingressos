package br.com.caelum.ingresso.desconto;


import br.com.caelum.ingresso.desconto.SemDesconto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class SemDescontoTest {

    @Test
    public void naoDeveAplicarDesconto() {
        SemDesconto semDesconto = new SemDesconto();
        BigDecimal vinteReais = new BigDecimal("20.00");
        BigDecimal valorComDescontoAplicado = semDesconto.calculaDesconto(vinteReais);

        Assert.assertEquals(vinteReais, valorComDescontoAplicado);
    }
}
