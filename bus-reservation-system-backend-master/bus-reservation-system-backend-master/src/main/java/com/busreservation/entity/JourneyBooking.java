package com.busreservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JourneyBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bookingId;
    
    private String bookingTime;
    
    private String status;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;
    
    private String journeyClass;
    
    @ManyToOne
    @JoinColumn(name = "busSeatNo")
    private BusSeatNo busSeatNo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJourneyClass() {
		return journeyClass;
	}

	public void setJourneyClass(String journeyClass) {
		this.journeyClass = journeyClass;
	}
	
	

	public User getPassenger() {
		return passenger;
	}

	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public BusSeatNo getBusSeatNo() {
		return busSeatNo;
	}

	public void setBusSeatNo(BusSeatNo busSeatNo) {
		this.busSeatNo = busSeatNo;
	}

	public JourneyBooking() {
		super();
	}

	public JourneyBooking(int id, String bookingId, String bookingTime, String status, String journeyClass) {
		super();
		this.id = id;
		this.bookingId = bookingId;
		this.bookingTime = bookingTime;
		this.status = status;
		this.journeyClass = journeyClass;
	}  

}
