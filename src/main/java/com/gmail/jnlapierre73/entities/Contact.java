package com.gmail.jnlapierre73.entities;

import com.gmail.jnlapierre73.interfaces.Duplicate;
import com.gmail.jnlapierre73.utilities.LevenshteinDistance;

public class Contact implements Duplicate<Contact> {

	private static final int DISTANCE_THRESHOLD = 3;
	
	
	private int id; // Used as a key in the Map storing potential duplicates together
	private String hiddenId; // Human readable "cheat" to determine if it is a duplicate
	private String firstName;
	private String lastName;
	private String company;
	private String email;
	private String address1;
	private String address2;
	private String zip;
	private String city;
	private String stateLong;
	private String state;
	private String phone;
	
	public Contact() {		
	}
	
	public boolean isDuplicate(Contact contact) {
		int distance = 0;
		
		// Gather all the Levenshtein distances
		distance += LevenshteinDistance.distance(this.getFirstName(), contact.getFirstName());
		distance += LevenshteinDistance.distance(this.getLastName(), contact.getLastName());
		distance += LevenshteinDistance.distance(this.getCompany(), contact.getCompany());
		distance += LevenshteinDistance.distance(this.getEmail(), contact.getEmail());
		distance += LevenshteinDistance.distance(this.getAddress1(), contact.getAddress1());
		distance += LevenshteinDistance.distance(this.getAddress2(), contact.getAddress2());
		distance += LevenshteinDistance.distance(this.getZip(), contact.getZip());
		distance += LevenshteinDistance.distance(this.getCity(), contact.getCity());
		distance += LevenshteinDistance.distance(this.getStateLong(), contact.getStateLong());
		distance += LevenshteinDistance.distance(this.getState(), contact.getState());
		distance += LevenshteinDistance.distance(this.getPhone(), contact.getPhone());
		
		// Average them
		distance = distance/11;
		//System.out.println("Average distance: " + distance);
		
		return distance <= DISTANCE_THRESHOLD;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(String hiddenId) {
		this.hiddenId = hiddenId;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hiddenId == null) ? 0 : hiddenId.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		Contact other = (Contact) obj;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hiddenId == null) {
			if (other.hiddenId != null)
				return false;
		} else if (!hiddenId.equals(other.hiddenId))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", hiddenId=" + hiddenId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", company=" + company + ", email=" + email + ", address1=" + address1 + ", address2=" + address2
				+ ", zip=" + zip + ", city=" + city + ", state=" + state + ", phone=" + phone + "]";
	}

	public String getStateLong() {
		return stateLong;
	}

	public void setStateLong(String stateLong) {
		this.stateLong = stateLong;
	}
}
