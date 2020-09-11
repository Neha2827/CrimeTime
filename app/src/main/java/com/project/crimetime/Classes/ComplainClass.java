package com.project.crimetime.Classes;

public class ComplainClass {
    String name;
    String complainNo;
    String complain;
    String status;
    String name;

    public ComplainClass(String complainNo, String complain, String status) {
        this.complainNo = complainNo;
        this.complain = complain;
        this.status = status;
    }

    public ComplainClass() {
    }

    public String getComplainNo() {
        return complainNo;
    }

    public void setComplainNo(String complainNo) {
        this.complainNo = complainNo;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
