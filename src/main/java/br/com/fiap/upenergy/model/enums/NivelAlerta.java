package br.com.fiap.upenergy.model.enums;

public enum NivelAlerta {

    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private String nivel;

    NivelAlerta(String nivel){
        this.nivel = nivel;
    }

    public String getNivel(){
        return this.nivel;
    }

}
