package com.project.crimetime.Classes;

import com.google.firebase.firestore.Exclude;

public class ComplainClass {
    String name;
    String complainNo;
    String complain;
    String status;
    String pin;
    String contact;
    String address;
    String date;

    public ComplainClass(String name, String date, String complain, String status, String pin, String contact, String address) {
        this.complainNo = complainNo;
        this.complain = complain;
        this.status = status;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.pin = pin;
        this.date = date;
    }

    public ComplainClass() {
    }

    @Exclude
    public String getComplainNo() {
        return complainNo;
    }

    public void setComplainNo(String complainNo)
    {
        this.complainNo = complainNo;
    }


    public String getName() {
        return name;
    }

    public String getComplain() {
        return complain;
    }

    public String getStatus() {
        return status;
    }

    public String getPin() {
        return pin;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


