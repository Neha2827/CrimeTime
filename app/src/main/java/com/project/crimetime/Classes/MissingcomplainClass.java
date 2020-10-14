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
    String complainNo;
    String status;
    public MissingcomplainClass(){

    }
    public MissingcomplainClass(String missingname, String missingage, String missingheight,
                                String missingskin, String missinghair, String missingtime, String missingplace,
                                String complainername, String complaineraddress, String complainerphone, String complainerpin,
                                String complaintdate, String image,String status,String complainNo){
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
        this.complainNo=complainNo;
        this.status=status;

        this.image=image;


    }
    public String getComplainNo(){return complainNo;}
    public void setComplainNo(String complainNo){
        this.complainNo=complainNo;
    }
    public void setMissingname(String missingname){
        this.missingname=missingname;

    }
    public void setMissingage(String missingage){
        this.missingage=missingage;
    }
    public void setMissingheight(String missingheight){
        this.missingheight=missingheight;
    }

    public void setMissinghair(String missinghair) {
        this.missinghair = missinghair;
    }

    public void setMissingskin(String missingskin) {
        this.missingskin = missingskin;
    }

    public void setMissingplace(String missingplace) {
        this.missingplace = missingplace;
    }

    public void setMissingtime(String missingtime) {
        this.missingtime = missingtime;
    }

    public void setComplainername(String complainername) {
        this.complainername = complainername;
    }

    public void setComplainerphone(String complainerphone) {
        this.complainerphone = complainerphone;
    }

    public void setComplainerpin(String complainerpin) {
        this.complainerpin = complainerpin;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){return status;}

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
