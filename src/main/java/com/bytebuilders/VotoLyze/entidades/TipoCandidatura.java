package com.bytebuilders.VotoLyze.entidades;

public enum TipoCandidatura {
    VEREADOR("Vereador(a)"), PREFEITO("Prefeito(a)");

    private String tipoCandidatura;

    TipoCandidatura(String s) {
        this.tipoCandidatura = s;
    }

    public String getValue() {
        return this.tipoCandidatura;
    }
}
