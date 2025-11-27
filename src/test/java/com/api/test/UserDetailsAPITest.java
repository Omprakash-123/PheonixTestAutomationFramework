package com.api.test;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import static com.api.utils.ConfigManager.*;

public class UserDetailsAPITest{

	@Test
	public void userDetailsAPITest() throws IOException {
	  
		Header authHeader= new Header("Authorization", getToken(ENG));
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header(authHeader)
		.and()
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(2000L))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));
	}
}
