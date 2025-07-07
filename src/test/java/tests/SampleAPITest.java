package tests;

import java.util.Iterator;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;
import utils.JsonReader;
import utils.LoggerUtil;
import utils.RequestSpecFactory;

@Listeners({ listeners.TestListener.class })
public class SampleAPITest extends BaseTest {

	@DataProvider(name = "authData")
	public Iterator<Object[]> authData() {
		return JsonReader.getTestData("src/test/resources/data/testdata_register_login.json");
	}

	@Test(dataProvider = "authData")
	public void testLogin(JSONObject payload) {
		LoggerUtil.info("Attempting login for: " + payload.toJSONString());

		Response response = RestAssured
									.given()
									.spec(RequestSpecFactory.getDefaultSpec())
									.body(payload.toJSONString())
									.log().all()
									.when()
									.post("/api/login");

		LoggerUtil.info("Response: " + response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 200);
		String token = response.jsonPath().getString("token");
		Assert.assertNotNull(token);
		LoggerUtil.pass("Login successful, token received: " + token);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateUser() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Jatin");
		requestBody.put("job", "QA Lead");

		Response response = RestAssured
								.given()
								.spec(RequestSpecFactory.getDefaultSpec())
								.body(requestBody.toJSONString())
								.when()
								.post("/api/users");

		LoggerUtil.info("Response: " + response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.jsonPath().get("name"), "Jatin");
		LoggerUtil.pass("User created successfully.");
	}

	@Test
	public void testUpdateUser() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Jatin Updated");
		requestBody.put("job", "QA Architect");

		Response response = RestAssured.given().spec(RequestSpecFactory.getDefaultSpec())
				.body(requestBody.toJSONString()).when().put("/api/users/2");

		LoggerUtil.info("Response: " + response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 200);
		LoggerUtil.pass("User updated successfully.");
	}

	@Test
	public void testDeleteUser() {
		Response response = RestAssured
									.given()
									.spec(RequestSpecFactory.getDefaultSpec())
									.when()
									.delete("/api/users/2");

		LoggerUtil.info("Response Status: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 204);
		LoggerUtil.pass("User deleted successfully.");
	}

}
