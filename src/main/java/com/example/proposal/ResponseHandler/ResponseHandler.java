package com.example.proposal.ResponseHandler;

public class ResponseHandler<P> {
    private String status;
    private Object data;
    private String message;



    public ResponseHandler(String message, Object data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public ResponseHandler()
    {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
