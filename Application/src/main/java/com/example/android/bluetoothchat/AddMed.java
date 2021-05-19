package com.example.android.bluetoothchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMed extends Activity {
    private EditText medname;
    private EditText medtime;
    private EditText numberofpills;
    private EditText binnumber;
    private TimePickerDialog timepicker;
    private Button btnsubmit;
    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;
    private Button saturday;
    private Button sunday;
    private String am_pm;
    private Calendar c = Calendar.getInstance();
    int[] dayofweek = new int[]{0, 0, 0, 0, 0, 0, 0};
    public static String mednamestring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        medname = findViewById(R.id.mednameinput);
        medtime = findViewById(R.id.timeinput);
        numberofpills = findViewById(R.id.numbpillinput);
        binnumber = findViewById(R.id.binnumbinput);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);

        medname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        medtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                if (hasFocus) {
                    final Calendar cldr = Calendar.getInstance();
                    int hour = cldr.get(Calendar.HOUR_OF_DAY);
                    int minutes = cldr.get(Calendar.MINUTE);
                    // time picker dialog
                    timepicker = new TimePickerDialog(AddMed.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int hour, int minute) {
                          //Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, hour);
                            c.set(Calendar.MINUTE,minute);
                            c.set(Calendar.SECOND,0);
                            startAlarm(c);
                            if ((hour >= 1) && (hour <= 11.59)) {
                                am_pm = "AM";
                            } else if (hour > 12) {
                                hour = hour - 12;
                                am_pm = "PM";
                            } else if (hour == 0) {
                                hour = 12;
                                am_pm = "AM";
                            }
                            if (hour < 10) {
                                medtime.setText("0" + hour + ":" + minute + " " + am_pm);
                            }
                            if (minute < 10) {
                                medtime.setText(hour + ":" + "0" + minute + " " + am_pm);
                            } else {
                                medtime.setText(hour + ":" + minute + " " + am_pm);
                            }
                        }
                    }, hour, minutes, false);
                    timepicker.show();
                }
            }
        });

        numberofpills.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        binnumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(AddMed.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        monday.setOnClickListener(new Clik());
        tuesday.setOnClickListener(new Clik());
        wednesday.setOnClickListener(new Clik());
        thursday.setOnClickListener(new Clik());
        friday.setOnClickListener(new Clik());
        saturday.setOnClickListener(new Clik());
        sunday.setOnClickListener(new Clik());


        btnsubmit = findViewById(R.id.addbutton);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mednamestring = medname.getText().toString();
                String timestring = medtime.getText().toString();
                String numbpillstring = numberofpills.getText().toString();
                String binnumb = binnumber.getText().toString();
                if(dayofweek[0]==1){
                    forDay(2);
                }
                if(dayofweek[1]==1){
                    forDay(3);
                }
                if(dayofweek[2]==1){
                    forDay(4);
                }
                if(dayofweek[3]==1){
                    forDay(5);
                }
                if(dayofweek[4]==1){
                    forDay(6);
                }
                if(dayofweek[5]==1){
                    forDay(7);
                }
                if(dayofweek[6]==1){
                    forDay(1);
                }
                startAlarm(c);
                //pass user information to medlist activity
                Intent toMedList = new Intent(getApplicationContext(), MedicationSchedule.class);
                toMedList.putExtra("medItem", mednamestring);
                toMedList.putExtra("timeformed", timestring);
                toMedList.putExtra("numberofpills", numbpillstring);
                toMedList.putExtra("daysofweek", dayofweek);
                toMedList.putExtra("binnumber",binnumb);
                startActivity(toMedList);
                finish();
                Log.d("dayofweek", "Med Info" + Arrays.toString(dayofweek));

            }
        });
    }

    public void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE,1);
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),1 * 60 * 60 * 1000,pendingIntent);
    }


    public void forDay(int week){
        c.set(Calendar.DAY_OF_WEEK,week);
    }

    public class Clik implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.monday:
                    monday.setSelected(!monday.isSelected());
                    if (dayofweek[0] == 0) {
                        dayofweek[0] = 1;
                        monday.setBackgroundColor(getResources().getColor(R.color.button_click));
                    } else {
                        dayofweek[0] = 0;
                        monday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
                case R.id.tuesday:
                    tuesday.setSelected(!tuesday.isSelected());
                    if (dayofweek[1] == 0) {
                        dayofweek[1] = 1;
                        tuesday.setBackgroundColor(getResources().getColor(R.color.button_click));

                    } else {
                        dayofweek[1] = 0;
                        tuesday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
                case R.id.wednesday:
                    wednesday.setSelected(!wednesday.isSelected());
                    if (dayofweek[2] == 0) {
                        dayofweek[2] = 1;
                        wednesday.setBackgroundColor(getResources().getColor(R.color.button_click));
                    } else {
                        dayofweek[2] = 0;
                        wednesday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
                case R.id.thursday:
                    thursday.setSelected(!thursday.isSelected());
                    if (dayofweek[3] == 0) {
                        dayofweek[3] = 1;
                        thursday.setBackgroundColor(getResources().getColor(R.color.button_click));
                    } else {
                        dayofweek[3] = 0;
                        thursday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
                case R.id.friday:
                    friday.setSelected(!friday.isSelected());
                    if (dayofweek[4] == 0) {
                        dayofweek[4] = 1;
                        friday.setBackgroundColor(getResources().getColor(R.color.button_click));
                    } else {
                        dayofweek[4] = 0;
                        friday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
                case R.id.saturday:
                    saturday.setSelected(!saturday.isSelected());
                    if (dayofweek[5] == 0) {
                        dayofweek[5] = 1;
                        saturday.setBackgroundColor(getResources().getColor(R.color.button_click));
                    } else {
                        dayofweek[5] = 0;
                        saturday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
                case R.id.sunday:
                    sunday.setSelected(!sunday.isSelected());
                    if (dayofweek[6] == 0) {
                        dayofweek[6] = 1;
                        sunday.setBackgroundColor(getResources().getColor(R.color.button_click));
                    } else {
                        dayofweek[6] = 0;
                        sunday.setBackgroundColor(getResources().getColor(R.color.button_unclick));
                    }
                    break;
            }
        }

    }
}
