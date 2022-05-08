package com.example.rfid_mobile;

public class RentalClass {
    public String name;
    public String startDate;
    public String endDate;
    public int objectID;

    RentalClass(String name, String startDate, String endDate, int objectID){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objectID = objectID;
    }


}
