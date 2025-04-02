package com.example.proposal.DTO;

public class ProposerDTO {
    
    private String name;
    private String gender;
    private String dateofbirth;
    private String annualincome;
    private String pannumber;
    private String aadharnumber;
    private String maritalstatus;
    private String email;
    private String phonenumber;
    private String address;
    private long pincode;
    private String city;
    private String state;
    private Character status;

 
    public ProposerDTO() {
    }

    public ProposerDTO(String name, String gender, String dateofbirth, String annualincome, 
                       String pannumber, String aadharnumber, String maritalstatus, 
                       String email, String phonenumber, String address, long pincode, 
                       String city, String state, Character status) {
        this.name = name;
        this.gender = gender;
        this.dateofbirth = dateofbirth;
        this.annualincome = annualincome;
        this.pannumber = pannumber;
        this.aadharnumber = aadharnumber;
        this.maritalstatus = maritalstatus;
        this.email = email;
        this.phonenumber = phonenumber;
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

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getAnnualincome() {
        return annualincome;
    }

    public void setAnnualincome(String annualincome) {
        this.annualincome = annualincome;
    }

    public String getPannumber() {
        return pannumber;
    }

    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
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
