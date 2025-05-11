package br.com.fiap.upenergy.model;

public class RespostaGeneric {

    private String message;

    public RespostaGeneric() {}

    public RespostaGeneric(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
