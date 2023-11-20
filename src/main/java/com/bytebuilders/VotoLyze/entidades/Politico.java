package com.bytebuilders.VotoLyze.entidades;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "POLITICOS")
public class Politico implements UserDetails {

    @OneToOne
    @JoinColumn(name="FK_PARTIDOS_PRT_ID", referencedColumnName = "PRT_ID")
    private Partido partido;

    @Column(name="PLT_TIPO_CANDIDATURA", nullable = false)

    private String tipoCandidatura;

    @Column(name="PLT_INICIO_MANDATO")
    private Date inicioMandato;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USR_ID", nullable = false)
    private Integer id;

    @Column(name = "USR_NOME", nullable = false)
    private String nome;

    @Column(name = "USR_SEXO", nullable = false)
    private char sexo;

    @Column(name = "USR_DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

    @Column(name = "USR_EMAIL", nullable = false)
    private String email;

    @Column(name = "USR_CPF", nullable = false)
    private String CPF;

    @Column(name = "USR_SENHA", nullable = false)
    private String senha;

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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getTipoCandidatura() {
        return tipoCandidatura;
    }

    public void setTipoCandidatura(String tipoCandidatura) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
