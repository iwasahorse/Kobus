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
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.games.GamesStatusCodes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyService extends IntentService {
    public static final int NOTI_ID = 101;
    private static AlarmManager alarmManager;
    private static int alarmOption;
    private static Intent myIntent;
    private static int port;
    private static PendingIntent setPi;
    private static double stop_latitude;
    private static double stop_longitude;
    private String altitude;
    private boolean connected;
    private Context context;
    private double dis;
    private BufferedReader in;
    private String latitude;
    private String longitude;
    private NotificationManager mNotiMgr;
    private PrintWriter out;
    private Socket socket;

    public static void setMyIntent(Intent intent) {
        myIntent = intent;
    }

    public MyService() {
        super("MyService");
        this.connected = false;
    }

    public void onCreate() {
        super.onCreate();
        this.context = getBaseContext();
        Log.i("check", "\uc0dd\uc131\ud55c\ub2e4");
        if (myIntent != null) {
            try {
                stop_latitude = myIntent.getDoubleExtra("stop_latitude", stop_latitude);
                stop_longitude = myIntent.getDoubleExtra("stop_longitude", stop_longitude);
                port = myIntent.getIntExtra("port", port);
                alarmOption = myIntent.getIntExtra("alarmOption", alarmOption);
            } catch (Exception e) {
                e.printStackTrace();
                if (isServiceAlarmOn(this.context)) {
                    setServiceAlarm(this.context, false);
                }
            }
        }
        this.mNotiMgr = (NotificationManager) getSystemService("notification");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("check", "\ud30c\uad18\ud55c\ub2e4");
        if (this.connected) {
            disconnect();
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        connect(port);
        return super.onStartCommand(intent, flags, startId);
    }

    protected void onHandleIntent(Intent intent) {
        try {
            interaction();
            if (this.latitude == null || this.longitude == null) {
                setServiceAlarm(this.context, false);
                return;
            }
            this.dis = calDistance(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue(), stop_latitude, stop_longitude);
            Log.i("distance", BuildConfig.VERSION_NAME + this.dis);
            if (alarmOption == 0 && this.dis >= 0.0d && this.dis <= 800.0d) {
                noti();
                setServiceAlarm(this.context, false);
            } else if (alarmOption == 1 && this.dis > 800.0d && this.dis <= 2400.0d) {
                noti();
                setServiceAlarm(this.context, false);
            } else if (alarmOption == 2 && this.dis > 2400.0d && this.dis <= 4000.0d) {
                noti();
                setServiceAlarm(this.context, false);
            }
        } catch (Exception e) {
            Log.i("exception error", "onHandleIntent Exception");
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        if (PendingIntent.getService(context, 0, new Intent(context, MyService.class), DriveFile.MODE_WRITE_ONLY) != null) {
            return true;
        }
        return false;
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        setPi = PendingIntent.getService(context, 0, new Intent(context, MyService.class), 0);
        alarmManager = (AlarmManager) context.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        if (isOn) {
            Log.i("check", "setServiceAlarm On");
            alarmManager.setRepeating(1, System.currentTimeMillis(), 1000, setPi);
            return;
        }
        Log.i("check", "setServiceAlarm Off");
        alarmManager.cancel(setPi);
        setPi.cancel();
    }

    public void noti() {
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
                intent = new Intent(this, CheonanBusStop.class);
                break;
            default:
                intent = new Intent(this, ShinbangBusStop.class);
                break;
        }
        Notification noti = new Builder(this).setSmallIcon(C0177R.drawable.ic_bus).setTicker("\ubc84\uc2a4\ub3c4\ucc29\uc804\uc785\ub2c8\ub2e4").setContentTitle("\ubc84\uc2a4\ub3c4\ucc29\uc804\uc785\ub2c8\ub2e4").setContentText("\ubc84\uc2a4\ub3c4\ucc29\uc804\uc785\ub2c8\ub2e4").setWhen(System.currentTimeMillis()).setContentIntent(PendingIntent.getActivity(this, 0, intent, 0)).build();
        noti.defaults = -1;
        noti.flags = 20;
        this.mNotiMgr.notify(NOTI_ID, noti);
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
