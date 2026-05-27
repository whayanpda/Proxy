package org.example;

import java.util.Arrays;
import java.util.List;

public class Jogador implements IJogador {
    private Integer id;
    private String nickname;
    private String classeEstrategica;
    private String emailConta;
    private String carteiraCreditos;

    public Jogador(int id) {
        this.id = id;
        Jogador objetoDoBanco = BancoDadosJogo.getJogador(id);
        this.nickname = objetoDoBanco.nickname;
        this.classeEstrategica = objetoDoBanco.classeEstrategica;
        this.emailConta = objetoDoBanco.emailConta;
        this.carteiraCreditos = objetoDoBanco.carteiraCreditos;
    }

    public Jogador(Integer id, String nickname, String classeEstrategica, String emailConta, String carteiraCreditos) {
        this.id = id;
        this.nickname = nickname;
        this.classeEstrategica = classeEstrategica;
        this.emailConta = emailConta;
        this.carteiraCreditos = carteiraCreditos;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public List<String> obterDadosPublicos() {
        return Arrays.asList(this.nickname, this.classeEstrategica);
    }

    @Override
    public List<String> obterDadosSegredos(Usuario usuario) {
        return Arrays.asList(this.emailConta, this.carteiraCreditos);
    }
}
