package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.ConfigManager.*;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() throws IOException {
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(Role.FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.time(lessThan(1000L))
		.body("data",notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
		
		}
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.when()
		.get("/dashboard/count")
		.then()
		.statusCode(401);
	}
	


}
