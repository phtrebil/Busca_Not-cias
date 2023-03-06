package com.example.buscanoticia.formatador;

public class FormatadorDeEspacos {

    private String frase;

    public FormatadorDeEspacos(String frase) {
        this.frase = frase;
    }

    ;

    public String tiraEspaco() {
        return frase.replaceAll("\\s+", "");
    }


}
