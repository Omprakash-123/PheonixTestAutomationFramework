package com.api.utils;

import java.io.IOException;

import org.hamcrest.Matchers;

import static com.api.constant.Role.*;
import com.api.pojo.UserCredentials;

import static com.api.utils.ConfigManager.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
//only have static method!!
   
	
   //GET-DEL
   public static RequestSpecification requestSpec() throws IOException {
	   //to take care of the common request sections(methods)
	  RequestSpecification request=new RequestSpecBuilder()
	   .setBaseUri(getProperty("BASE_URI"))
	   .setContentType(ContentType.JSON)
	   .setAccept(ContentType.JSON)
	   .log(LogDetail.URI)
	   .log(LogDetail.METHOD)
	   .log(LogDetail.HEADERS)
	   .log(LogDetail.BODY)
	   .build();
	   return request;
   }
   
   public static RequestSpecification requestSpecWithAuth() throws IOException {
	   //to take care of the common request sections(methods)
	  RequestSpecification request=new RequestSpecBuilder()
	   .setBaseUri(getProperty("BASE_URI"))
	   .setContentType(ContentType.JSON)
	   .setAccept(ContentType.JSON)
	   .addHeader("Authorization", AuthTokenProvider.getToken(FD))
	   .log(LogDetail.URI)
	   .log(LogDetail.METHOD)
	   .log(LogDetail.HEADERS)
	   .log(LogDetail.BODY)
	   .build();
	   return request;
   }
   
   
   //POST-PUT-PATCH(Body)
   public static RequestSpecification requestSpec(Object payload) throws IOException {
	   //to take care of the common request sections(methods)
	  RequestSpecification request=new RequestSpecBuilder()
	   .setBaseUri(getProperty("BASE_URI"))
	   .setContentType(ContentType.JSON)
	   .setAccept(ContentType.JSON)
	   .setBody(payload)
	   .log(LogDetail.URI)
	   .log(LogDetail.METHOD)
	   .log(LogDetail.HEADERS)
	   .log(LogDetail.BODY)
	   .build();
	   return request;
   }
   
   public static ResponseSpecification responseSpec_OK() throws IOException {
	  
	   ResponseSpecification responseSpecification =new ResponseSpecBuilder()
			   .expectContentType(ContentType.JSON)
			   .expectStatusCode(200)
			   .expectResponseTime(Matchers.lessThan(1000l))
			   .log(LogDetail.ALL)
			   .build();
			  return responseSpecification;
   }
   
   public static ResponseSpecification responseSpec() {
	  ResponseSpecification responseSpecification =new ResponseSpecBuilder()
	   .expectContentType(ContentType.JSON)
	   .expectStatusCode(200)
	   .expectResponseTime(Matchers.lessThan(1000l))
	   .log(LogDetail.ALL)
	   .build();
	  return responseSpecification;
   } 
   
   public static ResponseSpecification responseSpec_JSON(int statusCode) {
		  ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		   .expectContentType(ContentType.JSON)
		   .expectStatusCode(statusCode)
		   .expectResponseTime(Matchers.lessThan(1000l))
		   .log(LogDetail.ALL)
		   .build();
		  return responseSpecification;
	   } 
   
   public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		  ResponseSpecification responseSpecification =new ResponseSpecBuilder()
		   .expectStatusCode(statusCode)
		   .expectResponseTime(Matchers.lessThan(1000l))
		   .log(LogDetail.ALL)
		   .build();
		  return responseSpecification;
	   } 
}
