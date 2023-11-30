package com.QA.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.QA.Endpoints.Booking_Endpoints;
import com.QA.Managers.Password_Manger;
import com.QA.payloads.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class Booking_Test {

	Faker faker;
	ObjectMapper obj;
	Booking bookingPayload;
	Booking_Endpoints endpoints;
	public int Boking_Id;
	static Logger log;

	@BeforeClass
	public void Set_Up() {

		faker=new Faker();
		obj=new ObjectMapper();
		endpoints=new Booking_Endpoints();
		log=LogManager.getLogger(this.getClass());
		
		Booking.BookingDates bookingDates = new Booking.BookingDates();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date checkinDate = null;
		try {
			checkinDate = dateFormat.parse("2023-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date checkoutDate = null;
		try {
			checkoutDate = dateFormat.parse("2023-02-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		bookingDates.setCheckin(checkinDate);
		bookingDates.setCheckout(checkoutDate);
		
		bookingPayload = new Booking();
		
		bookingPayload.setFirstname(faker.name().firstName());
		bookingPayload.setLastname(faker.name().lastName());
		bookingPayload.setTotalprice(faker.number().numberBetween(111, 999));
		bookingPayload.setDepositpaid(true);
		bookingPayload.setBookingdates(bookingDates); // Set BookingDates instance
		bookingPayload.setAdditionalneeds("Breakfast");

		try {
			String jsonPayload= obj.writerWithDefaultPrettyPrinter().writeValueAsString(bookingPayload);
			System.out.println(jsonPayload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Booking_API_Test Started...");
	}

	@Test(priority = 1)
	public void Create_Booking_test() {
		
		log.info("Booking API Create User Test Started....");
		
        Response response= endpoints.Create_Booking(this.bookingPayload);
                           response.then().log().all();
        
                           log.info("Booking API Create User Test status Assertion passed....");
        AssertJUnit.assertTrue(response.statusLine().equals("HTTP/1.1 200 OK"));
        
        Boking_Id= response.body().jsonPath().getInt("bookingid");
        System.out.println(Boking_Id);
        log.info("Booking API Create User Test Ended....");
	}
	
	@Test(priority = 2)
	public void Get_Booking_Test() {
		log.info("Booking API Get User Test Started....");
		
		Response response= endpoints.Get_Booking(Boking_Id);
		                   response.then().log().all();
		                   log.info("Booking API Get User Test Assertion passed....");
		                   AssertJUnit.assertTrue(response.statusLine().equals("HTTP/1.1 200 OK"));
		                   
		          Headers headers=response.headers();
		          
		          for(Header header :headers) {
		        	  System.out.println(header.getName() +" : "+header.getValue());
		          }
		          
		String Body= response.body().asPrettyString();  
		MatcherAssert.assertThat(Body, JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/Schema_Validation/Booking.json")));
		log.info("Booking API Create User Test Ended....");
	}
	
	@Test(priority = 3)
	public void Update_Booking_Test() {
		log.info("Booking API Update User Test Started....");
		Response response= endpoints.Update_Booking
				                   (Boking_Id, bookingPayload, Password_Manger.Init_Auth_Token());
          
		                   response.then().log().all();
		                   log.info("Booking API Update User Test Ended....");
	}
	

	@Test(priority = 4)
	public void Delete_Booking_Test() {
		log.info("Booking API Delete User Test Started....");
		Response response= endpoints.Delete_Booking(Boking_Id, Password_Manger.Init_Auth_Token());
		                   response.then().log().all();
		                   log.info("Booking API Create User Test ended....");
	}
}
