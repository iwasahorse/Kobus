package com.pavement.kobus;

import android.app.IntentService;
import android.content.Intent;

public class GPSService extends IntentService {
    public GPSService() {
        super("GPSService");
    }

    public GPSService(String name) {
        super(name);
    }

    protected void onHandleIntent(Intent intent) {
    }
}
