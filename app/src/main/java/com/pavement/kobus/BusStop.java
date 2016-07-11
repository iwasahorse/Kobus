package com.pavement.kobus;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.Barcode.Phone;

public class BusStop extends AppCompatActivity implements CourseDialogFragment.DialogListener {
    static final LatLng buyeongapt_stop;
    static final LatLng byeoksan_stop;
    static final LatLng cheonanstation_stop;
    static final LatLng cheongsugo_stop;
    static final LatLng chungmu_stop;
    static final LatLng dongil_stop;
    static final LatLng dongwoo_stop;
    static final LatLng dujung_stop;
    static final LatLng guseonggs_stop;
    static final LatLng gwanghye_stop;
    static final LatLng hanhwaapt_stop;
    static final LatLng jeilgo_stop;
    static final LatLng jeonjaland_stop;
    static final LatLng jugong11_stop;
    static final LatLng jugong7_stop;
    static final LatLng ktx_stop;
    static final LatLng kut_stop;
    static final LatLng nambu_stop;
    static final LatLng neulpureun_stop;
    static final LatLng nodongbu_stop;
    static final LatLng samryong_stop;
    static final LatLng sejong_stop;
    static final LatLng seonghwang_stop;
    static final LatLng seongjeongjiha_stop;
    static final LatLng shinbanggs_stop;
    static final LatLng shinbanghyundai_stop;
    static final LatLng shinbangmg_stop;
    static final LatLng shinbangrichard_stop;
    static final LatLng singea_stop;
    static final LatLng sujainapt_stop;
    static final LatLng terminal_stop;
    static final LatLng wongseonggs_stop;
    static final LatLng wonjeon_stop;
    static final LatLng ycity_stop;
    static final LatLng yongam_stop;
    static final LatLng yongchun_stop;
    private ViewGroup mBusStopLayout;
    private GoogleMap mMap;
    private final CourseDialogFragment newFragment;

    /* renamed from: com.pavement.kobus.BusStop.1 */
    class C05391 implements OnMapReadyCallback {
        C05391() {
        }

        public void onMapReady(GoogleMap googleMap) {
            BusStop.this.mMap = googleMap;
            BusStop.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BusStop.kut_stop, 14.0f));
            BusStop.this.setStops();
        }
    }

    /* renamed from: com.pavement.kobus.BusStop.2 */
    class C05402 implements OnMarkerClickListener {
        C05402() {
        }

        public boolean onMarkerClick(Marker marker) {
            /*String markerTitle = marker.getTitle();
            View dialogView = BusStop.this.getLayoutInflater().inflate(R.layout.default_stop, null);
            Builder builder = new Builder(BusStop.this.mBusStopLayout.getContext());
            ImageView imageView = (ImageView) dialogView.findViewById(R.id.default_stop);
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
                case -1471205492:
                    if (markerTitle.equals("\uc2e0\ubc29\ub3d9 GS\uc8fc\uc720\uc18c")) {
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
                case -1062688784:
                    if (markerTitle.equals("\uc2e0\ubc29\ub3d9 \ub9ac\ucc28\ub4dc")) {
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
                case -902510544:
                    if (markerTitle.equals("\uc131\ud669\ub3d9 \uc2e0\ud611")) {
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
                case 51953473:
                    if (markerTitle.equals("\ucc9c\uc548\uc5ed")) {
                        z = true;
                        break;
                    }
                    break;
                case 53000520:
                    if (markerTitle.equals("\ud130\ubbf8\ub110")) {
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
                case 587941368:
                    if (markerTitle.equals("\ud55c\ud654\uc544\ud30c\ud2b8")) {
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
                case 1145641741:
                    if (markerTitle.equals("\ub3d9\uc77c\ud558\uc774\ube4c")) {
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
                case 1576017356:
                    if (markerTitle.equals("\uc804\uc790\ub79c\ub4dc")) {
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
                case 1616070666:
                    if (markerTitle.equals("\ucda9\ubb34\ubcd1\uc6d0")) {
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
                case 2143934160:
                    if (markerTitle.equals("\ud55c\uc591 \uc218\uc790\uc778APT")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case Phone.UNKNOWN *//*0*//*:
                    imageView.setImageResource(R.drawable.kut_stop);
                    break;
                case CompletionEvent.STATUS_FAILURE *//*1*//*:
                    imageView.setImageResource(R.drawable.dujeong_stop);
                    break;
                case CompletionEvent.STATUS_CONFLICT *//*2*//*:
                    imageView.setImageResource(R.drawable.jugong11_stop);
                    break;
                case CompletionEvent.STATUS_CANCELED *//*3*//*:
                    imageView.setImageResource(R.drawable.dongil_stop);
                    break;
                case Barcode.PHONE *//*4*//*:
                    break;
                case Barcode.PRODUCT *//*5*//*:
                    imageView.setImageResource(R.drawable.neulpureun_stop);
                    break;
                case Barcode.SMS *//*6*//*:
                    imageView.setImageResource(R.drawable.seongjeongjiha_stop);
                    break;
                case Barcode.TEXT *//*7*//*:
                    imageView.setImageResource(R.drawable.jeonjaland_stop);
                    break;
                case Barcode.URL *//*8*//*:
                    imageView.setImageResource(R.drawable.kut_stop);
                    break;
                case Barcode.WIFI *//*9*//*:
                    imageView.setImageResource(R.drawable.shinbanghyendea_stop);
                    break;
                case Barcode.GEO *//*10*//*:
                    imageView.setImageResource(R.drawable.shinbangmg_stop);
                    break;
                case Barcode.CALENDAR_EVENT *//*11*//*:
                    imageView.setImageResource(R.drawable.ktx_stop);
                    break;
                case Barcode.DRIVER_LICENSE *//*12*//*:
                    imageView.setImageResource(R.drawable.ycity_stop);
                    break;
                case ConnectionsStatusCodes.STATUS_ERROR *//*13*//*:
                    imageView.setImageResource(R.drawable.terminal_stop);
                    break;
                case Place.TYPE_BUS_STATION *//*14*//*:
                    imageView.setImageResource(R.drawable.cheonanstation_stop);
                    break;
                case Place.TYPE_CAFE *//*15*//*:
                    imageView.setImageResource(R.drawable.hanhwaapt_stop);
                    break;
                case Barcode.DATA_MATRIX *//*16*//*:
                    imageView.setImageResource(R.drawable.yongam_stop);
                    break;
                case Place.TYPE_CAR_DEALER *//*17*//*:
                    imageView.setImageResource(R.drawable.chungmu_stop);
                    break;
                case Place.TYPE_CAR_RENTAL *//*18*//*:
                    imageView.setImageResource(R.drawable.jugong7_stop);
                    break;
                case Place.TYPE_CAR_REPAIR *//*19*//*:
                    imageView.setImageResource(R.drawable.sejong_stop);
                    break;
                case Place.TYPE_CAR_WASH *//*20*//*:
                    imageView.setImageResource(R.drawable.seonghwang_stop);
                    break;
                case Place.TYPE_CASINO *//*21*//*:
                    imageView.setImageResource(R.drawable.shinbangrichard_stop);
                    break;
                case Place.TYPE_CEMETERY *//*22*//*:
                    imageView.setImageResource(R.drawable.shinbanggs_stop);
                    break;
                case Place.TYPE_CHURCH *//*23*//*:
                    imageView.setImageResource(R.drawable.jeilgo_stop);
                    break;
                case Place.TYPE_CITY_HALL *//*24*//*:
                    imageView.setImageResource(R.drawable.nambu_stop);
                    break;
                case Place.TYPE_CLOTHING_STORE *//*25*//*:
                    imageView.setImageResource(R.drawable.wongseonggs_stop);
                    break;
                case Place.TYPE_CONVENIENCE_STORE *//*26*//*:
                    imageView.setImageResource(R.drawable.samryong_stop);
                    break;
                case Place.TYPE_COURTHOUSE *//*27*//*:
                    imageView.setImageResource(R.drawable.guseonggs_stop);
                    break;
                case Place.TYPE_DENTIST *//*28*//*:
                    imageView.setImageResource(R.drawable.cheongsugo_stop);
                    break;
                case Place.TYPE_DEPARTMENT_STORE *//*29*//*:
                    imageView.setImageResource(R.drawable.sujainapt_stop);
                    break;
                default:
                    return false;
            }
            imageView.setImageResource(R.drawable.nodongbu_stop);
            builder.setView(dialogView);
            builder.setTitle(markerTitle);
            Event.onAlarmOptionSelected(builder, dialogView, marker);
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();*/
            return false;
        }
    }

    public BusStop() {
        this.newFragment = new CourseDialogFragment();
    }

    static {
        kut_stop = new LatLng(36.76481d, 127.282089d);
        dujung_stop = new LatLng(36.8331664d, 127.1465469d);
        jugong11_stop = new LatLng(36.818183d, 127.1170883d);
        nodongbu_stop = new LatLng(36.8337215d, 127.1333841d);
        neulpureun_stop = new LatLng(36.8290195d, 127.1330813d);
        seongjeongjiha_stop = new LatLng(36.8216146d, 127.1362282d);
        jeonjaland_stop = new LatLng(36.8151009d, 127.1345492d);
        gwanghye_stop = new LatLng(36.8076939d, 127.1326113d);
        shinbanghyundai_stop = new LatLng(36.7891664d, 127.1289787d);
        shinbangmg_stop = new LatLng(36.792067d, 127.12909d);
        ktx_stop = new LatLng(36.7942999d, 127.1037118d);
        ycity_stop = new LatLng(36.795915d, 127.1084903d);
        dongil_stop = new LatLng(36.7859225d, 127.1146038d);
        terminal_stop = new LatLng(36.8190056d, 127.1552321d);
        cheonanstation_stop = new LatLng(36.8081447d, 127.1475566d);
        hanhwaapt_stop = new LatLng(36.8015872d, 127.1136798d);
        yongam_stop = new LatLng(36.8009305d, 127.1189621d);
        chungmu_stop = new LatLng(36.7979027d, 127.1322097d);
        jugong7_stop = new LatLng(36.800632d, 127.121471d);
        sejong_stop = new LatLng(36.7984135d, 127.1415559d);
        seonghwang_stop = new LatLng(36.8156914d, 127.150162d);
        shinbangrichard_stop = new LatLng(36.7926876d, 127.1286888d);
        shinbanggs_stop = new LatLng(36.7927899d, 127.1288492d);
        jeilgo_stop = new LatLng(36.8062039d, 127.1558579d);
        nambu_stop = new LatLng(36.8006114d, 127.1485574d);
        wongseonggs_stop = new LatLng(36.8052247d, 127.1586144d);
        samryong_stop = new LatLng(36.7974828d, 127.1592148d);
        byeoksan_stop = new LatLng(36.7810184d, 127.1590985d);
        buyeongapt_stop = new LatLng(36.7617777d, 127.1728626d);
        guseonggs_stop = new LatLng(36.7940113d, 127.1598489d);
        cheongsugo_stop = new LatLng(36.7904561d, 127.1594437d);
        sujainapt_stop = new LatLng(36.7833849d, 127.1522386d);
        dongwoo_stop = new LatLng(36.7617777d, 127.1728626d);
        singea_stop = new LatLng(36.7940113d, 127.1598489d);
        wonjeon_stop = new LatLng(0.0d, 0.0d);
        yongchun_stop = new LatLng(0.0d, 0.0d);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.bus_stop_main);
        this.mBusStopLayout = (ViewGroup) findViewById(R.id.bus_stop_layout);
        ((Button) findViewById(R.id.btn_bus_location)).setVisibility(8);
        setMap();
    }

    protected void setMap() {
        if (this.mMap == null) {
            ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(new C05391());
        }
    }

    public void setStops() {
        MarkerOptions kut_stopMarker = new MarkerOptions().position(kut_stop).draggable(false).title("\ud55c\uae30\ub300").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions dujung_stopMarker = new MarkerOptions().position(dujung_stop).draggable(false).title("\ub450\uc815\uc5ed").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions jugong11_stopMarker = new MarkerOptions().position(jugong11_stop).draggable(false).title("\uc8fc\uacf511\ub2e8\uc9c0").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions dongil_stopMarker = new MarkerOptions().position(dongil_stop).draggable(false).title("\ub3d9\uc77c\ud558\uc774\ube4c").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions nodongbu_stopMarker = new MarkerOptions().position(nodongbu_stop).draggable(false).title("\ub178\ub3d9\ubd80").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions nulpulen_stopMarker = new MarkerOptions().position(neulpureun_stop).draggable(false).title("\ub298\ud478\ub978APT").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions sungjungjiha_stopMarker = new MarkerOptions().position(seongjeongjiha_stop).draggable(false).title("\uc131\uc815\uc9c0\ud558\ub3c4").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions junjaland_stopMarker = new MarkerOptions().position(jeonjaland_stop).draggable(false).title("\uc804\uc790\ub79c\ub4dc").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions gaunghea_stopMarker = new MarkerOptions().position(gwanghye_stop).draggable(false).title("\uad11\ud61c\ub2f9\uc57d\uad6d").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions sinbanghyendea_stopMarker = new MarkerOptions().position(shinbanghyundai_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 \ud604\ub300\uc544\ud30c\ud2b8").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions sinbangmg_stopMarker = new MarkerOptions().position(shinbangmg_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 \uc0c8\ub9c8\uc744\uae08\uace0").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions ktx_stopMarker = new MarkerOptions().position(ktx_stop).draggable(false).title("KTX").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions ycity_stopMarker = new MarkerOptions().position(ycity_stop).draggable(false).title("Y\uc2dc\ud2f0").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions terminal_stopMarker = new MarkerOptions().position(terminal_stop).draggable(false).title("\ud130\ubbf8\ub110").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions cheonanstation_stopMarker = new MarkerOptions().position(cheonanstation_stop).draggable(false).title("\ucc9c\uc548\uc5ed").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions yongam_stopMarker = new MarkerOptions().position(yongam_stop).draggable(false).title("\uc6a9\uc554\ub9c8\uc744").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions hanhwaapt_stopMarker = new MarkerOptions().position(hanhwaapt_stop).draggable(false).title("\ud55c\ud654\uc544\ud30c\ud2b8").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions chungmu_stopMarker = new MarkerOptions().position(chungmu_stop).draggable(false).title("\ucda9\ubb34\ubcd1\uc6d0").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions jugong7_stopMarker = new MarkerOptions().position(jugong7_stop).draggable(false).title("\uc8fc\uacf57\ub2e8\uc9c0").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions BSseojong_stopMarker = new MarkerOptions().position(sejong_stop).draggable(false).title("\uc138\uc885\uc544\ud2b8\ube4c\ub77c").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions seonghwang_stopMarker = new MarkerOptions().position(seonghwang_stop).draggable(false).title("\uc131\ud669\ub3d9 \uc2e0\ud611").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions shinbangrichard_stopMarker = new MarkerOptions().position(shinbangrichard_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 \ub9ac\ucc28\ub4dc").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions shinbanggs_stopMarker = new MarkerOptions().position(shinbanggs_stop).draggable(false).title("\uc2e0\ubc29\ub3d9 GS\uc8fc\uc720\uc18c").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions jeailgo_stopMarker = new MarkerOptions().position(jeilgo_stop).draggable(false).title("\uc81c\uc77c\uace0 \ub9de\uc740\ud3b8").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions nambu_stopMarker = new MarkerOptions().position(nambu_stop).draggable(false).title("\ub0a8\ubd80\uc624\uac70\ub9ac").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions wongseonggs_stopMarker = new MarkerOptions().position(wongseonggs_stop).draggable(false).title("\uc6d0\uc131\ub3d9 GS\ub9c8\ud2b8").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions samyong_stopMarker = new MarkerOptions().position(samryong_stop).draggable(false).title("\uc0bc\ub8e1\uad50").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions guseonggs_stopMarker = new MarkerOptions().position(guseonggs_stop).draggable(false).title("\uad6c\uc131\ub3d9 GS\uc8fc\uc720\uc18c").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions cheongsugo_stopMarker = new MarkerOptions().position(cheongsugo_stop).draggable(false).title("\uccad\uc218\uace0BS \uc55e\ucabd").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        MarkerOptions sujainapt_stopMarker = new MarkerOptions().position(sujainapt_stop).draggable(false).title("\ud55c\uc591 \uc218\uc790\uc778APT").icon(BitmapDescriptorFactory.fromResource(R.drawable.busstop));
        this.mMap.addMarker(kut_stopMarker).showInfoWindow();
        this.mMap.addMarker(dujung_stopMarker);
        this.mMap.addMarker(jugong11_stopMarker);
        this.mMap.addMarker(nodongbu_stopMarker);
        this.mMap.addMarker(nulpulen_stopMarker);
        this.mMap.addMarker(sungjungjiha_stopMarker);
        this.mMap.addMarker(junjaland_stopMarker);
        this.mMap.addMarker(gaunghea_stopMarker);
        this.mMap.addMarker(sinbanghyendea_stopMarker);
        this.mMap.addMarker(sinbanghyendea_stopMarker);
        this.mMap.addMarker(ktx_stopMarker);
        this.mMap.addMarker(ycity_stopMarker);
        this.mMap.addMarker(sinbangmg_stopMarker);
        this.mMap.addMarker(dongil_stopMarker);
        this.mMap.addMarker(terminal_stopMarker);
        this.mMap.addMarker(cheonanstation_stopMarker);
        this.mMap.addMarker(hanhwaapt_stopMarker);
        this.mMap.addMarker(yongam_stopMarker);
        this.mMap.addMarker(chungmu_stopMarker);
        this.mMap.addMarker(jugong7_stopMarker);
        this.mMap.addMarker(BSseojong_stopMarker);
        this.mMap.addMarker(seonghwang_stopMarker);
        this.mMap.addMarker(shinbangrichard_stopMarker);
        this.mMap.addMarker(shinbanggs_stopMarker);
        this.mMap.addMarker(jeailgo_stopMarker);
        this.mMap.addMarker(nambu_stopMarker);
        this.mMap.addMarker(wongseonggs_stopMarker);
        this.mMap.addMarker(samyong_stopMarker);
        this.mMap.addMarker(guseonggs_stopMarker);
        this.mMap.addMarker(cheongsugo_stopMarker);
        this.mMap.addMarker(sujainapt_stopMarker);
        this.mMap.setOnMarkerClickListener(new C05402());
    }

    public void onCourseSelected(DialogFragment dialog, int selected) {
        Intent intent = null;
        System.out.println("selected: " + selected);
        switch (selected) {
            case Phone.UNKNOWN /*0*/:
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
                intent = new Intent(this, ShinbangBusStop.class);
                break;
            default:
                return;
        }
        if (intent != null) {
            startActivity(intent);
        }
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
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jugong11_stop, 18.0f));
            case CompletionEvent.STATUS_CANCELED /*3*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nodongbu_stop, 18.0f));
            case Barcode.PHONE /*4*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(neulpureun_stop, 18.0f));
            case Barcode.PRODUCT /*5*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seongjeongjiha_stop, 18.0f));
            case Barcode.SMS /*6*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeonjaland_stop, 18.0f));
            case Barcode.TEXT /*7*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gwanghye_stop, 18.0f));
            case Barcode.URL /*8*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbanghyundai_stop, 18.0f));
            case Barcode.WIFI /*9*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbangmg_stop, 18.0f));
            case Barcode.GEO /*10*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ktx_stop, 18.0f));
            case Barcode.CALENDAR_EVENT /*11*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ycity_stop, 18.0f));
            case Barcode.DRIVER_LICENSE /*12*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(terminal_stop, 18.0f));
            case ConnectionsStatusCodes.STATUS_ERROR /*13*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cheonanstation_stop, 18.0f));
            case Place.TYPE_BUS_STATION /*14*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hanhwaapt_stop, 18.0f));
            case Place.TYPE_CAFE /*15*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yongam_stop, 18.0f));
            case Barcode.DATA_MATRIX /*16*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chungmu_stop, 18.0f));
            case Place.TYPE_CAR_DEALER /*17*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jugong7_stop, 18.0f));
            case Place.TYPE_CAR_RENTAL /*18*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sejong_stop, 18.0f));
            case Place.TYPE_CAR_REPAIR /*19*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seonghwang_stop, 18.0f));
            case Place.TYPE_CAR_WASH /*20*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbangrichard_stop, 18.0f));
            case Place.TYPE_CASINO /*21*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shinbanggs_stop, 18.0f));
            case Place.TYPE_CEMETERY /*22*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeilgo_stop, 18.0f));
            case Place.TYPE_CHURCH /*23*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nambu_stop, 18.0f));
            case Place.TYPE_CITY_HALL /*24*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wongseonggs_stop, 18.0f));
            case Place.TYPE_CLOTHING_STORE /*25*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(samryong_stop, 18.0f));
            case Place.TYPE_CONVENIENCE_STORE /*26*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(guseonggs_stop, 18.0f));
            case Place.TYPE_COURTHOUSE /*27*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cheongsugo_stop, 18.0f));
            case Place.TYPE_DENTIST /*28*/:
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sujainapt_stop, 18.0f));
            default:
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, this.mBusStopLayout, false);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
      //  ((TextView) findViewById(R.id.text_selected)).setText("\uc804\uccb4 \uc815\ub958\uc7a5");
        return super.onCreateOptionsMenu(menu);
    }

    public void onOptionsItemSelected(View view) {
        
        switch (view.getId()) {
            /*case R.id.ic_directions_black:
                this.newFragment.show(getFragmentManager(), "course");
            case R.id.ic_place_black:
                this.newFragment.show(getFragmentManager(), "all");*/
            default:
        }
    }

    public void onClickBusLocation(View view) {
        System.out.println("do nothing");
    }
}
