package com.bytebuilders.VotoLyze.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "PARTIDOS")
public class Partido {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PRT_ID", nullable = false)
    private Integer id;

    @Column(name = "PRT_NOME", nullable = false)
    private String nome;

    @Column(name="PRT_SIGLA", nullable = false)
    private String sigla;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
