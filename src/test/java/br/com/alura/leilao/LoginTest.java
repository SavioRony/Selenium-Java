package br.com.alura.leilao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;
    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
    }

    @BeforeEach
    public void inicializar(){
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }
    @AfterEach
    public void finalizar(){
        this.browser.quit();
    }
    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertNotEquals(URL_LOGIN, browser.getCurrentUrl());
        assertEquals("fulano", browser.findElement(By.id("usuarioLogado")).getText());

    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos(){
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("login-form")).submit();

        assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class,() -> browser.findElement(By.id("usuarioLogado")).getText());

    }

    @Test
    public void naoDeveriaAcessarPaginasRestritaSemEstarLogado(){
        this.browser.navigate().to("http://localhost:8080/leiloes/2");

        assertEquals("http://localhost:8080/login", browser.getCurrentUrl());
        assertFalse(browser.getPageSource().contains("Dados do Leilão"));

    }
}
