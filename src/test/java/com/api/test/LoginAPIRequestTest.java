package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIRequestTest {

	@Test
	public void loginAPITest1() throws IOException {
		
		
		System.out.println(System.getProperty("env"));
		UserCredentials userCredentials =new UserCredentials("iamfd","password");
		
		
		given()
		.baseUri(getProperty("BASE_URI"))
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
