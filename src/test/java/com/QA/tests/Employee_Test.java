package com.QA.tests;




import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.QA.Endpoints.Employee_Endpoints;
import com.QA.payloads.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Employee_Test {

	Employee_Endpoints endpoints;
	Employee employee;
	Faker faker;
	ObjectMapper obj;
	public static int Response_id;
	static Logger log;

	@BeforeClass
	public void set_Up() {

		endpoints=new Employee_Endpoints();
		faker=new Faker();
		obj=new ObjectMapper();
		log=LogManager.getLogger(this.getClass());

		employee=new Employee();
		employee.setName(faker.name().firstName());
		employee.setSalary(faker.number().digits(5));
		employee.setAge(faker.number().digits(2));

		System.out.println("Employee----Payload......");

		try {
			String payload= obj.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
			System.out.println(payload);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log.debug("Employee_API_Test Started");
	}

	@Test(priority = 1)
	public void Create_Employee_Details_Test() {
		
		log.info("Employee_API_Create_employee_Test Started...");

		Response response= endpoints.Create_Employee_Records(this.employee);
		
		log.info("Employee_API_Create_employee_Test Assertion passed...");

		AssertJUnit.assertTrue(response.statusLine().equals("HTTP/1.1 200 OK"));

		response.then().log().all();

		JsonPath path= response.body().jsonPath();

		Response_id= path.getInt("data.id");
		System.out.println("Response id : "+Response_id);
		log.info("Employee_API_Create_employee_Test Ended...");
	}


	@Test(priority = 2)
	public void Get_Employee_Details_Test() {
		log.info("Employee_API_Get_employee_Test Started...");
		
		endpoints.Get_Employee_Records(Response_id).then().log().all();
		
		log.info("Employee_API_Get_employee_Test Started...");
	}

	@Test(priority = 3)
	public void Update_Employee_Details_Test() {
		
		log.info("Employee_API_Update_employee_Test Started...");
		endpoints.Update_Employee_Records(Response_id,this.employee.getName()).then().log().all();
		log.info("Employee_API_Update_employee_Test Started...");
	}

	@Test(priority = 4)
	public void Delete_Employee_Details_Test() {
		log.info("Employee_API_Delete_employee_Test Started...");
		endpoints.Delete_Employee_Records(Response_id).then().log().all();
		log.info("Employee_API_Delete_employee_Test Started...");
	}
}
