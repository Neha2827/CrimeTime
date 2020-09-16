package com.project.crimetime.Classes;

public class ComplainClass {
    String name;
    String complain;
    String status;
    String date;
    String pinCode;
    String address;
    String contact;

    public ComplainClass(String name,String contact, String complain, String status,String pinCode,String date,String address) {

        this.complain = complain;
        this.status = status;
        this.name=name;
        this.pinCode=pinCode;
        this.date=date;
        this.address=address;
        this.contact=contact;
    }

    public ComplainClass() {
    }


    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String  contact) {
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
