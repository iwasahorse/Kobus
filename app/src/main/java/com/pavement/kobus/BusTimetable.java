package com.pavement.kobus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class BusTimetable extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0177R.layout.bus_timetable);
        TabHost tabHost = (TabHost) findViewById(C0177R.id.tabHost);
        tabHost.setup();
        TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(C0177R.id.cheon_an_timetable);
        spec.setIndicator("\ucc9c\uc548\uc9c0\uc5ed");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag2");
        spec.setContent(C0177R.id.cheon_an_timetable2);
        spec.setIndicator("\ucc9c\uc548 \ud68c\ucc28\ubcc4");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag3");
        spec.setContent(C0177R.id.cheong_ju_timetable);
        spec.setIndicator("\uccad\uc8fc\uc9c0\uc5ed");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag4");
        spec.setContent(C0177R.id.cheong_ju_timetable2);
        spec.setIndicator("\uccad\uc8fc \ud68c\ucc28\ubcc4");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag5");
        spec.setContent(C0177R.id.seoul_timetable);
        spec.setIndicator("\uc11c\uc6b8\uc9c0\uc5ed");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag6");
        spec.setContent(C0177R.id.daejeon_timetable);
        spec.setIndicator("\ub300\uc804\uc9c0\uc5ed");
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0177R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0177R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
