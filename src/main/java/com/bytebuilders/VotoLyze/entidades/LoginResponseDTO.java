
package com.bytebuilders.VotoLyze.entidades;

/**
 *
 * @author eduardo
 */
public class LoginResponseDTO {
    private String token;
    private String tipoUsuario;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
