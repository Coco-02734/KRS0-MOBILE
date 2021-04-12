package com.nicofrnds02734.krs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class EditDataActivity extends AppCompatActivity {
    EditText kode, nama, sks, na, nh, predikat;
    Button btn_kirim, btn_hapus;
    String id, tkode, tnama, tsks, tna, tnh, tpredikat,
            akode, anama, asks, ana, anh, apredikat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        kode = findViewById(R.id.kode);
        nama = findViewById(R.id.nama);
        sks = findViewById(R.id.sks);
        na = findViewById(R.id.na);
        nh = findViewById(R.id.nh);
        predikat = findViewById(R.id.predikat);
        btn_kirim = findViewById(R.id.btn_edit);
        btn_hapus = findViewById(R.id.btn_hapus);

        id = getIntent().getStringExtra("id");
        tkode = getIntent().getStringExtra("kode");
        tnama = getIntent().getStringExtra("nama");
        tsks = getIntent().getStringExtra("sks");
        tna = getIntent().getStringExtra("na");
        tnh = getIntent().getStringExtra("nh");
        tpredikat = getIntent().getStringExtra("predikat");
        kode.setText(tkode);
        nama.setText(tnama);
        sks.setText(tsks);
        na.setText(tna);
        nh.setText(tnh);
        predikat.setText(tpredikat);

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapilDialog();
            }
        });
    }

    private void tapilDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Yakin ingin menghapus data "+ tnama + " ?");
        alert.setMessage("Klik Hapus jika ingin menghapus")
                .setCancelable(false)
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      hapusData();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private void hapusData() {
        class HapusData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditDataActivity.this, "Menghapus Data...", "Tunggu...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(EditDataActivity.this, "Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                kode.setEnabled(false);
                nama.setEnabled(false);
                sks.setEnabled(false);
                na.setEnabled(false);
                nh.setEnabled(false);
                predikat.setEnabled(false);
                loading.cancel();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> ambil = new HashMap<>();
                ambil.put(Konfigurasi.KEY_ID, id);
                RequestHandler requestHandler = new RequestHandler();
                String res = requestHandler.sendPostRequest(Konfigurasi.URL_DELETE, ambil);
                return res;
            }
        }
        HapusData hapusData = new HapusData();
        hapusData.execute();
    }

    private void editData() {

        akode = kode.getText().toString();
        anama = nama.getText().toString();
        asks = sks.getText().toString();
        ana = na.getText().toString();
        anh = nh.getText().toString();
        apredikat = predikat.getText().toString();

        class EditData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditDataActivity.this, "Mengedit...", "Tunggu...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(EditDataActivity.this, "Berhasil Di Edit", Toast.LENGTH_SHORT).show();
                kode.setEnabled(false);
                nama.setEnabled(false);
                sks.setEnabled(false);
                na.setEnabled(false);
                nh.setEnabled(false);
                predikat.setEnabled(false);
                loading.cancel();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> ambil = new HashMap<>();
                ambil.put(Konfigurasi.KEY_ID, id);
                ambil.put(Konfigurasi.KEY_KODE, akode);
                ambil.put(Konfigurasi.KEY_NAMA, anama);
                ambil.put(Konfigurasi.KEY_SKS, asks);
                ambil.put(Konfigurasi.KEY_NA, ana);
                ambil.put(Konfigurasi.KEY_NH, anh);
                ambil.put(Konfigurasi.KEY_PRED, apredikat);
                RequestHandler requestHandler = new RequestHandler();
                String res = requestHandler.sendPostRequest(Konfigurasi.URL_EDIT, ambil);
                return res;
            }
        }
        EditData editData = new EditData();
        editData.execute();
    }
}