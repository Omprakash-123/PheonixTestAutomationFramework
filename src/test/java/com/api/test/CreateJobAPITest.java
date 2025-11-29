package com.api.test;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.request.model.CreateJobPayLoad;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	

	
    @Test
	public void createJobAPITest() throws IOException {
		
    	Customer cutomer=new Customer("Jatin","Sharma","8806144202","1234567890","sonawaneomprakash222@gmail.com","");
    	CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangar nagar", "Inorbit", "Mumbai", "411039", "India", "Maharastra");
    	CustomerProduct customerProduct =new CustomerProduct("2025-09-23T18:30:00.000", "22138664331392", "22138664331392", "22138664331392", "2025-09-23T18:30:00.000Z", 3, 3);
    	Problems problem=new Problems(1,"Battery Isssue");
    	List<Problems> problemList= new ArrayList<Problems>();
    	problemList.add(problem);
    	
    	CreateJobPayLoad  createJobPayLoad=new CreateJobPayLoad(0, 2, 1, 2, cutomer, customerAddress, customerProduct, problemList);
    	
    	
    	given()
    	.spec(SpecUtil.requestSpecWithAuth(FD, createJobPayLoad))
    	.log().all()
        .when()
        .post("/job/create")
        .then()
        .spec(SpecUtil.responseSpec_OK())
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
        .body("message", Matchers.equalTo("Job created successfully. "))
        .body("data.mst_service_location_id", Matchers.equalTo(1))
        .body("data.mst_warrenty_status_id", Matchers.equalTo(1))
        .body("data.job_number", Matchers.startsWith("JOB_"));
	}
}
