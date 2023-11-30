package com.QA.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Booking{

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private BookingDates bookingdates;

    private String additionalneeds;

  
    public Booking() {
    }

    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid,
                          BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

     public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public static class BookingDates {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date checkin;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date checkout;


        public BookingDates() {
        }

      
        public BookingDates(Date checkin, Date checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }


        public Date getCheckin() {
            return checkin;
        }

        public void setCheckin(Date checkin) {
            this.checkin = checkin;
        }

        public Date getCheckout() {
            return checkout;
        }

        public void setCheckout(Date checkout) {
            this.checkout = checkout;
        }
    }
}
