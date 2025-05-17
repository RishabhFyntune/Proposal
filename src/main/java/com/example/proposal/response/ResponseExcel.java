package com.example.proposal.response;


import jakarta.persistence.*;

@Entity
@Table(name = "response_excel_table")
public class ResponseExcel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status;
    private String error ;
    private String field;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseExcel() {
    }

    public ResponseExcel(Long id, Boolean status, String error, String field) {
        this.id = id;
        this.status = status;
        this.error = error;
        this.field = field;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
