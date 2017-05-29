package net.seila.myapplication;

/**
 * Created by rod on 27/05/2017.
 */

public class ListItem {

    private String titulo;
    private String urlCapa;

    public ListItem(String titulo, String urlCapa) {
        this.titulo = titulo;
        this.urlCapa = urlCapa;
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
