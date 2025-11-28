package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.SpecUtil.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIRequestTest {

	@Test
	public void loginAPITest1() throws IOException {
		
		
		System.out.println(System.getProperty("env"));
		UserCredentials userCredentials =new UserCredentials("iamfd","password");
		
		
		given()
		.spec(SpecUtil.requestSpec(userCredentials))
		.when()
		.post("login")
		.then().spec(SpecUtil.responseSpec())
		.and()
		.body("message", equalTo("Success"))
		.log().body()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		.extract().response();
		
				
		
	}

}
