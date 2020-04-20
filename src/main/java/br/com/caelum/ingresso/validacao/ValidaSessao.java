package br.com.caelum.ingresso.validacao;

import br.com.caelum.ingresso.model.Sessao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ValidaSessao {


    private List<Sessao> sessoesDaSala;

    public ValidaSessao(List<Sessao> sessoesDaSala) {
        this.sessoesDaSala = sessoesDaSala;
    }

    public boolean sessaoNaoTerminaNoDiaDeHoje(Sessao sessaoParaCriar) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime horarioDeInicioDaSessao = sessaoParaCriar.getHorario().atDate(hoje);
        LocalDateTime horarioDeTerminoDaSessao = horarioDeInicioDaSessao.plus(sessaoParaCriar.getFilme().getDuracao());

        LocalDateTime horarioFinalDeHoje = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        if (horarioDeTerminoDaSessao.isAfter(horarioFinalDeHoje)) {
            return true;
        }
        return false;
    }

    public boolean horarioEhConflitante(Sessao sessaoExistente, Sessao sessaoParaCriar) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime sessaoExistenteHorarioInicio = sessaoExistente.getHorario().atDate(hoje);
        LocalDateTime sessaoExistenteHorarioFinal = sessaoExistenteHorarioInicio.plus(sessaoExistente.getFilme().getDuracao());
        LocalDateTime horarioDeInicioDaSessao = sessaoParaCriar.getHorario().atDate(hoje);
        LocalDateTime horarioDeTerminoDaSessao = horarioDeInicioDaSessao.plus(sessaoParaCriar.getFilme().getDuracao());

        boolean sessaoParaCriarTerminaAntesDaExistente = horarioDeTerminoDaSessao.isBefore(sessaoExistenteHorarioInicio);
        boolean sessaoParaCriarComecaDepoisDaExistente = sessaoExistenteHorarioFinal.isBefore(horarioDeInicioDaSessao);


        if(sessaoParaCriarTerminaAntesDaExistente || sessaoParaCriarComecaDepoisDaExistente) {
            return false;
        }

        return true;
    }
    public boolean possoCriarSessao(Sessao sessaoParaCriar) {

        if (sessaoNaoTerminaNoDiaDeHoje(sessaoParaCriar)) {
            return false;
        }
        // java 7,6 ou menor
//        for (Sessao sessaoExistente: sessoesDaSala) {
//
//            if(horarioEhConflitante(sessaoExistente, sessaoParaCriar)) {
//                return false;
//            }
//            return true;
//        }

        //java8
        return sessoesDaSala
                .stream()
                .noneMatch(sessaoExistente -> horarioEhConflitante(sessaoExistente, sessaoParaCriar));

    }
}
