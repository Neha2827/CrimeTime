package com.project.crimetime.Classes;

public class MissingcomplainClass {
    String missingname;
    String missingage;
    String missingheight;
    String missingskin;
    String missinghair;
    String missingtime;
    String missingplace;
    String complainername;
    String complainerphone;
    String complaineraddress;
    String complainerpin;
    String complaintdate;
    String image;
    public MissingcomplainClass(){

    }
    public MissingcomplainClass(String missingname, String missingage, String missingheight,
                                String missingskin, String missinghair, String missingtime, String missingplace,
                                String complainername, String complaineraddress, String complainerphone, String complainerpin,
                                String complaintdate, String image){
        this.missingname=missingname;
        this.missingage=missingage;
        this.missingheight=missingheight;
        this.missingskin=missingskin;
        this.missinghair=missinghair;
        this.missingtime=missingtime;
        this.missingplace=missingplace;
        this.complainername=complainername;
        this.complaineraddress=complaineraddress;
        this.complainerphone=complainerphone;
        this.complainerpin=complainerpin;
        this.complaintdate=complaintdate;
        this.image=image;


    }

    public String getMissingname() {
        return missingname;
    }

    public String getMissingage() {
        return missingage;
    }

    public String getMissingheight() {
        return missingheight;
    }

    public String getMissingskin() {
        return missingskin;
    }

    public String getMissinghair() {
        return missinghair;
    }

    public String getMissingplace() {
        return missingplace;
    }

    public String getMissingtime() {
        return missingtime;
    }

    public String getComplainername() {
        return complainername;
    }

    public String getComplainerphone() {
        return complainerphone;
    }

    public String getComplaineraddress() {
        return complaineraddress;

    }

    public String getComplainerpin() {
        return complainerpin;
    }

    public String getComplaintdate() {
        return complaintdate;
    }

    public String getImage() {
        return image;
    }
}
