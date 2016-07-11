package com.pavement.kobus;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.busclient.CourseDialogFragment.DialogListener;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.Barcode.Phone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class CheonanBusStop extends AppCompatActivity implements DialogListener {
    static final LatLng cheonanstation_stop;
    static final LatLng guseonggs_stop;
    static final LatLng kut_stop;
    static final LatLng nambu_stop;
    static final LatLng samyong_stop;
    private String altitude;
    private boolean focused;
    private BufferedReader in;
    private String latitude;
    private String longitude;
    private ViewGroup mBusStopLayout;
    private Button mButtonBusLocation;
    private GoogleMap mMap;
    private Marker marker;
    private MarkerOptions myPositionMarker;
    private final CourseDialogFragment newFragment;
    private PrintWriter out;
    private Socket socket;
    private Timer timer;

    /* renamed from: com.example.busclient.CheonanBusStop.2 */
    class C01652 implements OnClickListener {
        C01652() {
        }

        public void onClick(View v) {
            CheonanBusStop.this.startActivity(new Intent(CheonanBusStop.this, ShinbangBusStop.class));
        }
    }

    /* renamed from: com.example.busclient.CheonanBusStop.3 */
    class C01663 implements OnTouchListener {
        C01663() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    public class UpdateGPSTask extends TimerTask {

        /* renamed from: com.example.busclient.CheonanBusStop.UpdateGPSTask.1 */
        class C01671 implements Runnable {
            C01671() {
            }

            public void run() {
                CheonanBusStop.this.interaction();
                if (CheonanBusStop.this.latitude == null || CheonanBusStop.this.longitude == null) {
                    CheonanBusStop.this.disconnect();
                    return;
                }
                if (CheonanBusStop.this.focused) {
                    CheonanBusStop.this.myPositionMarker.position(new LatLng(Double.valueOf(CheonanBusStop.this.latitude).doubleValue(), Double.valueOf(CheonanBusStop.this.longitude).doubleValue()));
                } else {
                    CheonanBusStop.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(CheonanBusStop.this.latitude).doubleValue(), Double.valueOf(CheonanBusStop.this.longitude).doubleValue()), 18.0f));
                    CheonanBusStop.this.myPositionMarker = new MarkerOptions().position(new LatLng(Double.valueOf(CheonanBusStop.this.latitude).doubleValue(), Double.valueOf(CheonanBusStop.this.longitude).doubleValue())).draggable(false).title("\ubc84\uc2a4 \uc704\uce58").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.ic_bus));
                    CheonanBusStop.this.focused = true;
                }
                CheonanBusStop.this.marker = CheonanBusStop.this.mMap.addMarker(CheonanBusStop.this.myPositionMarker);
                CheonanBusStop.this.marker.showInfoWindow();
            }
        }

        public void run() {
            CheonanBusStop.this.runOnUiThread(new C01671());
        }
    }

    /* renamed from: com.example.busclient.CheonanBusStop.1 */
    class C05411 implements OnMarkerClickListener {
        C05411() {
        }

        public boolean onMarkerClick(Marker marker) {
            String markerTitle = marker.getTitle();
            View dialogView = CheonanBusStop.this.getLayoutInflater().inflate(C0177R.layout.default_stop, null);
            Builder builder = new Builder(CheonanBusStop.this.mBusStopLayout.getContext());
            ImageView imageView = (ImageView) dialogView.findViewById(C0177R.id.default_stop);
            boolean z = true;
            switch (markerTitle.hashCode()) {
                case 48927307:
                    if (markerTitle.equals("\uc0bc\ub8e1\uad50")) {
                        z = true;
                        break;
                    }
                    break;
                case 51953473:
                    if (markerTitle.equals("\ucc9c\uc548\uc5ed")) {
                        z = true;
                        break;
                    }
                    break;
                case 53917996:
                    if (markerTitle.equals("\ud55c\uae30\ub300")) {
                        z = false;
                        break;
                    }
                    break;
                case 311023496:
                    if (markerTitle.equals("\ub0a8\ubd80\uc624\uac70\ub9ac")) {
                        z = true;
                        break;
                    }
                    break;
                case 1233669153:
                    if (markerTitle.equals("\uad6c\uc131\ub3d9(GS\uc8fc\uc720\uc18c)")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case Phone.UNKNOWN /*0*/:
                    imageView.setImageResource(C0177R.drawable.kut_stop);
                    break;
                case CompletionEvent.STATUS_FAILURE /*1*/:
                    imageView.setImageResource(C0177R.drawable.terminal_stop);
                    break;
                case CompletionEvent.STATUS_CONFLICT /*2*/:
                    imageView.setImageResource(C0177R.drawable.nambu_stop);
                    break;
                case CompletionEvent.STATUS_CANCELED /*3*/:
                    imageView.setImageResource(C0177R.drawable.samryong_stop);
                    break;
                case Barcode.PHONE /*4*/:
                    imageView.setImageResource(C0177R.drawable.guseonggs_stop);
                    break;
                default:
                    return false;
            }
            builder.setView(dialogView);
            builder.setTitle(markerTitle);
            Event.onAlarmOptionSelected(builder, dialogView, marker);
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            return false;
        }
    }

    public CheonanBusStop() {
        this.latitude = null;
        this.longitude = null;
        this.altitude = "0";
        this.timer = null;
        this.focused = false;
        this.marker = null;
        this.newFragment = new CourseDialogFragment();
    }

    static {
        kut_stop = new LatLng(36.76481d, 127.282089d);
        cheonanstation_stop = new LatLng(36.8081447d, 127.1475566d);
        nambu_stop = new LatLng(36.8006114d, 127.1485574d);
        samyong_stop = new LatLng(36.7974828d, 127.1592148d);
        guseonggs_stop = new LatLng(36.7940113d, 127.1598489d);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0177R.layout.bus_stop_main);
        this.mBusStopLayout = (ViewGroup) findViewById(C0177R.id.bus_stop_layout);
        this.mButtonBusLocation = (Button) findViewById(C0177R.id.btn_bus_location);
        setMap();
        connect(8888);
    }

    protected void setMap() {
        if (this.mMap == null) {
            this.mMap = ((MapFragment) getFragmentManager().findFragmentById(C0177R.id.map)).getMap();
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cheonanstation_stop, 14.0f));
        }
        setStops();
    }

    public void setStops() {
        MarkerOptions kut_stopMarker = new MarkerOptions().position(kut_stop).draggable(false).title("\ud55c\uae30\ub300").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions cheonanstation_stopMarker = new MarkerOptions().position(cheonanstation_stop).draggable(false).title("\ucc9c\uc548\uc5ed").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions nambu_stopMarker = new MarkerOptions().position(nambu_stop).draggable(false).title("\ub0a8\ubd80\uc624\uac70\ub9ac").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions samyong_stopMarker = new MarkerOptions().position(samyong_stop).draggable(false).title("\uc0bc\ub8e1\uad50").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions guseonggs_stopMarker = new MarkerOptions().position(guseonggs_stop).draggable(false).title("\uad6c\uc131\ub3d9(GS\uc8fc\uc720\uc18c)").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        this.mMap.addMarker(kut_stopMarker).showInfoWindow();
        this.mMap.addMarker(cheonanstation_stopMarker);
        this.mMap.addMarker(nambu_stopMarker);
        this.mMap.addMarker(guseonggs_stopMarker);
        this.mMap.addMarker(samyong_stopMarker);
        this.mMap.setOnMarkerClickListener(new C05411());
    }

    public void connect(int portNumber) {
        StrictMode.enableDefaults();
        String hostName = "218.150.181.119";
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(hostName, portNumber), GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.timer = new Timer();
            this.timer.schedule(new UpdateGPSTask(), 1000, (long) Event.period);
            Toast.makeText(this, "\uc11c\ubc84\ub85c\ubd80\ud130 GPS \uc815\ubcf4\ub97c \ubc1b\uae30 \uc2dc\uc791\ud588\uc2b5\ub2c8\ub2e4.", 0).show();
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
            Toast.makeText(this, "Unknown host: " + hostName, 0).show();
        } catch (IOException e2) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            Toast.makeText(this, "Couldn't get I/O for the connection to " + hostName, 0).show();
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
            System.err.println("\uc11c\ubc84\ub85c\ubd80\ud130 \uc751\ub2f5\uc744 \ubc1b\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
            disconnect();
            this.latitude = null;
            this.longitude = null;
        }
        if (this.marker != null) {
            this.marker.remove();
        }
    }

    public void disconnect() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        try {
            this.out.println("quit");
            this.socket.close();
            this.out.close();
            this.in.close();
            Toast.makeText(this, "\uc11c\ubc84\uc640\uc758 \uc5f0\uacb0\uc774 \uc885\ub8cc\ub410\uc2b5\ub2c8\ub2e4.", 0).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCourseSelected(DialogFragment dialog, int selected) {
        Intent intent = null;
        switch (selected) {
            case Phone.UNKNOWN /*0*/:
                intent = new Intent(this, BusStop.class);
                break;
            case CompletionEvent.STATUS_FAILURE /*1*/:
                break;
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                intent = new Intent(this, TerminalBusStop.class);
                break;
            case CompletionEvent.STATUS_CANCELED /*3*/:
                intent = new Intent(this, DujeongBusStop.class);
                break;
            case Barcode.PHONE /*4*/:
                intent = new Intent(this, KTXBusStop.class);
                break;
            case Barcode.PRODUCT /*5*/:
                intent = new Intent(this, ShinbangBusStop.class);
                break;
            default:
                return;
        }
        if (intent != null) {
            startActivity(intent);
        }
        finish();
    }

    public void onSelectCanceled(DialogFragment dialog) {
    }

    public void onStopSelected(int selected) {
        switch (selected) {
            case Phone.UNKNOWN /*0*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kut_stop, 18.0f));
            case CompletionEvent.STATUS_FAILURE /*1*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cheonanstation_stop, 18.0f));
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nambu_stop, 18.0f));
            case CompletionEvent.STATUS_CANCELED /*3*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(samyong_stop, 18.0f));
            case Barcode.PHONE /*4*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(guseonggs_stop, 18.0f));
            default:
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        View customView = LayoutInflater.from(this).inflate(C0177R.layout.actionbar_layout, this.mBusStopLayout, false);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
        ((TextView) findViewById(C0177R.id.text_selected)).setText("\ucc9c\uc548\uc5ed");
        return super.onCreateOptionsMenu(menu);
    }

    public void onOptionsItemSelected(View view) {
        switch (view.getId()) {
            case C0177R.id.ic_directions_black /*2131558507*/:
                this.newFragment.show(getFragmentManager(), "course");
            case C0177R.id.ic_place_black /*2131558509*/:
                this.newFragment.show(getFragmentManager(), "cheonan");
            default:
        }
    }

    public void onClickBusLocation(View view) {
        if (this.latitude != null && this.longitude != null) {
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue()), 18.0f));
        }
    }
}
