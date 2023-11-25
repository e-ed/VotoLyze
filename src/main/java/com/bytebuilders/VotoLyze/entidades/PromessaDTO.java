package com.bytebuilders.VotoLyze.entidades;

import java.sql.Date;

public record PromessaDTO(String titulo, String descricao, Date data, Politico politico) {
}
