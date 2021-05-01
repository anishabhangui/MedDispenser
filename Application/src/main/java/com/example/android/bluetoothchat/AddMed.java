package com.example.android.bluetoothchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Arrays;
import java.util.Calendar;

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
    int[] dayofweek = new int[]{0, 0, 0, 0, 0, 0, 0};

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
                    //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
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
                            if ((hour >= 1) && (hour <= 11.59)) {
                                am_pm = "AM";
                            } else if (hour >= 12) {
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
                String mednamestring = medname.getText().toString();
                String timestring = medtime.getText().toString();
                String numbpillstring = numberofpills.getText().toString();
                String binnumb = binnumber.getText().toString();
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


                //Intent toAdapter = new Intent(getApplicationContext(),MainActivity.class);

            }
        });
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