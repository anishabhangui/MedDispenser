package com.example.android.bluetoothchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicationSchedule extends Activity  {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private MedicationAdapter medicationAdapter;
    //private MainRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<medinfo> mMedInfoList = new ArrayList<medinfo>();
    private ArrayList<DayHeader> daysofweekinput = new ArrayList<DayHeader>();
    private ArrayList<medinfo> mondayitems = new ArrayList<>();
    private ArrayList<medinfo> tuesdayitems = new ArrayList<>();
    private ArrayList<medinfo> wednesdayitems = new ArrayList<>();
    private ArrayList<medinfo> thursdayitems = new ArrayList<>();
    private ArrayList<medinfo> fridayitems = new ArrayList<>();
    private ArrayList<medinfo> saturdayitems = new ArrayList<>();
    private ArrayList<medinfo> sundayitems = new ArrayList<>();
    private ImageButton btnaddmed, btnhome, btnclear;
    private String med;
    private String time;
    private String pillnumber;
    private String binnumber;
    private String dw;
    private int[] dayofweek;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_schedule);
        itemTouchHelper = new ItemTouchHelper(new SwipeItem(medicationAdapter));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            med = bundle.getString("medItem");
            time = bundle.getString("timeformed");
            pillnumber = bundle.getString("numberofpills");
            dayofweek = bundle.getIntArray("daysofweek");
            binnumber = bundle.getString("binnumber");
            dw = Arrays.toString(dayofweek);
            Log.d("dw array", dw);
            loadData();
            initdata();
            buildRecyclerView();
            saveData();
        }
        else{
            loadDatainit();
            buildRecyclerView();
        }

        btnaddmed = findViewById(R.id.addButton);
        btnaddmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = AskAdd();
                dialog.show();
            }
        });

        btnhome = findViewById(R.id.homebutton);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = AskHome();
                dialog.show();
            }
        });

        btnclear = findViewById(R.id.clearallbutton);
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog diaBox = AskDelete();
                diaBox.show();
            }
        });

     /*   ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItem(medicationAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);*/
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson_monday = new Gson();
        String json_monday = gson_monday.toJson(mondayitems);
        editor.putString("monday items",json_monday);
        editor.apply();
        Gson gson_tuesday = new Gson();
        String json_tuesday = gson_tuesday.toJson(tuesdayitems);
        editor.putString("tuesday items",json_tuesday);
        editor.apply();
        Gson gson_wednesday = new Gson();
        String json_wednesday = gson_wednesday.toJson(wednesdayitems);
        editor.putString("wednesday items",json_wednesday);
        editor.apply();
        Gson gson_thursday = new Gson();
        String json_thursday = gson_thursday.toJson(thursdayitems);
        editor.putString("thursday items",json_thursday);
        editor.apply();
        Gson gson_friday = new Gson();
        String json_friday = gson_friday.toJson(fridayitems);
        editor.putString("friday items",json_friday);
        editor.apply();
        Gson gson_saturday = new Gson();
        String json_saturday = gson_saturday.toJson(saturdayitems);
        editor.putString("saturday items",json_saturday);
        editor.apply();
        Gson gson_sunday = new Gson();
        String json_sunday = gson_sunday.toJson(sundayitems);
        editor.putString("sunday items",json_sunday);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson_monday = new Gson();
        String json_monday = sharedPreferences.getString("monday items",null);
        Gson gson_tuesday = new Gson();
        String json_tuesday = sharedPreferences.getString("tuesday items",null);
        Gson gson_wednesday = new Gson();
        String json_wednesday = sharedPreferences.getString("wednesday items",null);
        Gson gson_thursday = new Gson();
        String json_thursday = sharedPreferences.getString("thursday items",null);
        Gson gson_friday = new Gson();
        String json_friday = sharedPreferences.getString("friday items",null);
        Gson gson_saturday = new Gson();
        String json_saturday = sharedPreferences.getString("saturday items",null);
        Gson gson_sunday = new Gson();
        String json_sunday = sharedPreferences.getString("sunday items",null);
        Type type = new TypeToken<ArrayList<medinfo>>() {}.getType();
        mondayitems = gson_monday.fromJson(json_monday,type);
        if(mondayitems == null) {
            mondayitems = new ArrayList<>();
        }
        tuesdayitems = gson_tuesday.fromJson(json_tuesday,type);
        if(tuesdayitems == null) {
            tuesdayitems = new ArrayList<>();
        }
        wednesdayitems = gson_wednesday.fromJson(json_wednesday,type);
        if(wednesdayitems == null) {
            wednesdayitems = new ArrayList<>();
        }
        thursdayitems = gson_thursday.fromJson(json_thursday,type);
        if(thursdayitems == null) {
            thursdayitems = new ArrayList<>();
        }
        fridayitems = gson_friday.fromJson(json_friday,type);
        if(fridayitems == null) {
            fridayitems = new ArrayList<>();
        }
        saturdayitems = gson_saturday.fromJson(json_saturday,type);
        if(saturdayitems == null) {
            saturdayitems = new ArrayList<>();
        }
        sundayitems = gson_sunday.fromJson(json_sunday,type);
        if(sundayitems == null) {
            sundayitems = new ArrayList<>();
        }
        //Log.d("mon",json_monday);

    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainRecyclerViewAdapter(this,daysofweekinput,itemTouchHelper);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    public void loadDatainit(){
        loadData();
        daysofweekinput.add(new DayHeader("Monday",mondayitems));
        daysofweekinput.add(new DayHeader("Tuesday",tuesdayitems));
        daysofweekinput.add(new DayHeader("Wednesday",wednesdayitems));
        daysofweekinput.add(new DayHeader("Thursday",thursdayitems));
        daysofweekinput.add(new DayHeader("Friday",fridayitems));
        daysofweekinput.add(new DayHeader("Saturday",saturdayitems));
        daysofweekinput.add(new DayHeader("Sunday",sundayitems));
    }

    public void clearall(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        loadData();
        buildRecyclerView();
    }

    private AlertDialog AskDelete(){

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Deleting Medication List")
                .setMessage("Do you want to clear your medication schedule?")
                .setIcon(R.mipmap.ic_delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        clearall();
                        dialog.dismiss();
                        Intent intent = new Intent(MedicationSchedule.this, WelcomePage.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    private AlertDialog AskAdd(){

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Add New Medication")
                .setMessage("Do you want to add a new medication?")
                .setIcon(R.mipmap.ic_add)

                .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(MedicationSchedule.this, AddMed.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }

    private AlertDialog AskHome(){

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Return to Home Screen")
                .setMessage("Do you want to go back to the home screen?")
                .setIcon(R.mipmap.ic_home)

                .setPositiveButton("Home", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(MedicationSchedule.this, WelcomePage.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }





    public void initdata(){
        if(dw.charAt(1)=='1'){
            String monday = "Monday";
            //ArrayList<medinfo> mondayitems = new ArrayList<>();
            mondayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(monday,mondayitems));
            saveData();
        }
        else if(dw.charAt(1)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Monday",mondayitems));
        }
        if(dw.charAt(4)=='1'){
            String tuesday = "Tuesday";
            //ArrayList<medinfo> tuesdayitems = new ArrayList<>();
            tuesdayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(tuesday,tuesdayitems));
            saveData();
        }
        else if(dw.charAt(4)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Tuesday",tuesdayitems));
        }
        if (dw.charAt(7)=='1'){
            String wednesday = "Wednesday";
            //ArrayList<medinfo> wednesdayitems = new ArrayList<>();
            wednesdayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(wednesday,wednesdayitems));
            saveData();
        }
        else if(dw.charAt(7)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Wednesday",wednesdayitems));
        }
        if (dw.charAt(10)=='1'){
            String thursday = "Thursday";
            //ArrayList<medinfo> thursdayitems = new ArrayList<>();
            thursdayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(thursday,thursdayitems));
            saveData();
        }
        else if(dw.charAt(10)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Thursday",thursdayitems));
        }
        if (dw.charAt(13)=='1'){
            String friday = "Friday";
            //ArrayList<medinfo> fridayitems = new ArrayList<>();
            fridayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(friday,fridayitems));
            saveData();
        }
        else if(dw.charAt(13)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Friday",fridayitems));
        }
        if (dw.charAt(16)=='1'){
            String saturday = "Saturday";
            //ArrayList<medinfo> saturdayitems = new ArrayList<>();
            saturdayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(saturday,saturdayitems));
            saveData();

        }
        else if(dw.charAt(16)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Saturday",saturdayitems));
        }
        if (dw.charAt(19)=='1'){
            String sunday = "Sunday";
            //ArrayList<medinfo> sundayitems = new ArrayList<>();
            sundayitems.add(new medinfo(med,time,pillnumber,binnumber));
            daysofweekinput.add(new DayHeader(sunday,sundayitems));
            saveData();
        }
        else if(dw.charAt(19)=='0'){
            loadData();
            daysofweekinput.add(new DayHeader("Sunday",sundayitems));
        }
    }
}