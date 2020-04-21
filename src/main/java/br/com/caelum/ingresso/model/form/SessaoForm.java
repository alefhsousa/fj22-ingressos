package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class SessaoForm {


    @NotNull
    private Integer salaId;

    @NotNull
    private Integer filmeId;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime horario;

    public Sessao criaSessao(SalaDao salaDao, FilmeDao filmeDao) {
        Sala sala = salaDao.findOne(this.salaId);
        Filme filme = filmeDao.findOne(this.filmeId);

        return new Sessao(filme, sala, this.horario);
    }
    public Integer getSalaId() {
        return salaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public Integer getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
