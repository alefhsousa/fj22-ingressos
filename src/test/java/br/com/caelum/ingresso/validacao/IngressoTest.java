package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.desconto.DescontoParaEstudante;
import br.com.caelum.ingresso.desconto.SemDesconto;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class IngressoTest {

    @Test
    public void deveRetornarUmIngressoParaEstudante() {

        Sala sala = new Sala("SALA 3D", new BigDecimal("20.00"));
        Filme lagoAzul = new Filme("Lagoa Azul", Duration.ofMinutes(80), "Sessão da Tarde", BigDecimal.TEN);
        Sessao sessao = new Sessao(lagoAzul, sala, LocalTime.parse("10:00:00"));
        Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudante());

        Assert.assertEquals(new BigDecimal("15.00"), ingresso.getPreco());
    }

    // POLIFORMISMO


    @Test
    public void deveRetornarUmIngressoDoTipoInteiro() {

        Sala sala = new Sala("SALA 3D", new BigDecimal("20.00"));
        Filme lagoAzul = new Filme("Lagoa Azul", Duration.ofMinutes(80), "Sessão da Tarde", BigDecimal.TEN);
        Sessao sessao = new Sessao(lagoAzul, sala, LocalTime.parse("10:00:00"));
        Ingresso ingresso = new Ingresso(sessao, new SemDesconto());

        Assert.assertEquals(new BigDecimal("30.00"), ingresso.getPreco());
    }


}
