package br.com.alura.leilao;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void fechar(){
        this.browser.quit();
    }

    public void preencheFormularioDeLogin(String username, String password){
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuaLogin(){
        this.browser.findElement(By.id("login-form")).submit();
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
