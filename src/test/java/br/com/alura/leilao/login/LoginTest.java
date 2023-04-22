package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void finalizar() {
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();

        assertFalse(paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano", paginaDeLogin.nomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        paginaDeLogin.preencheFormularioDeLogin("invalido", "123456");
        paginaDeLogin.efetuaLogin();

        assertEquals("http://localhost:8080/login?error", paginaDeLogin.pegaUrlDaPagina());
        assertTrue(paginaDeLogin.contemTextoNaPagina("Usuário e senha inválidos."));
        assertNull(paginaDeLogin.nomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaAcessarPaginasRestritaSemEstarLogado() {
        paginaDeLogin.navegaParaPaginaDeLances();

        assertEquals("http://localhost:8080/login", paginaDeLogin.pegaUrlDaPagina());
        assertFalse(paginaDeLogin.contemTextoNaPagina("Dados do Leilão"));

    }
}
