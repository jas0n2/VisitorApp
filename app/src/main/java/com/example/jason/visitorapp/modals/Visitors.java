package com.example.jason.visitorapp.modals;

public class Visitors {

    private String name;
    private String visitee;
    private String reason;
    private String locationType;
    private String locationAddress;
private String timeIn,timeOut,date;
    public Visitors(String name, String visitee, String reason, String locationType, String locationAddress, String timeIn, String timeOut, String date) {
        this.name = name;
        this.visitee = visitee;
        this.reason = reason;
        this.locationType = locationType;
        this.locationAddress = locationAddress;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
