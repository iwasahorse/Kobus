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
import android.view.View;
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

public class DujeongBusStop extends AppCompatActivity implements DialogListener {
    static final LatLng chungmu_stop;
    static final LatLng dujung_stop;
    static final LatLng guseonggs_stop;
    static final LatLng gwanghye_stop;
    static final LatLng jeonjaland_stop;
    static final LatLng kut_stop;
    static final LatLng nambu_stop;
    static final LatLng neulpureun_stop;
    static final LatLng nodongbu_stop;
    static final LatLng samryong_stop;
    static final LatLng sejong_stop;
    static final LatLng seongjeongjiha_stop;
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

    public class UpdateGPSTask extends TimerTask {

        /* renamed from: com.example.busclient.DujeongBusStop.UpdateGPSTask.1 */
        class C01691 implements Runnable {
            C01691() {
            }

            public void run() {
                DujeongBusStop.this.interaction();
                if (DujeongBusStop.this.latitude == null || DujeongBusStop.this.longitude == null) {
                    DujeongBusStop.this.disconnect();
                    return;
                }
                if (DujeongBusStop.this.focused) {
                    DujeongBusStop.this.myPositionMarker.position(new LatLng(Double.valueOf(DujeongBusStop.this.latitude).doubleValue(), Double.valueOf(DujeongBusStop.this.longitude).doubleValue()));
                } else {
                    DujeongBusStop.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(DujeongBusStop.this.latitude).doubleValue(), Double.valueOf(DujeongBusStop.this.longitude).doubleValue()), 18.0f));
                    DujeongBusStop.this.myPositionMarker = new MarkerOptions().position(new LatLng(Double.valueOf(DujeongBusStop.this.latitude).doubleValue(), Double.valueOf(DujeongBusStop.this.longitude).doubleValue())).draggable(false).title("\ubc84\uc2a4 \uc704\uce58").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.ic_bus));
                    DujeongBusStop.this.focused = true;
                }
                if (DujeongBusStop.this.marker != null) {
                    DujeongBusStop.this.marker.remove();
                }
                DujeongBusStop.this.marker = DujeongBusStop.this.mMap.addMarker(DujeongBusStop.this.myPositionMarker);
                DujeongBusStop.this.marker.showInfoWindow();
            }
        }

        public void run() {
            DujeongBusStop.this.runOnUiThread(new C01691());
        }
    }

    /* renamed from: com.example.busclient.DujeongBusStop.1 */
    class C05421 implements OnMarkerClickListener {
        C05421() {
        }

        public boolean onMarkerClick(Marker marker) {
            String markerTitle = marker.getTitle();
            View dialogView = DujeongBusStop.this.getLayoutInflater().inflate(C0177R.layout.default_stop, null);
            Builder builder = new Builder(DujeongBusStop.this.mBusStopLayout.getContext());
            ImageView imageView = (ImageView) dialogView.findViewById(C0177R.id.default_stop);
            boolean z = true;
            switch (markerTitle.hashCode()) {
                case -2128900080:
                    if (markerTitle.equals("\uad6c\uc131\ub3d9 GS\uc8fc\uc720\uc18c")) {
                        z = true;
                        break;
                    }
                    break;
                case -933153171:
                    if (markerTitle.equals("\ub298\ud478\ub978APT")) {
                        z = true;
                        break;
                    }
                    break;
                case -354528962:
                    if (markerTitle.equals("\uad11\ud61c\ub2f9\uc57d\uad6d")) {
                        z = true;
                        break;
                    }
                    break;
                case 7235496:
                    if (markerTitle.equals("\uc131\uc815\uc9c0\ud558\ub3c4")) {
                        z = true;
                        break;
                    }
                    break;
                case 45135935:
                    if (markerTitle.equals("\ub178\ub3d9\ubd80")) {
                        z = true;
                        break;
                    }
                    break;
                case 45998280:
                    if (markerTitle.equals("\ub450\uc815\uc5ed")) {
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
                case 311023496:
                    if (markerTitle.equals("\ub0a8\ubd80\uc624\uac70\ub9ac")) {
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
                case 1576017356:
                    if (markerTitle.equals("\uc804\uc790\ub79c\ub4dc")) {
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
            }
            switch (z) {
                case Phone.UNKNOWN /*0*/:
                    imageView.setImageResource(C0177R.drawable.kut_stop);
                    break;
                case CompletionEvent.STATUS_FAILURE /*1*/:
                    imageView.setImageResource(C0177R.drawable.dujeong_stop);
                    break;
                case CompletionEvent.STATUS_CONFLICT /*2*/:
                    imageView.setImageResource(C0177R.drawable.nodongbu_stop);
                    break;
                case CompletionEvent.STATUS_CANCELED /*3*/:
                    imageView.setImageResource(C0177R.drawable.neulpureun_stop);
                    break;
                case Barcode.PHONE /*4*/:
                    imageView.setImageResource(C0177R.drawable.seongjeongjiha_stop);
                    break;
                case Barcode.PRODUCT /*5*/:
                    imageView.setImageResource(C0177R.drawable.jeonjaland_stop);
                    break;
                case Barcode.SMS /*6*/:
                    imageView.setImageResource(C0177R.drawable.gwanghye_stop);
                    break;
                case Barcode.TEXT /*7*/:
                    imageView.setImageResource(C0177R.drawable.chungmu_stop);
                    break;
                case Barcode.URL /*8*/:
                    imageView.setImageResource(C0177R.drawable.sejong_stop);
                    break;
                case Barcode.WIFI /*9*/:
                    imageView.setImageResource(C0177R.drawable.nambu_stop);
                    break;
                case Barcode.GEO /*10*/:
                    imageView.setImageResource(C0177R.drawable.samryong_stop);
                    break;
                case Barcode.CALENDAR_EVENT /*11*/:
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

    public DujeongBusStop() {
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
        dujung_stop = new LatLng(36.8331664d, 127.1465469d);
        nodongbu_stop = new LatLng(36.8337215d, 127.1333841d);
        neulpureun_stop = new LatLng(36.8290195d, 127.1330813d);
        seongjeongjiha_stop = new LatLng(36.8216146d, 127.1362282d);
        jeonjaland_stop = new LatLng(36.8151009d, 127.1345492d);
        gwanghye_stop = new LatLng(36.8076939d, 127.1326113d);
        chungmu_stop = new LatLng(36.7979027d, 127.1322097d);
        sejong_stop = new LatLng(36.7984135d, 127.1415559d);
        nambu_stop = new LatLng(36.8006114d, 127.1485574d);
        samryong_stop = new LatLng(36.7974828d, 127.1592148d);
        guseonggs_stop = new LatLng(36.7940113d, 127.1598489d);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0177R.layout.bus_stop_main);
        this.mBusStopLayout = (ViewGroup) findViewById(C0177R.id.bus_stop_layout);
        this.mButtonBusLocation = (Button) findViewById(C0177R.id.btn_bus_location);
        setMap();
        connect(6666);
    }

    protected void setMap() {
        if (this.mMap == null) {
            this.mMap = ((MapFragment) getFragmentManager().findFragmentById(C0177R.id.map)).getMap();
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kut_stop, 14.0f));
        }
        setStops();
    }

    public void setStops() {
        MarkerOptions kut_stopMarker = new MarkerOptions().position(kut_stop).draggable(false).title("\ud55c\uae30\ub300").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions dujung_stopMarker = new MarkerOptions().position(dujung_stop).draggable(false).title("\ub450\uc815\uc5ed").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions nodongbu_stopMarker = new MarkerOptions().position(nodongbu_stop).draggable(false).title("\ub178\ub3d9\ubd80").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions nulpulen_stopMarker = new MarkerOptions().position(neulpureun_stop).draggable(false).title("\ub298\ud478\ub978APT").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions sungjungjiha_stopMarker = new MarkerOptions().position(seongjeongjiha_stop).draggable(false).title("\uc131\uc815\uc9c0\ud558\ub3c4").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions junjaland_stopMarker = new MarkerOptions().position(jeonjaland_stop).draggable(false).title("\uc804\uc790\ub79c\ub4dc").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions gaunghea_stopMarker = new MarkerOptions().position(gwanghye_stop).draggable(false).title("\uad11\ud61c\ub2f9\uc57d\uad6d").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions chungmu_stopMarker = new MarkerOptions().position(chungmu_stop).draggable(false).title("\ucda9\ubb34\ubcd1\uc6d0").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions sejong_stopMarker = new MarkerOptions().position(sejong_stop).draggable(false).title("\uc138\uc885\uc544\ud2b8\ube4c\ub77c").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions nambu_stopMarker = new MarkerOptions().position(nambu_stop).draggable(false).title("\ub0a8\ubd80\uc624\uac70\ub9ac").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions samyong_stopMarker = new MarkerOptions().position(samryong_stop).draggable(false).title("\uc0bc\ub8e1\uad50").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions guseonggs_stopMarker = new MarkerOptions().position(guseonggs_stop).draggable(false).title("\uad6c\uc131\ub3d9 GS\uc8fc\uc720\uc18c").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        this.mMap.addMarker(kut_stopMarker).showInfoWindow();
        this.mMap.addMarker(dujung_stopMarker);
        this.mMap.addMarker(nodongbu_stopMarker);
        this.mMap.addMarker(nulpulen_stopMarker);
        this.mMap.addMarker(sungjungjiha_stopMarker);
        this.mMap.addMarker(junjaland_stopMarker);
        this.mMap.addMarker(gaunghea_stopMarker);
        this.mMap.addMarker(chungmu_stopMarker);
        this.mMap.addMarker(sejong_stopMarker);
        this.mMap.addMarker(nambu_stopMarker);
        this.mMap.addMarker(samyong_stopMarker);
        this.mMap.addMarker(guseonggs_stopMarker);
        this.mMap.setOnMarkerClickListener(new C05421());
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
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dujung_stop, 18.0f));
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nodongbu_stop, 18.0f));
            case CompletionEvent.STATUS_CANCELED /*3*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(neulpureun_stop, 18.0f));
            case Barcode.PHONE /*4*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seongjeongjiha_stop, 18.0f));
            case Barcode.PRODUCT /*5*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeonjaland_stop, 18.0f));
            case Barcode.SMS /*6*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gwanghye_stop, 18.0f));
            case Barcode.TEXT /*7*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chungmu_stop, 18.0f));
            case Barcode.URL /*8*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sejong_stop, 18.0f));
            case Barcode.WIFI /*9*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nambu_stop, 18.0f));
            case Barcode.GEO /*10*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(samryong_stop, 18.0f));
            case Barcode.CALENDAR_EVENT /*11*/:
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
        ((TextView) findViewById(C0177R.id.text_selected)).setText("\ub450\uc815\uc5ed");
        return super.onCreateOptionsMenu(menu);
    }

    public void onOptionsItemSelected(View view) {
        switch (view.getId()) {
            case C0177R.id.ic_directions_black /*2131558507*/:
                this.newFragment.show(getFragmentManager(), "course");
            case C0177R.id.ic_place_black /*2131558509*/:
                this.newFragment.show(getFragmentManager(), "dujeong");
            default:
        }
    }

    public void onClickBusLocation(View view) {
        if (this.latitude != null && this.longitude != null) {
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue()), 18.0f));
        }
    }
}
