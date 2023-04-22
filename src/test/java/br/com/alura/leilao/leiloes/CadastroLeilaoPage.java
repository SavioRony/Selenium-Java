package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage  extends PageObject {
    private final String URL_NOVO_LEILAO = "http://localhost:8080/leiloes/new";
    public CadastroLeilaoPage(WebDriver browser) {
        super(browser);
    }

    public LeiloesPage cadastrarLeilao(String nome, String valor, String data) {
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valor")).sendKeys(valor);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
        this.browser.findElement(By.id("button-submit")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaAtual() {
        return this.browser.getCurrentUrl().equals(URL_NOVO_LEILAO);
    }

    public boolean isMensagensDeValidacoesVisiveis() {
        String pageSource = this.browser.getPageSource();

        return pageSource.contains("minimo 3 caracteres")
                && pageSource.contains("não deve estar em branco")
                && pageSource.contains("deve ser um valor maior de 0.1")
                && pageSource.contains("deve ser uma data no formato dd/MM/yyyy");

    }
}
