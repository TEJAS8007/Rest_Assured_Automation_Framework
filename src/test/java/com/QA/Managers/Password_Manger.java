package com.QA.Managers;

import com.QA.Utilities.PropertyReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Password_Manger {
	
	public static String Init_Auth_Token() {
		
		Response response= RestAssured.given()
		                  .contentType(ContentType.JSON)
		                  .body(PropertyReader.Init_Properties().getProperty("body").trim())
		
		                  .when().post(PropertyReader.Init_Properties().getProperty("url").trim());
		
		JsonPath path= response.body().jsonPath();
		
		String token= path.getString("token");
		return token;
	}
	
	/*
	public static void main(String[] args) {
		String to=Init_Auth_Token();
		System.out.println(to);
	} 
	 */
}
