package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

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
		.spec(SpecUtil.requestSpecWithAuth())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
		
		}
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
	


}
