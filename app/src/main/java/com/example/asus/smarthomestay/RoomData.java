package com.example.asus.smarthomestay;

public class RoomData {

private boolean status1;
private boolean status2;


    public RoomData() {

    }

    public RoomData(boolean status1, boolean status2) {
        this.status1 = status1;
        this.status2 = status2;
    }

    public boolean isStatus1() {
        return status1;
    }

    public void setStatus1(boolean status1) {
        this.status1 = status1;
    }

    public boolean isStatus2() {
        return status2;
    }

    public void setStatus2(boolean status2) {
        this.status2 = status2;
    }



}
