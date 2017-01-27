package com.example.dellpc.parkingmanagement;

/**
 * Created by dell pc on 27-Jan-17.
 */

public class UserBookClass {
    String Username;
    String Slotno;
    String StartTime;
    String EndTime;

    public UserBookClass(String mUserName, String mSlotno, String mStarttime, String mEndTime){
        Username = mUserName;
        Slotno = mSlotno;
        StartTime = mStarttime;
        EndTime = mEndTime;
    }

    public String getUsername() {
        return Username;
    }

    public String getSlotno() {
        return Slotno;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setSlotno(String slotno) {
        Slotno = slotno;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
