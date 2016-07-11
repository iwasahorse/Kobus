package com.pavement.kobus;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.games.GamesStatusCodes;
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

public class PassengerNumbers extends Activity {
    String count;
    private BufferedReader in;
    private PrintWriter out;
    Button search;
    private Socket socket;
    private Spinner spinner;
    TextView textView;
    private Timer timer;

    /* renamed from: com.example.busclient.PassengerNumbers.1 */
    class C01761 implements OnClickListener {
        C01761() {
        }

        public void onClick(View v) {
            PassengerNumbers.this.connect();
        }
    }

    public PassengerNumbers() {
        this.timer = null;
        this.count = "0";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0177R.layout.activity_passengers);
        this.search = (Button) findViewById(C0177R.id.search);
        this.textView = (TextView) findViewById(C0177R.id.textView);
        this.spinner = (Spinner) findViewById(C0177R.id.spinner_course);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, C0177R.array.array_course2, 17367050);
        adapter.setDropDownViewResource(17367049);
        this.spinner.setAdapter(adapter);
        this.search.setOnClickListener(new C01761());
    }

    public void connect() {
        int portNumber;
        String hostName = "218.150.181.119";
        StrictMode.enableDefaults();
        switch (this.spinner.getSelectedItemPosition()) {
            case Phone.UNKNOWN /*0*/:
                portNumber = 8888;
                break;
            case CompletionEvent.STATUS_FAILURE /*1*/:
                portNumber = 7777;
                break;
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                portNumber = 6666;
                break;
            case CompletionEvent.STATUS_CANCELED /*3*/:
                portNumber = 5555;
                break;
            case Barcode.PHONE /*4*/:
                portNumber = 4444;
                break;
            default:
                portNumber = 40404;
                break;
        }
        try {
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(hostName, portNumber), GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS);
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            interaction();
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostName);
        } catch (IOException e2) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            Toast.makeText(this, "\ubc84\uc2a4\uac00 \uc6b4\ud589\uc911\uc778 \uc0c1\ud0dc\uac00 \uc544\ub2d9\ub2c8\ub2e4", 0).show();
        }
    }

    public void interaction() {
        try {
            this.out.println(3);
            this.count = this.in.readLine();
            try {
                Integer.valueOf(this.count);
            } catch (NumberFormatException e) {
                this.count = "0";
            }
            this.textView.setText(this.count);
            disconnect();
        } catch (IOException e2) {
            e2.printStackTrace();
            System.err.println("\uc11c\ubc84\ub85c\ubd80\ud130 \uc751\ub2f5\uc744 \ubc1b\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
