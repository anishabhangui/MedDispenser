package com.example.android.bluetoothchat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

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
    }

    public static Comparator<medinfo> MedTimeCompare = new Comparator<medinfo>() {
        @Override
        public int compare(medinfo m1, medinfo m2) {
            int comp = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                Date d1 = sdf.parse(m1.getmTime());
                Date d2 = sdf.parse(m2.getmTime());
                if(d1==d2){
                    comp=0;
                }
                else if(d1.after(d2)){
                    comp=1;
                }
                else
                   comp=-1;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return comp;

        }
    };

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
