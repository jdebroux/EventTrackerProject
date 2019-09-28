package com.skilldistillery.weddings.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String address;
	
	@Column(name="contact_first_name")
	private String contactFirstName;

	@Column(name="contact_last_name")
	private String contactLastName;
	
	private Long phone;
	
	@JsonIgnore
	@OneToMany(mappedBy="venue")
	private List<Wedding> weddings;
	
	public Venue() {
	}

	public Venue(String name, String address, String contactFirstName, String contactLastName, Long phone) {
		this.name = name;
		this.address = address;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.phone = phone;
	}
	
	public void addWedding(Wedding wedding) {
		if(weddings == null) weddings = new ArrayList<>();
		
		if(!weddings.contains(wedding)) {
			weddings.add(wedding);
			if(wedding.getVenue() != null) {
				wedding.getVenue().getWeddings().remove(wedding);
			}
			wedding.setVenue(this);
		}
	}
	
	public void removeWedding(Wedding wedding) {
		wedding.setVenue(null);
		if(weddings != null) {
			weddings.remove(wedding);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public List<Wedding> getWeddings() {
		return new ArrayList<>(weddings);
	}

	public void setWeddings(List<Wedding> weddings) {
		this.weddings = weddings;
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
		Venue other = (Venue) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", address=" + address + ", contactFirstName=" + contactFirstName
				+ ", contactLastName=" + contactLastName + ", phone=" + phone + "]";
	}
}
