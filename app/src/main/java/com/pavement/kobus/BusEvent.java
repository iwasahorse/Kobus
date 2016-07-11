package com.pavement.kobus;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.gms.drive.DriveStatusCodes;
import com.google.android.gms.maps.model.Marker;

public class BusEvent {
    static int alarmOption;
    static int period;

    /* renamed from: com.example.busclient.Event.1 */
    static class C01701 implements OnClickListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ View val$dialogView;
        final /* synthetic */ Intent val$intent;
        final /* synthetic */ Marker val$marker;
        final /* synthetic */ RadioButton val$radioButton1;
        final /* synthetic */ RadioButton val$radioButton3;
        final /* synthetic */ RadioButton val$radioButton5;
        final /* synthetic */ RadioGroup val$radioGroup;

        C01701(RadioGroup radioGroup, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, Context context, View view, Intent intent, Marker marker) {
            this.val$radioGroup = radioGroup;
            this.val$radioButton1 = radioButton;
            this.val$radioButton3 = radioButton2;
            this.val$radioButton5 = radioButton3;
            this.val$context = context;
            this.val$dialogView = view;
            this.val$intent = intent;
            this.val$marker = marker;
        }

        public void onClick(DialogInterface dialog, int which) {
            int port;
            int selectedId = this.val$radioGroup.getCheckedRadioButtonId();
            if (selectedId == this.val$radioButton1.getId()) {
                alarmOption = 0;
            } else if (selectedId == this.val$radioButton3.getId()) {
                alarmOption = 1;
            } else if (selectedId == this.val$radioButton5.getId()) {
                alarmOption = 2;
            } else {
                alarmOption = 3;
                if (MyService.isServiceAlarmOn(this.val$context)) {
                    MyService.setServiceAlarm(this.val$context, false);
                    return;
                }
                return;
            }
            String courseName = this.val$dialogView.getContext().getClass().getName();
            boolean z = true;
            switch (courseName.hashCode()) {
                case -1786140057:
                    if (courseName.equals("com.example.busclient.KTXBusStop")) {
                        z = true;
                        break;
                    }
                    break;
                case -1241155942:
                    if (courseName.equals("com.example.busclient.DujeongBusStop")) {
                        z = true;
                        break;
                    }
                    break;
                case -1118028022:
                    if (courseName.equals("com.example.busclient.CheonanBusStop")) {
                        z = false;
                        break;
                    }
                    break;
                case -1034961828:
                    if (courseName.equals("com.example.busclient.ShinbangBusStop")) {
                        z = true;
                        break;
                    }
                    break;
                case -882688494:
                    if (courseName.equals("com.example.busclient.TerminalBusStop")) {
                        z = true;
                        break;
                    }
                    break;
            }
            port = 8888;
            /*switch (z) {
                case Phone.UNKNOWN *//*0*//*:
                    port = 8888;
                    break;
                case CompletionEvent.STATUS_FAILURE *//*1*//*:
                    port = 7777;
                    break;
                case CompletionEvent.STATUS_CONFLICT *//*2*//*:
                    port = 6666;
                    break;
                case CompletionEvent.STATUS_CANCELED *//*3*//*:
                    port = 5555;
                    break;
                case Barcode.PHONE *//*4*//*:
                    port = 4444;
                    break;
                default:
                    return;
            }*/
            this.val$intent.putExtra("stop_latitude", this.val$marker.getPosition().latitude);
            this.val$intent.putExtra("stop_longitude", this.val$marker.getPosition().longitude);
            this.val$intent.putExtra("port", port);
            this.val$intent.putExtra("alarmOption", alarmOption);
           // MyService.setMyIntent(this.val$intent);
            if (MyService.isServiceAlarmOn(this.val$context)) {
                MyService.setServiceAlarm(this.val$context, false);
            }
            MyService.setServiceAlarm(this.val$context, true);
            Log.i("check service on: ", BuildConfig.VERSION_NAME + MyService.isServiceAlarmOn(this.val$context));
        }
    }

    /* renamed from: com.example.busclient.Event.2 */
    static class C01712 implements OnClickListener {
        C01712() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    static {
        alarmOption = 3;
        period = DriveStatusCodes.DRIVE_EXTERNAL_STORAGE_REQUIRED;
    }

    public static void onAlarmOptionSelected(Builder builder, View dialogView, Marker marker) {
        Context context = builder.getContext();
        builder.setPositiveButton("\uc644\ub8cc", new C01701((RadioGroup) dialogView.findViewById(R.id.dialog_rg), (RadioButton) dialogView.findViewById(R.id.dialog_1min), (RadioButton) dialogView.findViewById(R.id.dialog_3min), (RadioButton) dialogView.findViewById(R.id.dialog_5min), context, dialogView, new Intent(context, MyService.class), marker));
        builder.setNegativeButton("\ucde8\uc18c", new C01712());
    }
}
