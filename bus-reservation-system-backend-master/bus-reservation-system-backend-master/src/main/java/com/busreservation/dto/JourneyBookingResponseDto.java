package com.busreservation.dto;

import java.util.ArrayList;
import java.util.List;

import com.busreservation.entity.JourneyBooking;

import lombok.Data;

@Data
public class JourneyBookingResponseDto extends CommonApiResponse {
	
	private List<JourneyBooking> bookings = new ArrayList<>();

	public List<JourneyBooking> getBookings() {
		return bookings;
	}

	public void setBookings(List<JourneyBooking> bookings) {
		this.bookings = bookings;
	}

	public JourneyBookingResponseDto() {
		super();
	}

	public JourneyBookingResponseDto(List<JourneyBooking> bookings) {
		super();
		this.bookings = bookings;
	}
}