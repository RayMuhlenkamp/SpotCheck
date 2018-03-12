package com.ucsb.cs48.spotcheck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.ucsb.cs48.spotcheck.SCFirebaseInterface.SCFirebase;
import com.ucsb.cs48.spotcheck.SCFirebaseInterface.SCFirebaseAuth;
import com.ucsb.cs48.spotcheck.SCFirebaseInterface.SCFirebaseCallback;
import com.ucsb.cs48.spotcheck.SCLocalObjects.ParkingSpot;
import com.ucsb.cs48.spotcheck.SCLocalObjects.SpotCheckUser;

import java.util.ArrayList;

public class MyParkingSpots extends AppCompatActivity {

    private SpotCheckUser user;
    private SCFirebase scFirebase;
    private SCFirebaseAuth scFirebaseAuth;
    private FirebaseUser currentUser;

    private ArrayList<ParkingSpot> usersParkingSpots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_parking_spots);


        scFirebase = new SCFirebase();
        scFirebaseAuth = new SCFirebaseAuth();
        currentUser = scFirebaseAuth.getCurrentUser();

//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.activity_listview, mobileArray);
//
//        ListView listView = (ListView) findViewById(R.id.mobile_list);
//        listView.setAdapter(adapter);


        final ProgressDialog dialog = ProgressDialog.show(MyParkingSpots.this, "",
            "Setting up email...", true);
        scFirebase.getUsersParkingSpots(currentUser.getUid(), new SCFirebaseCallback<ArrayList<ParkingSpot>>() {
            @Override
            public void callback(ArrayList<ParkingSpot> data) {
               dialog.dismiss();
               if(data != null) {
                   usersParkingSpots = data;

                   final Handler mainHandler = new Handler(Looper.getMainLooper());
                   mainHandler.post(new Runnable() {
                       @Override
                       public void run() {

                           /**
                            * Fill in the adapter/list view here
                            * with the usersParkingSpots
                            *
                            */
                       }
                   });

               }

            }
        });

//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.activity_listview, user.getCurrentListings());
//
//        ListView listView = (ListView) findViewById(R.id.my_parking_spots);
//        listView.setAdapter(adapter);


    }
}
