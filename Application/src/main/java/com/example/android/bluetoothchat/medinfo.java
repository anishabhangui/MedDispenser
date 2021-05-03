package com.example.android.bluetoothchat;

public class medinfo {
    private String mMedName;
    private String mTime;
    private String mNumbPills;
    private String mBinNumb;
    private boolean mNotificationShow;


    public medinfo(String nameofmed, String timeformed, String numberpills, String binnumb){
        mMedName = nameofmed;
        mTime = timeformed;
        mNumbPills = numberpills;
        mBinNumb = binnumb;
        //mNotificationShow = true;


    }

    public String getmMedName() {
        return mMedName;
    }

    public void setmMedName(String mMedName) {
        this.mMedName = mMedName;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmNumbPills() {
        return mNumbPills;
    }

    public void setmNumbPills(String mNumbPills) {
        this.mNumbPills = mNumbPills;
    }

    public String getmBinNumb() {return mBinNumb;}

    public void setmBinNumb(String mBinNumb) { this.mBinNumb = mBinNumb; }


    /*public boolean getNotificationShow(){return mNotificationShow;}

    public void setNotificationShow(boolean show){this.mNotificationShow=show;}*/
}
