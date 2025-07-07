package base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("baseURI");
        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }
}
