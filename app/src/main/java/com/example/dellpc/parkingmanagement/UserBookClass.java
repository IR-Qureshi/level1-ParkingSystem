package com.example.dellpc.parkingmanagement;

/**
 * Created by dell pc on 27-Jan-17.
 */

public class UserBookClass {
    String username;
    String slotno;
    String startTime;
    String endTime;

    public UserBookClass(){

    }

    public UserBookClass(String username, String slotno, String startTime, String endTime){
        this.username = username;
        this.slotno = slotno;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public String getSlotno() {
        return slotno;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    //    public void setUsername(String username) {
//        Username = username;
//    }
//
//    public void setSlotno(String slotno) {
//        Slotno = slotno;
//    }
//
//    public void setStartTime(String startTime) {
//        StartTime = startTime;
//    }
//
//    public void setEndTime(String endTime) {
//        EndTime = endTime;
//    }
}
