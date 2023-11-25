package com.bytebuilders.VotoLyze.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;


@Entity
@Table(name = "PROMESSAS")
public class Promessa {

    @ManyToOne
    @JoinColumn(name="FK_POLITICOS_USR_ID", referencedColumnName = "USR_ID")
    private Politico politico;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PRM_ID", nullable = false)
    private Integer id;

    @Column(name = "PRM_TITULO", nullable = false)
    private String titulo;

    @Column(name="PRM_DESCRICAO", nullable = false)
    private String descricao;

    @Column(name="PRM_DATA", nullable = false)
    private Date data;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Politico getPolitico() {
        return politico;
    }

    public void setPolitico(Politico politico) {
        this.politico = politico;
    }

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
}
