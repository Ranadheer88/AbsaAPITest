package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExtentReports.ExtentTestManager;
import utils.Listner.TestListener;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;
@Listeners(TestListener.class)
public class BreedsApiTest {

    Response allBreedsResponse;
    @BeforeSuite
    public void setUp(){
        RestAssured.baseURI = "https://dog.ceo/api";
    }

    @Test
    public void getAllBreeds(){
        ExtentTestManager.getTest().log(LogStatus.INFO,"Base URI", RestAssured.baseURI);
        String path = "/breeds/list/all";
        ExtentTestManager.getTest().log(LogStatus.INFO,"Path", path);
        allBreedsResponse = given().when().get(path).then().
                statusCode(200).body("status", equalTo("success")).
                extract().response();
        ExtentTestManager.getTest().log(LogStatus.INFO,"Response Body", allBreedsResponse.body().prettyPrint());
    }

    @Test(dependsOnMethods = { "getAllBreeds" })
    public void checkRetrieverOnAllBreeds(){
        allBreedsResponse.then().body("message",hasKey("retriever"));
    }

    @Test
    public void getSubBreedForRetriever(){
        ExtentTestManager.getTest().log(LogStatus.INFO,"Base URI", RestAssured.baseURI);
        String path = "/breed/retriever/list";
        ExtentTestManager.getTest().log(LogStatus.INFO,"Path", path);
        Response response = given().when().get(path).then().
                statusCode(200).body("status", equalTo("success")).
                extract().response();
        ExtentTestManager.getTest().log(LogStatus.INFO,"Response Body", response.body().prettyPrint());
    }

    @Test
    public void getRandomImageForSubBreedGolden(){
        ExtentTestManager.getTest().log(LogStatus.INFO,"Base URI", RestAssured.baseURI);
        String path = "/breed/retriever/golden/images/random";
        ExtentTestManager.getTest().log(LogStatus.INFO,"Path", path);
        Response response = given().when().get(path).then().
                statusCode(200).body("status", equalTo("success")).
                extract().response();
        ExtentTestManager.getTest().log(LogStatus.INFO,"Response Body", response.body().prettyPrint());
    }

}
