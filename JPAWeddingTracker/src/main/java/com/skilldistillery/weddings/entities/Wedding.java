package com.skilldistillery.weddings.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Wedding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="booking_date")
	private Date bookingDate;
	
	@Column(name="celebration_date")
	private Date celebrationDate;
	
	@Column(name="up_lighting")
	private Integer upLighting;
	
	@Column(name="total_cost")
	private Double totalCost;
	
	@ManyToOne
	@JoinColumn(name="venue_id")
	private Venue venue;
	
	@ManyToMany(mappedBy="weddings")
	private List<DJ> djs;
	
	@OneToMany(mappedBy="wedding")
	private List<Client> clients;
	
	public Wedding() {
	}

	public Wedding(int id) {
		this.id = id;
	}



	public Wedding(Date bookingDate, Date celebrationDate, Integer upLighting, Double totalCost) {
		this.bookingDate = bookingDate;
		this.celebrationDate = celebrationDate;
		this.upLighting = upLighting;
		this.totalCost = totalCost;
	}

	public Wedding(Date bookingDate, Date celebrationDate, Integer upLighting, Double totalCost, Venue venue,
			List<DJ> djs, List<Client> clients) {
		this.bookingDate = bookingDate;
		this.celebrationDate = celebrationDate;
		this.upLighting = upLighting;
		this.totalCost = totalCost;
		this.venue = venue;
		this.djs = djs;
		this.clients = clients;
	}

	public void addDJ(DJ dj) {
		if (djs == null) {
			djs = new ArrayList<>();
		}
		if(!djs.contains(dj)) {
			djs.add(dj);
			dj.addWedding(this);
		}
	}
	
	public void removeDJ(DJ dj) {
		if (djs != null && djs.contains(dj)) {
			djs.remove(dj);
			dj.removeWedding(this);
		}
	}
	
	public void addClient(Client client) {
		if(clients == null) clients = new ArrayList<>();
		
		if(!clients.contains(client)) {
			clients.add(client);
			if(client.getWedding() != null) {
				client.getWedding().getClients().remove(client);
			}
			client.setWedding(this);
		}
	}
	
	public void removeFilm(Client client) {
		client.setWedding(null);
		if(clients != null) {
			clients.remove(client);
		}
	}
	
	public List<DJ> getDjs() {
		return djs;
	}

	public void setDjs(List<DJ> djs) {
		this.djs = djs;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getCelebrationDate() {
		return celebrationDate;
	}

	public void setCelebrationDate(Date celebrationDate) {
		this.celebrationDate = celebrationDate;
	}

	public Integer getUpLighting() {
		return upLighting;
	}

	public void setUpLighting(Integer upLighting) {
		this.upLighting = upLighting;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wedding other = (Wedding) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wedding [id=" + id + ", bookingDate=" + bookingDate + ", celebrationDate=" + celebrationDate
				+ ", upLighting=" + upLighting + ", totalCost=" + totalCost + ", venue=" + venue + ", djs=" + djs
				+ ", clients=" + clients + "]";
	}
}
