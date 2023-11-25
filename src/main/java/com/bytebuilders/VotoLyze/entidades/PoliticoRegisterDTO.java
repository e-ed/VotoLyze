package com.bytebuilders.VotoLyze.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class PoliticoRegisterDTO {

    private String login;
    private String password;

    @JsonProperty("CPF")
    private String CPF;
    private String nome;
    private String sexo;
    private Date dataNascimento;
    private TipoCandidatura tipoCandidatura;
    private Date inicioMandato;
    private Partido partido;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoCandidatura getTipoCandidatura() {
        return tipoCandidatura;
    }

    public void setTipoCandidatura(TipoCandidatura tipoCandidatura) {
        this.tipoCandidatura = tipoCandidatura;
    }

    public Date getInicioMandato() {
        return inicioMandato;
    }

    public void setInicioMandato(Date inicioMandato) {
        this.inicioMandato = inicioMandato;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
