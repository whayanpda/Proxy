package org.example;

import java.util.List;

public interface IJogador {
    List<String> obterDadosPublicos();
    List<String> obterDadosSegredos(Usuario usuario);
}
