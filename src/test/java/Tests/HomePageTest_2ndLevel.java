package Tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class HomePageTest_2ndLevel {

    WebDriver driver;
    WireMockServer wireMockServer;
    WebDriverWait wait;
    @BeforeTest
    public void background(){
        wireMockServer = new WireMockServer(9999); //No-args constructor will start on port 8080, no HTTPS
        configureFor(9999);
        wireMockServer.start();

//        stubFor(any(anyUrl())
//                .willReturn(aResponse().withStatus(204)
//                        .withHeader("Content-Type", "application/json")
//                        .withHeader("Access-Control-Allow-Origin","http://www.decodingwithsahil.com")
//                        .withHeader("Access-Control-Allow-Methods","POST")
//                        .withHeader("Access-Control-Allow-Headers","Content-Type")));
    }
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.decodingwithsahil.com/NewYearSaleDiscountDemo1.html");
        wait = new WebDriverWait(driver, 2000);
    }

    @Test
    public void test1() {
        stubFor(post(anyUrl())
                .withRequestBody(equalToJson("{\"CID\":\"1234\",\"shopping\":\"9000\"}"))
                .withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(201).withBody(("{\n" +
                                "    \"successMsg\": \"true\",\n" +
                                "    \"res\": [\n" +
                                "        {\n" +
                                "            \"eligible\": \"Yes, You Are Eligibile For NEW YEAR Discount !\",\n" +
                                "            \"discount\": \"45%\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"eligible\": \"Yes, You Are Eligibile For NEW YEAR Discount !\",\n" +
                                "            \"discount\": \"75%\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}")).withHeader("Content-Type", "application/json")
                        .withHeader("Access-Control-Allow-Origin","http://www.decodingwithsahil.com")));


        driver.findElement(By.id("CID")).clear();
        driver.findElement(By.id("CID")).sendKeys("1234");
        driver.findElement(By.id("shoppingAmount")).clear();
        driver.findElement(By.id("shoppingAmount")).sendKeys("9000");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='response_1']/h2")));
        WebElement heading = driver.findElement(By.xpath("//*[@id='response_1']/h2"));
        WebElement responseMsg1 = driver.findElement(By.xpath("//*[@id='response_1']/h3[1]"));
        WebElement discountMsg1 = driver.findElement(By.xpath("//*[@id='response_1']/h3[2]"));
        WebElement responseMsg2 = driver.findElement(By.xpath("//*[@id='response_2']/h3[1]"));
        WebElement discountMsg2 = driver.findElement(By.xpath("//*[@id='response_2']/h3[2]"));

        Assert.assertTrue(heading.isDisplayed());
        Assert.assertTrue(responseMsg1.isDisplayed());
        Assert.assertTrue(discountMsg2.isDisplayed());
        Assert.assertTrue(responseMsg1.isDisplayed());
        Assert.assertTrue(discountMsg2.isDisplayed());


        Assert.assertEquals(heading.getText(), "DISCOUNT LIST :");
        Assert.assertEquals(responseMsg1.getText(), "TATA Sales : Yes, You Are Eligibile For NEW YEAR Discount !");
        Assert.assertEquals(discountMsg1.getText(), "Discount Percentage : 45%");
        Assert.assertEquals(responseMsg2.getText(), "Reliance Sales : Yes, You Are Eligibile For NEW YEAR Discount !");
        Assert.assertEquals(discountMsg2.getText(), "Discount Percentage : 75%");
    }


    @Test
    public void test2() {
        stubFor(post(anyUrl())
                .withRequestBody(equalToJson("{\"CID\":\"1234\",\"shopping\":\"2000\"}"))
                .withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(201).withBody(("{\n" +
                                "    \"successMsg\": \"true\",\n" +
                                "    \"res\": [\n" +
                                "        {\n" +
                                "            \"eligible\": \"Yes, You Are Eligibile For NEW YEAR Discount !\",\n" +
                                "            \"discount\": \"5%\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"eligible\": \"No , Sorry You Are No Eligible ! Try Next Year ~\",\n" +
                                "            \"discount\": \"NA\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}")).withHeader("Content-Type", "application/json")
                        .withHeader("Access-Control-Allow-Origin","http://www.decodingwithsahil.com")));


        driver.findElement(By.id("CID")).clear();
        driver.findElement(By.id("CID")).sendKeys("1234");
        driver.findElement(By.id("shoppingAmount")).clear();
        driver.findElement(By.id("shoppingAmount")).sendKeys("2000");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='response_1']/h2")));
        WebElement heading = driver.findElement(By.xpath("//*[@id='response_1']/h2"));
        WebElement responseMsg1 = driver.findElement(By.xpath("//*[@id='response_1']/h3[1]"));
        WebElement discountMsg1 = driver.findElement(By.xpath("//*[@id='response_1']/h3[2]"));
        WebElement responseMsg2 = driver.findElement(By.xpath("//*[@id='response_2']/h3[1]"));
        WebElement discountMsg2 = driver.findElement(By.xpath("//*[@id='response_2']/h3[2]"));

        Assert.assertTrue(heading.isDisplayed());
        Assert.assertTrue(responseMsg1.isDisplayed());
        Assert.assertTrue(discountMsg2.isDisplayed());
        Assert.assertTrue(responseMsg1.isDisplayed());
        Assert.assertTrue(discountMsg2.isDisplayed());


        Assert.assertEquals(heading.getText(), "DISCOUNT LIST :");
        Assert.assertEquals(responseMsg1.getText(), "TATA Sales : Yes, You Are Eligibile For NEW YEAR Discount !");
        Assert.assertEquals(discountMsg1.getText(), "Discount Percentage : 5%");
        Assert.assertEquals(responseMsg2.getText(), "Reliance Sales : No , Sorry You Are No Eligible ! Try Next Year ~");
        Assert.assertEquals(discountMsg2.getText(), "Discount Percentage : NA");
    }



    @Test
    public void test3() {

        stubFor(post(anyUrl())
                .withRequestBody(equalToJson("{\"CID\":\"5678\",\"shopping\":\"1000\"}"))
                .withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(201).withBody(("{\n" +
                                "    \"successMsg\": \"true\",\n" +
                                "    \"res\": [\n" +
                                "        {\n" +
                                "            \"eligible\": \"No , Sorry You Are No Eligible ! Try Next Year ~\",\n" +
                                "            \"discount\": \"NA\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"eligible\": \"No , Sorry You Are No Eligible ! Try Next Year ~\",\n" +
                                "            \"discount\": \"NA\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}")).withHeader("Content-Type", "application/json")
                        .withHeader("Access-Control-Allow-Origin","http://www.decodingwithsahil.com")));


        driver.findElement(By.id("CID")).clear();
        driver.findElement(By.id("CID")).sendKeys("5678");
        driver.findElement(By.id("shoppingAmount")).clear();
        driver.findElement(By.id("shoppingAmount")).sendKeys("1000");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='response_1']/h2")));
        WebElement heading = driver.findElement(By.xpath("//*[@id='response_1']/h2"));
        WebElement responseMsg1 = driver.findElement(By.xpath("//*[@id='response_1']/h3[1]"));
        WebElement discountMsg1 = driver.findElement(By.xpath("//*[@id='response_1']/h3[2]"));
        WebElement responseMsg2 = driver.findElement(By.xpath("//*[@id='response_2']/h3[1]"));
        WebElement discountMsg2 = driver.findElement(By.xpath("//*[@id='response_2']/h3[2]"));

        Assert.assertTrue(heading.isDisplayed());
        Assert.assertTrue(responseMsg1.isDisplayed());
        Assert.assertTrue(discountMsg2.isDisplayed());
        Assert.assertTrue(responseMsg1.isDisplayed());
        Assert.assertTrue(discountMsg2.isDisplayed());


        Assert.assertEquals(heading.getText(), "DISCOUNT LIST :");
        Assert.assertEquals(responseMsg1.getText(), "TATA Sales : No , Sorry You Are No Eligible ! Try Next Year ~");
        Assert.assertEquals(discountMsg1.getText(), "Discount Percentage : NA");
        Assert.assertEquals(responseMsg2.getText(), "Reliance Sales : No , Sorry You Are No Eligible ! Try Next Year ~");
        Assert.assertEquals(discountMsg2.getText(), "Discount Percentage : NA");
    }



    @Test
    public void test4() {
        stubFor(post(anyUrl())
                .withRequestBody(equalToJson("{\"CID\":\"0101\",\"shopping\":\"5000\"}"))
                .withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(200).withBody(("{\n" +
                                "    \"successMsg\": \"false\",\n" +
                                "    \"message\": \"Invalid Customer ID.\"\n" +
                                "}")).withHeader("Content-Type", "application/json")
                        .withHeader("Access-Control-Allow-Origin","http://www.decodingwithsahil.com")));


        driver.findElement(By.id("CID")).clear();
        driver.findElement(By.id("CID")).sendKeys("0101");
        driver.findElement(By.id("shoppingAmount")).clear();
        driver.findElement(By.id("shoppingAmount")).sendKeys("5000");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='response-container']/h2[1]")));

        WebElement errorMsg = driver.findElement(By.xpath("//*[@id='response-container']/h2[1]"));
        WebElement errorMsgDetails = driver.findElement(By.xpath("//*[@id='response-container']/h2[2]"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertTrue(errorMsgDetails.isDisplayed());

        Assert.assertEquals(errorMsg.getText(), "INVALID CUSTOMER ID.");
        Assert.assertEquals(errorMsgDetails.getText(), "PLEASE TRY AGAIN AFTER SOME TIME OR CONTACT OUR CUSTOMER SUPPORT FOR MORE INFORMATION.");

    }


    @Test
    public void test5() {
        stubFor(post(anyUrl())
                .withRequestBody(equalToJson("{\"CID\":\"1234\",\"shopping\":\"9999\"}"))
                .withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(500).withHeader("Content-Type", "application/json")
                        .withHeader("Access-Control-Allow-Origin","http://www.decodingwithsahil.com").withBody("Internal Server Error")));


        driver.findElement(By.id("CID")).clear();
        driver.findElement(By.id("CID")).sendKeys("1234");
        driver.findElement(By.id("shoppingAmount")).clear();
        driver.findElement(By.id("shoppingAmount")).sendKeys("9999");
        driver.findElement(By.xpath("//button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='response-container']/h2[1]")));

        WebElement errorMsg = driver.findElement(By.xpath("//*[@id='response-container']/h2[1]"));
        WebElement errorMsgDetail = driver.findElement(By.xpath("//*[@id='response-container']/h2[2]"));
        wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='response-container']/h3")));


        WebElement discountMsg = driver.findElement(By.xpath("//*[@id='response-container']/h3"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertTrue(errorMsgDetail.isDisplayed());
        Assert.assertTrue(discountMsg.isDisplayed());

        Assert.assertEquals(errorMsg.getText(), "SORRY , THERE WAS A SERVER SIDE ERROR.");
        Assert.assertEquals(errorMsgDetail.getText(), "PLEASE TRY AGAIN AFTER SOME TIME");
        Assert.assertEquals(discountMsg.getText(), "Discount Percentage : ERROR");
    }


    @AfterMethod
    public void tearDown() {
       driver.quit();
    }
}