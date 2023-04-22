package br.com.alura.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageObject {
    protected WebDriver browser;
    public void fechar(){
        this.browser.quit();
    }

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        if(browser == null){
            this.browser = new ChromeDriver();
        }else{
            this.browser = browser;
        }

        //Configuração de TimeOut
        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)    //Espera por elementos na pagina.
                .pageLoadTimeout(10, TimeUnit.SECONDS);  //Espera de carregamento da pagina.
    }
}
