package br.com.alura.leilao.login;


import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {
    private final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public void preencheFormularioDeLogin(String username, String password){
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuaLogin(){
        this.browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaDeLogin(){
        return URL_LOGIN.equals(browser.getCurrentUrl());
    }
    public Object nomeUsuarioLogado(){
        try {
            return browser.findElement(By.id("usuarioLogado")).getText();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public Object pegaUrlDaPagina(){
        return browser.getCurrentUrl();
    }

    public void navegaParaPaginaDeLances(){
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTextoNaPagina(String texto){
        return browser.getPageSource().contains(texto);
    }

}
