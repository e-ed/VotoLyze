package com.bytebuilders.VotoLyze.entidades;

import java.sql.Date;

public class PromessaDTO {
    private String titulo;
    private String descricao;
    private Date data;
    private Politico politico;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Politico getPolitico() {
        return politico;
    }

    public void setPolitico(Politico politico) {
        this.politico = politico;
    }
}
