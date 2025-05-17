package com.example.proposal.dto;


public class ProposerDto {
    
    private String name;
    private String gender;
    private String dateOfBirth;
    private String annualincome;
    private String panNumber;
    private String aadharnumber;
    private String maritalstatus;
    private String email;
    private String phoneNumber;
    private String address;
    private String pincode;
    private String city;
    private String state;
    private Character status;

 
    public ProposerDto() {
    }

    public ProposerDto(String name, String gender, String dateOfBirth, String annualincome,
                       String panNumber, String aadharnumber, String maritalstatus,
                       String email, String phoneNumber, String address, String pincode,
                       String city, String state, Character status) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}
