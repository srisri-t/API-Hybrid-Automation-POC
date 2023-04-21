package step.definitions;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.Assert;

import java.net.URI;

import static org.hamcrest.Matchers.equalTo;


public class DemoRestAPITest {

    private Scenario scenario;
    private Response response;
//    private final String BASE_URL = "http://localhost:8888";
    private final String BASE_URL = "https://reqres.in/api";

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(url));
    }

    @Then("Response Code {string} is validated")
    public void response_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
//        response.then().statusCode(200);

        int actualResponseCode = response.then().extract().statusCode();
        System.out.println("Response  "+response.then().extract().response());
        System.out.println("Status res Code  "+responseCode);
        System.out.println("Status actual res Code  "+actualResponseCode);

        Assert.assertEquals(responseMessage, responseCode+"");
    }

    @Then("Response  is array total {string}")
    public void responseIsArrayWith(String arg0) {
        response.then().statusCode(200);
        response = response.then().extract().response();
        scenario.log("Response Received == " + response.asPrettyString());
        JSONArray resJson = new JSONArray(response.asString());
        Assert.assertEquals(resJson.length()+"",arg0);
    }



    @Given("the API endpoint is {string}")
    public void the_api_endpoint_is(String url) {
        RestAssured.baseURI = url;
    }

    @When("I send a GET request to the API")
    public void i_send_a_get_request_to_the_api() {
        response = RestAssured.get();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
        int resStatusCode = response.getStatusCode();
        System.out.println("Status code :"+resStatusCode);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String expectedText) {
        response.then().body("title", equalTo(expectedText));
        String responseBody = response.getBody().asString();
        System.out.println("Body :"+responseBody);
    }
    @Then("the response should contain email {string}")
    public void the_response_should_contain_email(String expectedText) {

        String responseBody = response.getBody().asString();
        System.out.println("Body :"+responseBody);
        response.then().body("data", equalTo(expectedText));
    }
}

