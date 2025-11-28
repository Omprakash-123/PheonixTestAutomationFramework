package com.api.test;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.CreateJobPayLoad;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
	
	
	

	
    @Test
	public void createJobAPITest() throws IOException {
		
    	Customer cutomer=new Customer("Jatin","Sharma","8806144202","1234567890","sonawaneomprakash222@gmail.com","");
    	CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangar nagar", "Inorbit", "Mumbai", "411039", "India", "Maharastra");
    	CustomerProduct customerProduct =new CustomerProduct("2025-09-23T18:30:00.000", "11148664331396", "11148664331396", "11148664331396", "2025-09-23T18:30:00.000Z", 3, 3);
    	Problems problem=new Problems(1,"Battery Isssue");
    	Problems[] problemsArray= new Problems[1];
    	problemsArray[0]=problem;
    	
    	CreateJobPayLoad  createJobPayLoad=new CreateJobPayLoad(0, 2, 1, 2, cutomer, customerAddress, customerProduct, problemsArray);
    	
    	
    	given()
    	.spec(SpecUtil.requestSpecWithAuth(FD, createJobPayLoad))
    	.log().all()
        .when()
        .post("/job/create")
        .then()
        .spec(SpecUtil.responseSpec_OK());
	}
}
