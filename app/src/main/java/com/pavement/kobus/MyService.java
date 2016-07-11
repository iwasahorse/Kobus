package com.pavement.kobus;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import com.google.android.gms.games.GamesStatusCodes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyService extends IntentService {
    public static final int NOTIFICATION_ID = 101;
    private static int alarmOption;
    private static int port;
    private static double stop_latitude;
    private static double stop_longitude;
    private String altitude;
    private boolean connected;
    private Context context;
    private BufferedReader in;
    private String latitude;
    private String longitude;
    private NotificationManager notificationManager;
    private PrintWriter out;
    private Socket socket;

    public MyService() {
        super("MyService");
        this.connected = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();

        Log.i("check", "\uc0dd\uc131\ud55c\ub2e4");
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("check", "\ud30c\uad18\ud55c\ub2e4");
        if (this.connected) {
            disconnect();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            try {
                stop_latitude = intent.getDoubleExtra("stop_latitude", stop_latitude);
                stop_longitude = intent.getDoubleExtra("stop_longitude", stop_longitude);
                port = intent.getIntExtra("port", port);
                alarmOption = intent.getIntExtra("alarmOption", alarmOption);
            } catch (Exception e) {
                e.printStackTrace();
                if (isServiceAlarmOn(this.context)) {
                    setServiceAlarm(this.context, false);
                }
            }
        }
        connect(port);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Double distance;
        try {
            interaction();
            if (this.latitude == null || this.longitude == null) {
                setServiceAlarm(this.context, false);
                return;
            }
            distance = calDistance(Double.valueOf(this.latitude), Double.valueOf(this.longitude), stop_latitude, stop_longitude);
            Log.i("distance", distance.toString());
            if (alarmOption == 0 && distance >= 0.0d && distance <= 800.0d) {
                showNotification();
                setServiceAlarm(this.context, false);
            } else if (alarmOption == 1 && distance > 800.0d && distance <= 2400.0d) {
                showNotification();
                setServiceAlarm(this.context, false);
            } else if (alarmOption == 2 && distance > 2400.0d && distance <= 4000.0d) {
                showNotification();
                setServiceAlarm(this.context, false);
            }
        } catch (Exception e) {
            Log.i("exception error", "onHandleIntent Exception");
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        return (PendingIntent.getService(context, 0, new Intent(context, MyService.class), PendingIntent.FLAG_UPDATE_CURRENT) != null);
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        PendingIntent setPi = PendingIntent.getService(context, 0, new Intent(context, MyService.class), 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (isOn) {
            Log.i("check", "setServiceAlarm On");
            alarmManager.setRepeating(1, System.currentTimeMillis(), 3000, setPi);
            return;
        }
        Log.i("check", "setServiceAlarm Off");
        alarmManager.cancel(setPi);
        setPi.cancel();
    }

    public void showNotification() {
        Intent intent;
        switch (port) {
            case 5555:
                intent = new Intent(this, TerminalBusStop.class);
                break;
            case 6666:
                intent = new Intent(this, TerminalBusStop.class);
                break;
            case 7777:
                intent = new Intent(this, TerminalBusStop.class);
                break;
            case 8888:
                intent = new Intent(this, TerminalBusStop.class);
                break;
            default:
                intent = new Intent(this, TerminalBusStop.class);
                break;
        }
        Notification notification = new Builder(this).setSmallIcon(R.drawable.ic_bus).setTicker("Test1234").setContentTitle("5678").setContentText("abcd").setWhen(System.currentTimeMillis()).setContentIntent(PendingIntent.getActivity(this, 0, intent, 0)).build();
        notification.defaults = -1;
        notification.flags = 20;
        this.notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public double calDistance(double lat1, double lon1, double lat2, double lon2) {
        return (((60.0d * rad2deg(Math.acos((Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))) + ((Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))) * Math.cos(deg2rad(lon1 - lon2)))))) * 1.1515d) * 1.609344d) * 1000.0d;
    }

    private double deg2rad(double deg) {
        return (3.141592653589793d * deg) / 180.0d;
    }

    private double rad2deg(double rad) {
        return (180.0d * rad) / 3.141592653589793d;
    }

    public void connect(int portNumber) {
        StrictMode.enableDefaults();
        String hostName = "218.150.181.119";
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(hostName, portNumber), GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.connected = true;
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
        } catch (IOException e2) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        }
    }

    public void interaction() {
        try {
            this.out.println(2);
            this.latitude = this.in.readLine();
            this.longitude = this.in.readLine();
            this.altitude = this.in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("exception error", "\uc11c\ubc84\ub85c\ubd80\ud130 \uc751\ub2f5\uc744 \ubc1b\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
            disconnect();
        }
    }

    public void disconnect() {
        try {
            this.out.println("quit");
            this.socket.close();
            this.out.close();
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
