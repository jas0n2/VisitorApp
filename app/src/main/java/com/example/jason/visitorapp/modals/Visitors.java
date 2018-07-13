package com.example.jason.visitorapp.modals;

public class Visitors {

    private String name;
    private String email;
    private String phonenumber;
    private String visitee;
    private String reason;
    private String locationType;
    private String locationAddress;

    public Visitors(String name, String email, String phonenumber, String visitee, String reason, String locationType, String locationAddress) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.visitee = visitee;
        this.reason = reason;
        this.locationType = locationType;
        this.locationAddress = locationAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getVisitee() {
        return visitee;
    }

    public void setVisitee(String visitee) {
        this.visitee = visitee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
