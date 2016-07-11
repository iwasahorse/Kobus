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

public class KTXBusStop extends AppCompatActivity implements DialogListener {
    static final LatLng buyeongapt_stop;
    static final LatLng byeoksan_stop;
    static final LatLng hanhwaapt_stop;
    static final LatLng jugong11_stop;
    static final LatLng jugong7_stop;
    static final LatLng ktx_stop;
    static final LatLng kut_stop;
    static final LatLng shinbanggs_stop;
    static final LatLng shinbangrichard_stop;
    static final LatLng ycity_stop;
    static final LatLng yongam_stop;
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

        /* renamed from: com.example.busclient.KTXBusStop.UpdateGPSTask.1 */
        class C01721 implements Runnable {
            C01721() {
            }

            public void run() {
                KTXBusStop.this.interaction();
                if (KTXBusStop.this.latitude == null || KTXBusStop.this.longitude == null) {
                    KTXBusStop.this.disconnect();
                    return;
                }
                if (KTXBusStop.this.focused) {
                    KTXBusStop.this.myPositionMarker.position(new LatLng(Double.valueOf(KTXBusStop.this.latitude).doubleValue(), Double.valueOf(KTXBusStop.this.longitude).doubleValue()));
                } else {
                    KTXBusStop.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(KTXBusStop.this.latitude).doubleValue(), Double.valueOf(KTXBusStop.this.longitude).doubleValue()), 18.0f));
                    KTXBusStop.this.myPositionMarker = new MarkerOptions().position(new LatLng(Double.valueOf(KTXBusStop.this.latitude).doubleValue(), Double.valueOf(KTXBusStop.this.longitude).doubleValue())).draggable(false).title("\ubc84\uc2a4 \uc704\uce58").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.ic_bus));
                    KTXBusStop.this.focused = true;
                }
                if (KTXBusStop.this.marker != null) {
                    KTXBusStop.this.marker.remove();
                }
                KTXBusStop.this.marker = KTXBusStop.this.mMap.addMarker(KTXBusStop.this.myPositionMarker);
                KTXBusStop.this.marker.showInfoWindow();
            }
        }

        public void run() {
            KTXBusStop.this.runOnUiThread(new C01721());
        }
    }

    /* renamed from: com.example.busclient.KTXBusStop.1 */
    class C05431 implements OnMarkerClickListener {
        C05431() {
        }

        public boolean onMarkerClick(Marker marker) {
            String markerTitle = marker.getTitle();
            View dialogView = KTXBusStop.this.getLayoutInflater().inflate(C0177R.layout.default_stop, null);
            Builder builder = new Builder(KTXBusStop.this.mBusStopLayout.getContext());
            ImageView imageView = (ImageView) dialogView.findViewById(C0177R.id.default_stop);
            boolean z = true;
            switch (markerTitle.hashCode()) {
                case -1471205492:
                    if (markerTitle.equals("\uc2e0\ubc29\ub3d9 GS\uc8fc\uc720\uc18c")) {
                        z = true;
                        break;
                    }
                    break;
                case -1062688784:
                    if (markerTitle.equals("\uc2e0\ubc29\ub3d9 \ub9ac\ucc28\ub4dc")) {
                        z = true;
                        break;
                    }
                    break;
                case 74767:
                    if (markerTitle.equals("KTX")) {
                        z = true;
                        break;
                    }
                    break;
                case 1685933:
                    if (markerTitle.equals("Y\uc2dc\ud2f0")) {
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
                case 587941368:
                    if (markerTitle.equals("\ud55c\ud654\uc544\ud30c\ud2b8")) {
                        z = true;
                        break;
                    }
                    break;
                case 1565152135:
                    if (markerTitle.equals("\uc6a9\uc554\ub9c8\uc744")) {
                        z = true;
                        break;
                    }
                    break;
                case 1592942646:
                    if (markerTitle.equals("\uc8fc\uacf57\ub2e8\uc9c0")) {
                        z = true;
                        break;
                    }
                    break;
                case 2092306673:
                    if (markerTitle.equals("\uc8fc\uacf511\ub2e8\uc9c0")) {
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
                    imageView.setImageResource(C0177R.drawable.jugong11_stop);
                    break;
                case CompletionEvent.STATUS_CONFLICT /*2*/:
                    imageView.setImageResource(C0177R.drawable.ktx_stop);
                    break;
                case CompletionEvent.STATUS_CANCELED /*3*/:
                    imageView.setImageResource(C0177R.drawable.ycity_stop);
                    break;
                case Barcode.PHONE /*4*/:
                    imageView.setImageResource(C0177R.drawable.hanhwaapt_stop);
                    break;
                case Barcode.PRODUCT /*5*/:
                    imageView.setImageResource(C0177R.drawable.yongam_stop);
                    break;
                case Barcode.SMS /*6*/:
                    imageView.setImageResource(C0177R.drawable.jugong7_stop);
                    break;
                case Barcode.TEXT /*7*/:
                    imageView.setImageResource(C0177R.drawable.shinbangrichard_stop);
                    break;
                case Barcode.URL /*8*/:
                    imageView.setImageResource(C0177R.drawable.shinbanggs_stop);
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

    public KTXBusStop() {
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
        jugong11_stop = new LatLng(36.818183d, 127.1170883d);
        ktx_stop = new LatLng(36.7942999d, 127.1037118d);
        ycity_stop = new LatLng(36.795915d, 127.1084903d);
        hanhwaapt_stop = new LatLng(36.8015872d, 127.1136798d);
        yongam_stop = new LatLng(36.8009305d, 127.1189621d);
        jugong7_stop = new LatLng(36.800632d, 127.121471d);
        shinbangrichard_stop = new LatLng(36.7926876d, 127.1286888d);
        shinbanggs_stop = new LatLng(36.7927899d, 127.1288492d);
        byeoksan_stop = new LatLng(36.7810184d, 127.1590985d);
        buyeongapt_stop = new LatLng(36.7617777d, 127.1728626d);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0177R.layout.bus_stop_main);
        this.mBusStopLayout = (ViewGroup) findViewById(C0177R.id.bus_stop_layout);
        this.mButtonBusLocation = (Button) findViewById(C0177R.id.btn_bus_location);
        setMap();
        connect(5555);
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
        MarkerOptions jugong11_stopMarker = new MarkerOptions().position(jugong11_stop).draggable(false).title("\uc8fc\uacf511\ub2e8\uc9c0").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions ktx_stopMarker = new MarkerOptions().position(ktx_stop).draggable(false).title("KTX").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions ycity_stopMarker = new MarkerOptions().position(ycity_stop).draggable(false).title("Y\uc2dc\ud2f0").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions hanhwaapt_stopMarker = new MarkerOptions().position(hanhwaapt_stop).draggable(false).title("\ud55c\ud654\uc544\ud30c\ud2b8").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions yongam_stopMarker = new MarkerOptions().position(yongam_stop).draggable(false).title("\uc6a9\uc554\ub9c8\uc744").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions jugong7_stopMarker = new MarkerOptions().position(jugong7_stop).draggable(false).title("\uc8fc\uacf57\ub2e8\uc9c0").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions shinbangrichard_stopMarker = new MarkerOptions().position(shinbangrichard_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 \ub9ac\ucc28\ub4dc").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        MarkerOptions shinbanggs_stopMarker = new MarkerOptions().position(shinbanggs_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 GS\uc8fc\uc720\uc18c").icon(BitmapDescriptorFactory.fromResource(C0177R.drawable.busstop));
        this.mMap.addMarker(kut_stopMarker).showInfoWindow();
        this.mMap.addMarker(jugong11_stopMarker);
        this.mMap.addMarker(ktx_stopMarker);
        this.mMap.addMarker(ycity_stopMarker);
        this.mMap.addMarker(hanhwaapt_stopMarker);
        this.mMap.addMarker(yongam_stopMarker);
        this.mMap.addMarker(jugong7_stopMarker);
        this.mMap.addMarker(shinbangrichard_stopMarker);
        this.mMap.addMarker(shinbanggs_stopMarker);
        this.mMap.setOnMarkerClickListener(new C05431());
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
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jugong11_stop, 18.0f));
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ktx_stop, 18.0f));
            case CompletionEvent.STATUS_CANCELED /*3*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ycity_stop, 18.0f));
            case Barcode.PHONE /*4*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hanhwaapt_stop, 18.0f));
            case Barcode.PRODUCT /*5*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yongam_stop, 18.0f));
            case Barcode.SMS /*6*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jugong7_stop, 18.0f));
            case Barcode.TEXT /*7*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbangrichard_stop, 18.0f));
            case Barcode.URL /*8*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbanggs_stop, 18.0f));
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
        ((TextView) findViewById(C0177R.id.text_selected)).setText("\uace0\uc18d\ucca0");
        return super.onCreateOptionsMenu(menu);
    }

    public void onOptionsItemSelected(View view) {
        switch (view.getId()) {
            case C0177R.id.ic_directions_black /*2131558507*/:
                this.newFragment.show(getFragmentManager(), "course");
            case C0177R.id.ic_place_black /*2131558509*/:
                this.newFragment.show(getFragmentManager(), "ktx");
            default:
        }
    }

    public void onClickBusLocation(View view) {
        if (this.latitude != null && this.longitude != null) {
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(this.latitude).doubleValue(), Double.valueOf(this.longitude).doubleValue()), 18.0f));
        }
    }
}
