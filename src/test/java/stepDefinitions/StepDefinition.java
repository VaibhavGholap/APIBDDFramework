package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefinition extends Utils {
	

	ResponseSpecification Resp;
	RequestSpecification res;
	Response responce;
	TestDataBuild data=new TestDataBuild();
	
	@Given("Add Place Payload {string},{string},{string}")
	public void add_place_payload(String name, String language, String address) throws IOException  {
		
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		
		
		  res = given().log().all().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	@When("user call {string} with Post http request")
	public void user_call_with_post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		 Resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 responce = res.when().post("/maps/api/place/add/json").then().spec(Resp).extract().response();
	}
	@Then("the API call got success with status status code {int}")
	public void the_api_call_got_success_with_status_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(responce.getStatusCode(),200);  // inport static package (import static org.junit.Assert.*;)
	    
	}
	@Then("{string} in responce is {string}")
	public void in_responce_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	   String resp= responce.asString();
	   JsonPath js = new JsonPath(resp);
	   assertEquals(js.get(keyValue).toString(),Expectedvalue);
	}

	

}
