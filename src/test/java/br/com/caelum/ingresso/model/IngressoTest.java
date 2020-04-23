package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class IngressoTest {

    @Test
    public void deveRetornarUmIngressoParaEstudante() {

        Lugar lugar = new Lugar("A", 1);
        Sala sala = new Sala("SALA 3D", new BigDecimal("20.00"));
        Filme lagoAzul = new Filme("Lagoa Azul", Duration.ofMinutes(80), "Sessão da Tarde", BigDecimal.TEN);
        Sessao sessao = new Sessao(lagoAzul, sala, LocalTime.parse("10:00:00"));
        Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);

        Assert.assertEquals(new BigDecimal("15.00"), ingresso.getPreco());
    }

    // POLIFORMISMO


    @Test
    public void deveRetornarUmIngressoDoTipoInteiro() {
        Lugar lugar = new Lugar("A", 1);
        Sala sala = new Sala("SALA 3D", new BigDecimal("20.00"));
        Filme lagoAzul = new Filme("Lagoa Azul", Duration.ofMinutes(80), "Sessão da Tarde", BigDecimal.TEN);
        Sessao sessao = new Sessao(lagoAzul, sala, LocalTime.parse("10:00:00"));
        Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRA, lugar);

        Assert.assertEquals(new BigDecimal("30.00"), ingresso.getPreco());
    }


}
