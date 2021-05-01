package com.example.android.bluetoothchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends Activity {
    private Button addmedbtn;
    private Button medschedulebtn;
    private Button bluetoothbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        addmedbtn = findViewById(R.id.addmedbutton);
        addmedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this,AddMed.class);
                startActivity(intent);
            }
        });

        medschedulebtn = findViewById(R.id.medschedule);
        medschedulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this,MedicationSchedule.class);
                startActivity(intent);
            }
        });
        bluetoothbtn = findViewById(R.id.bluetooth);
        bluetoothbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePage.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}