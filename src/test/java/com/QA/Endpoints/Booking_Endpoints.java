package com.QA.Endpoints;

import com.QA.Routes.Routes;
import com.QA.payloads.Booking;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Booking_Endpoints {

	public Response Create_Booking(Booking booking_Payload) {

		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.body(booking_Payload)

				.when().post(Routes.Create_Booking_Url);

		return response;
	}

	public Response Get_Booking(int BookingId) {

		Response response=RestAssured.given()
				
				.when().get(Routes.Get_Booking_Url+BookingId);

		return response;
	}
	
	public Response Update_Booking(int BookingId,Booking Booking_PAyload,String Cookie) {

		Response response=RestAssured.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.cookie("Cookie", Cookie)
				.body(Booking_PAyload)
				
				.when().put(Routes.Update_Booking_Url+BookingId);

		return response;
	}
	
	public Response Delete_Booking(int BookingId,String Cookie) {

		Response response=RestAssured.given()
				.contentType(ContentType.JSON)
				.cookie("Cookie", Cookie)
				
				.when().put(Routes.Delete_Booking_Url+BookingId);

		return response;
	}
}
