package br.com.caelum.ingresso.integracao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagemDoFilme {

    @JsonProperty("Poster")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
