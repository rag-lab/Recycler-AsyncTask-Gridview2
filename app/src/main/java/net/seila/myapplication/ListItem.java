package net.seila.myapplication;

/**
 * Created by rod on 27/05/2017.
 */

public class ListItem {

    private String titulo;
    private String urlCapa;
    private String sinopse;
    private String duracao;
    private String ano;

    public ListItem(String titulo, String urlCapa, String duracao, String ano, String sinopse) {
        this.titulo = titulo;
        this.urlCapa = urlCapa;
        this.duracao = duracao;
        this.ano = ano;
        this.sinopse = sinopse;
    }


    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlCapa() {
        String param = "";
        return param + urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
    }

}
