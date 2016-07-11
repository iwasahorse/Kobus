package com.pavement.kobus;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private Button busstop_button;
    private Button passenger_button;
    private Button timetable_button;

    private OnClickListener onClickListenerBusStop = new OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, BusStop.class));
        }
    };

    /* renamed from: com.example.busclient.MainActivity.1 */
    /*class C01731 implements OnClickListener {
        C01731() {
        }

        public void onClick(View v) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, BusTimetable.class));
        }
    }

    *//* renamed from: com.example.busclient.MainActivity.2 *//*
    class C01742 implements OnClickListener {
        C01742() {
        }

        public void onClick(View v) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, BusStop.class));
        }
    }

    *//* renamed from: com.example.busclient.MainActivity.3 *//*
    class C01753 implements OnClickListener {
        C01753() {
        }

        public void onClick(View v) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, PassengerNumbers.class));
        }
    }*/

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            System.out.println("version: " + getPackageManager().getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0).versionCode);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        this.timetable_button = (Button) findViewById(R.id.bus_timetable);
        this.busstop_button = (Button) findViewById(R.id.search_busstop);
        this.passenger_button = (Button) findViewById(R.id.passengers_search);
        if (MyService.isServiceAlarmOn(getBaseContext())) {
            MyService.setServiceAlarm(getBaseContext(), false);
        }

        /*this.timetable_button.setOnClickListener(new C01731());
        this.passenger_button.setOnClickListener(new C01753());*/
    }
}
