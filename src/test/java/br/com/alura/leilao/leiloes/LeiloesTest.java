package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        this.paginaDeCadastro = paginaDeLeiloes.carregarFumulario();
    }
    @AfterEach
    public void finalizar() {
        this.paginaDeLeiloes.fechar();
    }
    @Test
    public void deveriaCadastrarLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = this.paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);

        assertTrue(this.paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));

    }

    @Test
    public void deveriaValidarCadastroDeLeilao(){
        this.paginaDeLeiloes = this.paginaDeCadastro.cadastrarLeilao("", "", "");

        assertFalse(this.paginaDeCadastro.isPaginaAtual());
        assertTrue(this.paginaDeCadastro.isMensagensDeValidacoesVisiveis());
    }

}
