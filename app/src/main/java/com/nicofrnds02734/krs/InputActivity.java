package com.nicofrnds02734.krs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class InputActivity extends AppCompatActivity {
    EditText kode, nama, sks, na, nh, predikat;
    Button btn_kirim;
    int nilai;
    String tkode, tnama, tsks, tna, tnh, tpred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        kode = findViewById(R.id.kode);
        nama = findViewById(R.id.nama);
        sks = findViewById(R.id.sks);
        na = findViewById(R.id.na);
        nh = findViewById(R.id.nh);
        predikat = findViewById(R.id.predikat);
        btn_kirim = findViewById(R.id.btn_tambah);

        na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nilai = Integer.parseInt(na.getText().toString());
                if (nilai > 92) {
                    nh.setText("A");
                    predikat.setText("A");
                } else if (nilai <= 92 && nilai > 82) {
                    nh.setText("B");
                    predikat.setText("B");
                } else if (nilai <= 82 && nilai > 65) {
                    nh.setText("C");
                    predikat.setText("C");
                } else if (nilai <= 65 && nilai > 55) {
                    nh.setText("D");
                    predikat.setText("D");
                } else {
                    nh.setText("E");
                    predikat.setText("E");
                }
            }
        });

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimData();
            }
        });

    }

    private void kirimData() {
        tkode = kode.getText().toString();
        tnama = nama.getText().toString();
        tsks = sks.getText().toString();
        tna = na.getText().toString();
        tnh = nh.getText().toString();
        tpred = predikat.getText().toString();

        class TambahData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputActivity.this, "Menambahkan...", "Tunggu...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(InputActivity.this, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                kode.setText("");
                nama.setText("");
                sks.setText("");
                na.setText("");
                nh.setText("");
                predikat.setText("");
                loading.cancel();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> ambil = new HashMap<>();
                ambil.put(Konfigurasi.KEY_KODE, tkode);
                ambil.put(Konfigurasi.KEY_NAMA, tnama);
                ambil.put(Konfigurasi.KEY_SKS, tsks);
                ambil.put(Konfigurasi.KEY_NA, tna);
                ambil.put(Konfigurasi.KEY_NH, tnh);
                ambil.put(Konfigurasi.KEY_PRED, tpred);
                RequestHandler requestHandler = new RequestHandler();
                String res = requestHandler.sendPostRequest(Konfigurasi.URL_TAMBAH, ambil);
                return res;
            }
        }
        TambahData tambahData = new TambahData();
        tambahData.execute();
    }
}