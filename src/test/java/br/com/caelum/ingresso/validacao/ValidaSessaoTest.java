package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidaSessaoTest {

    private Filme aLagoaAzul;
    private Sala salaImax;
    private Sessao sessaoExistente;
    private BigDecimal preco;

    @Before
    public void setup() {
        preco = new BigDecimal("25.00");
        aLagoaAzul = new Filme("A Lagoa Azul", Duration.ofMinutes(120), "Romance", BigDecimal.ONE);
        salaImax = new Sala("Sala IMAX", preco);
        sessaoExistente = new Sessao(aLagoaAzul, salaImax, LocalTime.parse("14:00:00"));
    }


    @Test
    public void deveCriarUmaSesssaoQuandoNaoHaRegistros() {
        Sala sala = new Sala("3D", BigDecimal.TEN);
        Filme filme = new Filme("Sergio", Duration.ofMinutes(80), "Ação", BigDecimal.TEN);
        Sessao sessao = new Sessao(filme, sala, LocalTime.parse("20:00:00"));
        ValidaSessao validaSessao = new ValidaSessao(new ArrayList<>());

        boolean retorno = validaSessao.possoCriarSessao(sessao);

        assertTrue(retorno);

    }

    @Test
    public void deveNaoCaberQuandoComecaAntesETerminaAntes() {
        ValidaSessao validaSessao = new ValidaSessao(Arrays.asList(sessaoExistente));
        Sessao sessaoNova = new Sessao(aLagoaAzul, salaImax, LocalTime.parse("11:00:00"));

        boolean coube = validaSessao.possoCriarSessao(sessaoNova);

        assertTrue(coube);

    }

    @Test
    public void naoDeveCaberQuandoComecaAntesETerminaDurante() {

        ValidaSessao validaSessao = new ValidaSessao(Arrays.asList(sessaoExistente));
        Sessao sessao = new Sessao(aLagoaAzul, salaImax, LocalTime.parse("13:00:00"));

        boolean coube = validaSessao.possoCriarSessao(sessao);

        assertFalse(coube);
    }

}

