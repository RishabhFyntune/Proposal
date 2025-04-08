package com.example.proposal.ResponseHandler;

public class ResponseHandler<Y> {
    private boolean status;
    private Object data;
    private String message;
    private long totalRecord;

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }



    public ResponseHandler(String message, Object data, boolean status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public ResponseHandler()
    {
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
