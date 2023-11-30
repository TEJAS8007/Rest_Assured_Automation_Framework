package com.QA.Endpoints;


import io.restassured.http.ContentType;
import io.restassured.response.Response;


import  io.restassured.RestAssured;
import com.QA.Routes.Routes;
import com.QA.payloads.Employee;


public class Employee_Endpoints {

	public Response Create_Employee_Records(Employee employee_payload) {

		Response response= RestAssured.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(employee_payload)

				.when().post(Routes.Create_Employee_Url);

		return response;
	}

	public Response Get_Employee_Records(int Employee_id) {

		Response response= RestAssured.given()
				.accept(ContentType.JSON)
				
				.when().get(Routes.Get_Employee_Url+Employee_id);

		return response;
	}
	
	public Response Update_Employee_Records(int Employee_id,String UserName) {

		Response response= RestAssured.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(UserName)
				
				.when().put(Routes.Update_Employee_url+Employee_id);

		return response;
	}
	
	public Response Delete_Employee_Records(int Employee_id) {

		Response response= RestAssured.given()
				.accept(ContentType.JSON)
				
				
				.when().delete(Routes.Delete_Employee_url+Employee_id);

		return response;
	}
}
