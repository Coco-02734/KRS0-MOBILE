package com.nicofrnds02734.krs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class KartuActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<DataModel> arrayList;
    SwipeRefreshLayout ulangi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu);

        listView =  findViewById(R.id.listdata);
        ulangi = findViewById(R.id.swipe);

        ulangi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new TampilkanData().execute();
            }
        });
        arrayList = new ArrayList<>();
    }

    public void RefreshData(){
        new TampilkanData().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new TampilkanData().execute();
    }

    public  class TampilkanData extends AsyncTask<Void, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ulangi.setRefreshing(true);
        }

        @Override
        protected String doInBackground(Void... voids) {
            arrayList.clear();
            try {
                URL url = new URL("http://192.168.1.68/khs_api/ambildata.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String json;
                while((json = bufferedReader.readLine()) != null){
                    sb.append(json + "\n");
                }
                return sb.toString().trim();
            }catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                ulangi.setRefreshing(false);
                LoadIntoListView(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void LoadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] stok = new String[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String id = object.getString("id");
            String kode = object.getString("kode");
            String matakuliah = object.getString("matakuliah");
            String sks = object.getString("sks");
            String nilai_angka = object.getString("nilai_angka");
            String nilai_huruf = object.getString("nilai_huruf");
            String predikat = object.getString("predikat");

            DataModel model = new DataModel();
            model.setId(id);
            model.setKode(kode);
            model.setMatakuliah(matakuliah);
            model.setSks(sks);
            model.setNilai_angka(nilai_angka);
            model.setNilai_huruf(nilai_huruf);
            model.setPredikat(predikat);
            arrayList.add(model);
        }
        DataAdapter adapter = new DataAdapter(KartuActivity.this, arrayList);
        listView.setAdapter(adapter);
    }
}