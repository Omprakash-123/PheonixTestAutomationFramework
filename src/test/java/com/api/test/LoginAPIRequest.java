package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIRequest {

	@Test
	public void loginAPITest() {
		
		UserCredentials userCredentials =new UserCredentials("iamfd","password");
		
		
		given()
		.baseUri("http://64.227.160.186:9000/v1")
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(userCredentials)
		.log().uri()
		.log().headers()
		.log().method()
		.log().body()
		.when()
		.post("login")
		.then()
		.statusCode(200)
		.time(lessThan(2000L))
		.and()
		.body("message", equalTo("Success"))
		.log().body()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		.extract().response();
		
				
		
	}

}
