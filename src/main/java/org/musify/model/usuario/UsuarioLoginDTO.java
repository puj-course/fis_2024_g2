package org.musify.model.usuario;

public class UsuarioLoginDTO {
    private String nickname;
    private String password;
    public UsuarioLoginDTO() {}

    public UsuarioLoginDTO(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
