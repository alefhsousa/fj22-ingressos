package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.integracao.ImagemDoFilme;
import br.com.caelum.ingresso.integracao.ImdbCliente;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.ValidaSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class SessaoController {

    @Autowired
    private FilmeDao filmeDao;

    @Autowired
    private SalaDao salaDao;

    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private ImdbCliente imdbCliente;

    @GetMapping("/sessao/{id}/lugares")
    public ModelAndView lugaresDaSessao(@PathVariable("id") Integer sessaoId) {
        Sessao sessao = sessaoDao.findOne(sessaoId);
        ModelAndView view = new ModelAndView("sessao/lugares");
        view.addObject("sessao", sessao);
        Optional<ImagemDoFilme> imagemDoFilme = imdbCliente.buscaDetalheDeUmFilme(sessao.getFilme(), ImagemDoFilme.class);
        view.addObject("imagemCapa", imagemDoFilme.orElse(new ImagemDoFilme()));
        return view;
    }

    @GetMapping("/admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer id, SessaoForm form) {
        ModelAndView view = new ModelAndView("sessao/sessao");
        List<Filme> todosFilmes = filmeDao.findAll();
        Sala sala = salaDao.findOne(id);
        view.addObject("filmes", todosFilmes);
        view.addObject("sala", sala);
        view.addObject("form", form);
        return view;
    }

    @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView salvar(@Valid SessaoForm sessaoForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return form(sessaoForm.getSalaId(), sessaoForm);
        }

        Sessao sessao = sessaoForm.criaSessao(salaDao, filmeDao);

        List<Sessao> sessoesDaSala = sessaoDao.findBySala(sessao.getSala());
        ValidaSessao validadorDeSessao = new ValidaSessao(sessoesDaSala);

        if (validadorDeSessao.possoCriarSessao(sessao)) {
            sessaoDao.salvar(sessao);
            return new ModelAndView("redirect:/admin/sala/" + sessao.getSala().getId() + "/sessoes");
        }

        return form(sessaoForm.getSalaId(), sessaoForm);
    }
}
