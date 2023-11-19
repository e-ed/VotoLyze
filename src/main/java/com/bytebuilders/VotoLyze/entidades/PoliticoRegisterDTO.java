package com.bytebuilders.VotoLyze.entidades;

import java.sql.Date;

public record PoliticoRegisterDTO(String login,
                                  String password,
                                  String CPF,
                                  String nome,
                                  String sexo,
                                  Date dataNascimento,
                                  TipoCandidatura tipoCandidatura,
                                  Date inicioMandato,
                                  Partido partido
                                  ) {

}
