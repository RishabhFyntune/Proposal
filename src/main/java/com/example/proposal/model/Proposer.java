package com.example.proposal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "proposer")
public class Proposer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "full_name")
	private String name;

	@Column(name = "gender")
	private String gender;


	@Column(name = "gender_id")
	private int genderId;

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "annual_income")
	private String annualincome;

	@Column(name = "pan_number")
	private String panNumber;

	@Column(name = "aadhar_number")
	private String aadharnumber;

	@Column(name = "martial_status")
	private String maritalstatus;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "pin_code")
	private String pincode;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "status")
	private Character status ;

	@Column(name = "created-At")
	@CreationTimestamp
	private String createdAt;

	@Column(name = "updated-At")
	@UpdateTimestamp
	private String updatedAt;


	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public Proposer(Long id, String name, String gender, String dateOfBirth, String annualincome, String panNumber,
					String aadharnumber, String maritalstatus, String email, String phoneNumber, String address, String pincode,
					String city, String state, Character status) {
		super();
		Id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.annualincome = annualincome;
		this.panNumber = panNumber;
		this.aadharnumber = aadharnumber;
		this.maritalstatus = maritalstatus;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.status = status;
	}

	public Proposer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(String annualincome) {
		this.annualincome = annualincome;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phonenumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	
	 public Character getStatus() 
	 { 
		 return status; 
	 }
	  
	  
	 
	  public void setStatus(Character status) 
	  { 
		  this.status = status; 
	  }



}
