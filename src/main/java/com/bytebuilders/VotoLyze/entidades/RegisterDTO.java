/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bytebuilders.VotoLyze.entidades;

import java.sql.Date;

/**
 *
 * @author eduardo
 */
public record RegisterDTO(String login, String password, String CPF, String nome, String sexo, Date dataNascimento) {
    
}
