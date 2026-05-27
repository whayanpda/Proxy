package org.example;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JogadorProxyTest {

    @Test
    void deveRetornarDadosPublicosDoJogador() {
        BancoDadosJogo.addJogador(new Jogador(1, "Artemis", "Mago", "artemis@email.com", "1500"));
        JogadorProxy proxy = new JogadorProxy(1);

        assertEquals(Arrays.asList("Artemis", "Mago"), proxy.obterDadosPublicos());
    }

    @Test
    void deveRetornarDadosSegredosQuandoUsuarioForGameMaster() {
        BancoDadosJogo.addJogador(new Jogador(2, "Nyx", "Assassino", "nyx@email.com", "3200"));
        JogadorProxy proxy = new JogadorProxy(2);
        Usuario gameMaster = new Usuario("Admin", true);

        assertEquals(Arrays.asList("nyx@email.com", "3200"), proxy.obterDadosSegredos(gameMaster));
    }

    @Test
    void deveNegarDadosSegredosQuandoUsuarioNaoForGameMaster() throws Exception {
        BancoDadosJogo.addJogador(new Jogador(3, "Orion", "Guerreiro", "orion@email.com", "900"));
        JogadorProxy proxy = new JogadorProxy(3);
        Usuario jogadorComum = new Usuario("Visitante", false);

        assertThrows(IllegalArgumentException.class, () -> proxy.obterDadosSegredos(jogadorComum));
        assertNull(getJogadorReal(proxy));
    }

    @Test
    void deveCarregarJogadorRealApenasQuandoDadosForemConsultados() throws Exception {
        BancoDadosJogo.addJogador(new Jogador(4, "Luna", "Arqueiro", "luna@email.com", "250"));
        JogadorProxy proxy = new JogadorProxy(4);

        assertNull(getJogadorReal(proxy));

        proxy.obterDadosPublicos();

        assertNotNull(getJogadorReal(proxy));
    }

    private Jogador getJogadorReal(JogadorProxy proxy) throws Exception {
        Field jogadorReal = JogadorProxy.class.getDeclaredField("jogadorReal");
        jogadorReal.setAccessible(true);
        return (Jogador) jogadorReal.get(proxy);
    }
}
