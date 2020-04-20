package br.com.caelum.ingresso.model;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Filme filme;

    @ManyToOne
    private Sala sala;

    private LocalTime horario;
    /*
    *
    * @hibernate only
     */
    public Sessao() {

    }

    public Sessao(Filme filme, Sala sala, LocalTime horario) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
    }


    public Filme getFilme() {
        return filme;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }
}
