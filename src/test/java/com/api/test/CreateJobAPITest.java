package com.api.test;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayLoad;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
    @Test
	public void createJobAPITest() throws IOException {
		
    	Customer cutomer=new Customer("Jatin","Sharma","8806144202","1234567890","sonawaneomprakash222@gmail.com","");
    	CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangar nagar", "Inorbit", "Mumbai", "411039", "India", "Maharastra");
    	CustomerProduct customerProduct =new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "22138664331392", "22138664331392", "22138664331392", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
    	Problems problem=new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery Isssue");
    	List<Problems> problemList= new ArrayList<Problems>();
    	problemList.add(problem);
    	
    	CreateJobPayLoad  createJobPayLoad=new CreateJobPayLoad(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), cutomer, customerAddress, customerProduct, problemList);
    	
    	
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
