package com.example.android.bluetoothchat;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DayHeader {
    public String dayName;
    public ArrayList<medinfo> mMedItems;
    private boolean mNotificationShow;


    public DayHeader(String nameofday, ArrayList<medinfo> meditems){
        this.dayName = nameofday;
        this.mMedItems = meditems;
        mNotificationShow = true;
    }

    public String getDayName(){ return dayName;}

    private void setDayName(){this.dayName=dayName;}

    public ArrayList<medinfo> getmMedItems() {
        return mMedItems;
    }

    public void setmMedItems(ArrayList<medinfo> mMedItems) {
        this.mMedItems = mMedItems;
    }

    public boolean getNotificationShow(){return mNotificationShow;}

    public void setNotificationShow(boolean show){this.mNotificationShow=show;}

}
