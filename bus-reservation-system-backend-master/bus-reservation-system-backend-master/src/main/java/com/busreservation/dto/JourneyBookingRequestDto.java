package com.busreservation.dto;

import lombok.Data;

@Data
public class JourneyBookingRequestDto {
	
    private int totalPassengers;
    
    private String journeyClassType;
    
    private int passengerId;

    private int journeyId;

	public int getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

	public String getJourneyClassType() {
		return journeyClassType;
	}

	public void setJourneyClassType(String journeyClassType) {
		this.journeyClassType = journeyClassType;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

	public JourneyBookingRequestDto() {
		super();
	}

	public JourneyBookingRequestDto(int totalPassengers, String journeyClassType, int passengerId, int journeyId) {
		super();
		this.totalPassengers = totalPassengers;
		this.journeyClassType = journeyClassType;
		this.passengerId = passengerId;
		this.journeyId = journeyId;
	}

}
