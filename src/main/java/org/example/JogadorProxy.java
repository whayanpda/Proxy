package org.example;

import java.util.List;

public class JogadorProxy implements IJogador {
    private Jogador jogadorReal;
    private Integer id;

    public JogadorProxy(Integer id) {
        this.id = id;
    }

    @Override
    public List<String> obterDadosPublicos() {
        if (this.jogadorReal == null) {
            this.jogadorReal = new Jogador(this.id);
        }
        return this.jogadorReal.obterDadosPublicos();
    }

    @Override
    public List<String> obterDadosSegredos(Usuario usuario) {
        if (!usuario.isGameMaster()) {
            throw new IllegalArgumentException("Acesso negado: Usuario nao possui privilegios de GM.");
        }
        if (this.jogadorReal == null) {
            this.jogadorReal = new Jogador(this.id);
        }
        return this.jogadorReal.obterDadosSegredos(usuario);
    }
}
