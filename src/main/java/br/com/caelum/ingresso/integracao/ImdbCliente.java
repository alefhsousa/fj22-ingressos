package br.com.caelum.ingresso.integracao;


import br.com.caelum.ingresso.model.Filme;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ImdbCliente {

    public Optional<DetalheDoFilme> buscaDetalheDeUmFilme(Filme filme) {
        RestTemplate restTemplate = new RestTemplate();

        String nomeFormatoParaNaoTerEspaco = filme.getNome().replace(" ", "+");
        String url = "https://omdb-fj22.herokuapp.com/movie?title=" + nomeFormatoParaNaoTerEspaco;


        try {
            DetalheDoFilme detalheDoFilme = restTemplate.getForObject(url, DetalheDoFilme.class);
            return Optional.ofNullable(detalheDoFilme);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
