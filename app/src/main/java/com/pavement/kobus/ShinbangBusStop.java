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

public class ShinbangBusStop extends AppCompatActivity implements DialogListener {
    static final LatLng cheongsugo_stop;
    static final LatLng chungmu_stop;
    static final LatLng guseonggs_stop;
    static final LatLng jeilgo_stop;
    static final LatLng kut_stop;
    static final LatLng samyong_stop;
    static final LatLng sejong_stop;
    static final LatLng shinbanghyundai_stop;
    static final LatLng shingbangmg_stop;
    static final LatLng sujainapt_stop;
    static final LatLng wongseonggs_stop;
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

    /* renamed from: com.example.busclient.ShinbangBusStop.2 */
    class C01782 implements OnClickListener {
        C01782() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(ShinbangBusStop.this, CheonanBusStop.class);
        }
    }

    /* renamed from: com.example.busclient.ShinbangBusStop.3 */
    class C01793 implements OnClickListener {
        C01793() {
        }

        public void onClick(View v) {
            ShinbangBusStop.this.startActivity(new Intent(ShinbangBusStop.this, CheonanBusStop.class));
        }
    }

    /* renamed from: com.example.busclient.ShinbangBusStop.4 */
    class C01804 implements OnTouchListener {
        C01804() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    public class UpdateGPSTask extends TimerTask {

        /* renamed from: com.example.busclient.ShinbangBusStop.UpdateGPSTask.1 */
        class C01811 implements Runnable {
            C01811() {
            }

            public void run() {
                ShinbangBusStop.this.interaction();
                if (ShinbangBusStop.this.latitude == null || ShinbangBusStop.this.longitude == null) {
                    ShinbangBusStop.this.disconnect();
                    return;
                }
                if (ShinbangBusStop.this.focused) {
                    ShinbangBusStop.this.myPositionMarker.position(new LatLng(Double.valueOf(ShinbangBusStop.this.latitude).doubleValue(), Double.valueOf(ShinbangBusStop.this.longitude).doubleValue()));
                } else {
                    ShinbangBusStop.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(ShinbangBusStop.this.latitude).doubleValue(), Double.valueOf(ShinbangBusStop.this.longitude).doubleValue()), 18.0f));
                    ShinbangBusStop.this.myPositionMarker = new MarkerOptions().position(new LatLng(Double.valueOf(ShinbangBusStop.this.latitude).doubleValue(), Double.valueOf(ShinbangBusStop.this.longitude).doubleValue())).draggable(false).title("\ubc84\uc2a4 \uc704\uce58").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.ic_bus));
                    ShinbangBusStop.this.focused = true;
                }
                if (ShinbangBusStop.this.marker != null) {
                    ShinbangBusStop.this.marker.remove();
                }
                ShinbangBusStop.this.marker = ShinbangBusStop.this.mMap.addMarker(ShinbangBusStop.this.myPositionMarker);
                ShinbangBusStop.this.marker.showInfoWindow();
            }
        }

        public void run() {
            ShinbangBusStop.this.runOnUiThread(new C01811());
        }
    }

    /* renamed from: com.example.busclient.ShinbangBusStop.1 */
    class C05441 implements OnMarkerClickListener {
        C05441() {
        }

        public boolean onMarkerClick(Marker marker) {
            String markerTitle = marker.getTitle();
            View dialogView = ShinbangBusStop.this.getLayoutInflater().inflate(C0177R.layout.default_stop, null);
            Builder builder = new Builder(ShinbangBusStop.this.mBusStopLayout.getContext());
            ImageView imageView = (ImageView) dialogView.findViewById(C0177R.id.default_stop);
            boolean z = true;
            switch (markerTitle.hashCode()) {
                case -2128900080:
                    if (markerTitle.equals("\uad6c\uc131\ub3d9 GS\uc8fc\uc720\uc18c")) {
                        z = true;
                        break;
                    }
                    break;
                case -1790047316:
                    if (markerTitle.equals("\uc2e0\ubc29\ub3d9 \uc0c8\ub9c8\uc744\uae08\uace0")) {
                        z = true;
                        break;
                    }
                    break;
                case -1770635082:
                    if (markerTitle.equals("\uc81c\uc77c\uace0 \ub9de\uc740\ud3b8")) {
                        z = true;
                        break;
                    }
                    break;
                case -1116660220:
                    if (markerTitle.equals("\uc2e0\ubc29\ub3d9 \ud604\ub300\uc544\ud30c\ud2b8")) {
                        z = true;
                        break;
                    }
                    break;
                case 48927307:
                    if (markerTitle.equals("\uc0bc\ub8e1\uad50")) {
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
                case 353577273:
                    if (markerTitle.equals("\uccad\uc218\uace0BS \uc55e\ucabd")) {
                        z = true;
                        break;
                    }
                    break;
                case 493423473:
                    if (markerTitle.equals("\uc138\uc885\uc544\ud2b8\ube4c\ub77c")) {
                        z = true;
                        break;
                    }
                    break;
                case 707056484:
                    if (markerTitle.equals("\uc6d0\uc131\ub3d9 GS\ub9c8\ud2b8")) {
                        z = true;
                        break;
                    }
                    break;
                case 1616070666:
                    if (markerTitle.equals("\ucda9\ubb34\ubcd1\uc6d0")) {
                        z = true;
                        break;
                    }
                    break;
                case 2143934160:
                    if (markerTitle.equals("\ud55c\uc591 \uc218\uc790\uc778APT")) {
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
                    imageView.setImageResource(C0177R.drawable.shinbanghyendea_stop);
                    break;
                case CompletionEvent.STATUS_CONFLICT /*2*/:
                    imageView.setImageResource(C0177R.drawable.shinbangmg_stop);
                    break;
                case CompletionEvent.STATUS_CANCELED /*3*/:
                    imageView.setImageResource(C0177R.drawable.chungmu_stop);
                    break;
                case Barcode.PHONE /*4*/:
                    imageView.setImageResource(C0177R.drawable.sejong_stop);
                    break;
                case Barcode.PRODUCT /*5*/:
                    imageView.setImageResource(C0177R.drawable.jeilgo_stop);
                    break;
                case Barcode.SMS /*6*/:
                    imageView.setImageResource(C0177R.drawable.wongseonggs_stop);
                    break;
                case Barcode.TEXT /*7*/:
                    imageView.setImageResource(C0177R.drawable.samryong_stop);
                    break;
                case Barcode.URL /*8*/:
                    imageView.setImageResource(C0177R.drawable.guseonggs_stop);
                    break;
                case Barcode.WIFI /*9*/:
                    imageView.setImageResource(C0177R.drawable.cheongsugo_stop);
                    break;
                case Barcode.GEO /*10*/:
                    imageView.setImageResource(C0177R.drawable.sujainapt_stop);
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

    public ShinbangBusStop() {
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
        shinbanghyundai_stop = new LatLng(36.7891664d, 127.1289787d);
        shingbangmg_stop = new LatLng(36.792067d, 127.12909d);
        chungmu_stop = new LatLng(36.7979027d, 127.1322097d);
        sejong_stop = new LatLng(36.7984135d, 127.1415559d);
        jeilgo_stop = new LatLng(36.8062039d, 127.1558579d);
        wongseonggs_stop = new LatLng(36.8052247d, 127.1586144d);
        samyong_stop = new LatLng(36.7974828d, 127.1592148d);
        guseonggs_stop = new LatLng(36.7940113d, 127.1598489d);
        cheongsugo_stop = new LatLng(36.7904561d, 127.1594437d);
        sujainapt_stop = new LatLng(36.7833849d, 127.1522386d);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0177R.layout.bus_stop_main);
        this.mBusStopLayout = (ViewGroup) findViewById(C0177R.id.bus_stop_layout);
        this.mButtonBusLocation = (Button) findViewById(C0177R.id.btn_bus_location);
        setMap();
        connect(4444);
    }

    protected void setMap() {
        if (this.mMap == null) {
            this.mMap = ((MapFragment) getFragmentManager().findFragmentById(C0177R.id.map)).getMap();
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sejong_stop, 14.0f));
        }
        setStops();
    }

    public void setStops() {
        MarkerOptions kut_stopMarker = new MarkerOptions().position(kut_stop).draggable(false).title("\ud55c\uae30\ub300").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions sinbanghyendea_stopMarker = new MarkerOptions().position(shinbanghyundai_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 \ud604\ub300\uc544\ud30c\ud2b8").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions sinbangmg_stopMarker = new MarkerOptions().position(shingbangmg_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 \uc0c8\ub9c8\uc744\uae08\uace0").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions chungmu_stopMarker = new MarkerOptions().position(chungmu_stop).draggable(false).title("\ucda9\ubb34\ubcd1\uc6d0").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions BSseojong_stopMarker = new MarkerOptions().position(sejong_stop).draggable(false).title("\uc138\uc885\uc544\ud2b8\ube4c\ub77c").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions jeailgo_stopMarker = new MarkerOptions().position(jeilgo_stop).draggable(false).title("\uc81c\uc77c\uace0 \ub9de\uc740\ud3b8").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions wongseonggs_stopMarker = new MarkerOptions().position(wongseonggs_stop).draggable(false).title("\uc6d0\uc131\ub3d9 GS\ub9c8\ud2b8").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions samyong_stopMarker = new MarkerOptions().position(samyong_stop).draggable(false).title("\uc0bc\ub8e1\uad50").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions guseonggs_stopMarker = new MarkerOptions().position(guseonggs_stop).draggable(false).title("\uad6c\uc131\ub3d9 GS\uc8fc\uc720\uc18c").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions cheongsugo_stopMarker = new MarkerOptions().position(cheongsugo_stop).draggable(false).title("\uccad\uc218\uace0BS \uc55e\ucabd").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions sujainapt_stopMarker = new MarkerOptions().position(sujainapt_stop).draggable(false).title("\ud55c\uc591 \uc218\uc790\uc778APT").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        this.mMap.addMarker(kut_stopMarker).showInfoWindow();
        this.mMap.addMarker(sinbanghyendea_stopMarker);
        this.mMap.addMarker(sinbanghyendea_stopMarker);
        this.mMap.addMarker(sinbangmg_stopMarker);
        this.mMap.addMarker(chungmu_stopMarker);
        this.mMap.addMarker(BSseojong_stopMarker);
        this.mMap.addMarker(jeailgo_stopMarker);
        this.mMap.addMarker(wongseonggs_stopMarker);
        this.mMap.addMarker(samyong_stopMarker);
        this.mMap.addMarker(guseonggs_stopMarker);
        this.mMap.addMarker(cheongsugo_stopMarker);
        this.mMap.addMarker(sujainapt_stopMarker);
        this.mMap.setOnMarkerClickListener(new C05441());
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
            this.timer.schedule(new UpdateGPSTask(), 0, 500);
            Toast.makeText(this, "\uc11c\ubc84\ub85c\ubd80\ud130 GPS \uc815\ubcf4\ub97c \ubc1b\uae30 \uc2dc\uc791\ud588\uc2b5\ub2c8\ub2e4.", 0).show();
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
            Toast.makeText(this, "Unknown host: " + hostName, 0).show();
        } catch (IOException e2) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            Toast.makeText(this, "Couldn't get I/O for the connection to", 0).show();
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
            if (this.marker != null) {
                this.marker.remove();
            }
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
                intent = new Intent(this, CheonanBusStop.class);
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
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbanghyundai_stop, 18.0f));
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shingbangmg_stop, 18.0f));
            case CompletionEvent.STATUS_CANCELED /*3*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chungmu_stop, 18.0f));
            case Barcode.PHONE /*4*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sejong_stop, 18.0f));
            case Barcode.PRODUCT /*5*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeilgo_stop, 18.0f));
            case Barcode.SMS /*6*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wongseonggs_stop, 18.0f));
            case Barcode.TEXT /*7*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(samyong_stop, 18.0f));
            case Barcode.URL /*8*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(guseonggs_stop, 18.0f));
            case Barcode.WIFI /*9*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cheongsugo_stop, 18.0f));
            case Barcode.GEO /*10*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sujainapt_stop, 18.0f));
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
        ((TextView) findViewById(C0177R.id.text_selected)).setText("\uc2e0\ubc29\ub3d9");
        return super.onCreateOptionsMenu(menu);
    }

    public void onOptionsItemSelected(View view) {
        switch (view.getId()) {
            case C0177R.id.ic_directions_black /*2131558507*/:
                this.newFragment.show(getFragmentManager(), "course");
            case C0177R.id.ic_place_black /*2131558509*/:
                this.newFragment.show(getFragmentManager(), "shinbang");
            default:
        }
    }

    public void onClickBusLocation(View view) {
        if (this.latitude != null && this.longitude != null) {
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue()), 18.0f));
        }
    }
}
