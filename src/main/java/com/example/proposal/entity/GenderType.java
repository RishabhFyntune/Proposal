package com.example.proposal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Gender_Type")
public class GenderType
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private int genderId;


    @Column(name = "gender_type")
    private String genderType;

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getGenderID() {
        return genderId;
    }



    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }
}
