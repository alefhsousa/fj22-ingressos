package br.com.caelum.ingresso.dao;


import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessaoDao {

    @PersistenceContext
    private EntityManager manager;

    public void salvar(Sessao sessao) {
        manager.persist(sessao);
    }

    public List<Sessao> findBySala(Sala sala) {
        return manager.createQuery("select s from Sessao s where s.sala = :sala", Sessao.class)
                .setParameter("sala", sala)
                .getResultList();
    }
}
