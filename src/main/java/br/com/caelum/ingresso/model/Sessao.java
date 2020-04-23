package br.com.caelum.ingresso.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    private BigDecimal preco;

    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private Set<Ingresso> ingressos = new HashSet<>();


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
        this.preco = sala.getPreco().add(filme.getPreco());
    }

    public Lugar map(Ingresso ingresso) {
        return ingresso.getLugar();

    }
    public boolean isDisponivel(Lugar lugarSelecionado) {

        return ingressos.stream().map(Ingresso::getLugar).noneMatch(lugar -> lugar.equals(lugarSelecionado));
    }


    public Map<String, List<Lugar>> getMapaDeLugares() {
        return sala.getMapaDeLugares();
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

    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }
}
