package br.com.caelum.ingresso.integracao;


import br.com.caelum.ingresso.model.Filme;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ImdbCliente {


    public <T> Optional<T> buscaDetalheDeUmFilme(Filme filme, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();

        String nomeFormatoParaNaoTerEspaco = filme.getNome().replace(" ", "+");
        String url = "https://omdb-fj22.herokuapp.com/movie?title=" + nomeFormatoParaNaoTerEspaco;


        try {
            return Optional.ofNullable(restTemplate.getForObject(url, clazz));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
