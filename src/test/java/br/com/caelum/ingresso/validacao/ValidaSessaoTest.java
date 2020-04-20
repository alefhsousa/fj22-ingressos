package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class ValidaSessaoTest {

    @Test
    public void deveCriarUmaSesssaoQuandoNaoHaRegistros() {
        Sala sala = new Sala("3D");
        Filme filme = new Filme("Sergio", Duration.ofMinutes(80), "Ação");
        Sessao sessao = new Sessao(filme, sala, LocalTime.parse("20:00:00"));
        ValidaSessao validaSessao = new ValidaSessao(new ArrayList<>());

        boolean retorno = validaSessao.possoCriarSessao(sessao);

        Assert.assertTrue(retorno);

    }



}
