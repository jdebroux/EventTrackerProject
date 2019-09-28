package com.skilldistillery.weddings.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DJ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="pay_per_gig")
	private Integer payPerGig;
	
	private String email;
	
	private Long phone;
	
	@JsonIgnore
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name="dj_wedding", joinColumns=@JoinColumn(name="dj_id"),
	inverseJoinColumns=@JoinColumn(name="wedding_id"))
	private List<Wedding> weddings;
	
	public DJ() {
	}
	
	public DJ(String firstName, String lastName, Integer payPerGig, String email, Long phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.payPerGig = payPerGig;
		this.email = email;
		this.phone = phone;
	}

	public void addWedding(Wedding wedding) {
		if(weddings == null) {
			weddings = new ArrayList<>();
		}
		if(!weddings.contains(wedding)) {
			weddings.add(wedding);
			wedding.addDJ(this);
		}
	}
	
	public void removeWedding(Wedding wedding) {
		if(weddings != null && weddings.contains(wedding)) {
			weddings.remove(wedding);
			wedding.removeDJ(this);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getPayPerGig() {
		return payPerGig;
	}

	public void setPayPerGig(Integer payPerGig) {
		this.payPerGig = payPerGig;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DJ other = (DJ) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DJ [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", payPerGig=" + payPerGig
				+ ", email=" + email + ", phone=" + phone + "]";
	}
}
