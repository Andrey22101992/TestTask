import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class YoutubeTest {

    public ChromeDriver driver;


    @BeforeClass
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void closeDriver ()
    {
        driver.close();
    }

    @Test
    public void ActionsOnYoutubeSite()
    {
        // переход на страницу https://www.youtube.com/
        driver.get("https://www.youtube.com");
        // проверить, что вкладка браузера называется “YouTube”
        String title = driver.getTitle();
        Assert.assertEquals("YouTube", title);
        //  ввод в строку поиска запрос(запрос должен состоять из 2-4 цифр, случайных при каждом запуске теста)
        int a = 10; int b = 9999;  int randomNumber = a + (int) (Math.random() * b);
        String stringRandomNumber = String.valueOf(randomNumber);
        driver.findElement(By.name("search_query")).click();
        driver.findElement(By.name("search_query")).sendKeys(stringRandomNumber);
        // сделать браузер во весь экран
        driver.manage().window().maximize();
        try
        { Thread.sleep(1500); }
        catch(InterruptedException e)
        {
        }
        // в списке результатов поиска выбираем вторую позицию сверху
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("search_query")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("search_query")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("search_query")).sendKeys(Keys.ENTER);

        // запуск четвертого сверху видео в списке результатов поиска
        driver.findElement(By.xpath("//*[@id=\"contents\"]/ytd-video-renderer[4]")).click();
        try
        { Thread.sleep(1500); }
        catch(InterruptedException e)
        {
        }
        //  клик на аватар отправителя видео
        driver.findElement(By.cssSelector(".yt-simple-endpoint.style-scope.ytd-video-owner-renderer")).click();

        // клик на кнопку с текстом “ПОДПИСАТЬСЯ”
        driver.findElement(By.cssSelector("#subscribe-button .style-scope.ytd-c4-tabbed-header-renderer")).click();
        //  проверка, что текст “ВОЙТИ”(точное совпадение) отображается в появившемся контейнере
        String valueOfButton = driver.findElement(By.cssSelector("#text")).getText();
        Assert.assertEquals(valueOfButton,"ВОЙТИ");
        // закрытие вкладки браузера в @AfterMethod



    }


}
