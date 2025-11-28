package com.api.test;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import static com.api.utils.ConfigManager.*;

public class UserDetailsAPITest{

	@Test
	public void userDetailsAPITest() throws IOException {
	  
		
		given()
		.spec(SpecUtil.requestSpecWithAuth())
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));
	}
}
