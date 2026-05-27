package org.example;

public class Usuario {
    private String nickname;
    private boolean gameMaster;

    public Usuario(String nickname, boolean gameMaster) {
        this.nickname = nickname;
        this.gameMaster = gameMaster;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isGameMaster() {
        return gameMaster;
    }
}
