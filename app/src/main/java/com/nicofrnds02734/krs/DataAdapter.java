package com.nicofrnds02734.krs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter {
    ArrayList<DataModel> arrayList;
    Context context;

    public DataAdapter(Context context, ArrayList<DataModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_data, parent, false);
        }
        TextView tvNama, tvPredikat, tvKode, tvSks, tvNa, tvNh;
        CardView card;
        tvNama = convertView.findViewById(R.id.tvNama);
        tvPredikat = convertView.findViewById(R.id.tvPredikat);
        tvKode = convertView.findViewById(R.id.tvKode);
        tvSks = convertView.findViewById(R.id.tvSks);
        tvNa = convertView.findViewById(R.id.tvNa);
        tvNh = convertView.findViewById(R.id.tvNh);
        card = convertView.findViewById(R.id.card);

        tvNama.setText(arrayList.get(position).getMatakuliah());
        tvPredikat.setText(arrayList.get(position).getPredikat());
        tvKode.setText(arrayList.get(position).getKode());
        tvSks.setText(arrayList.get(position).getSks());
        tvNa.setText(arrayList.get(position).getNilai_angka());
        tvNh.setText(arrayList.get(position).getNilai_huruf());
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditDataActivity.class);
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("kode", arrayList.get(position).getKode());
                intent.putExtra("nama", arrayList.get(position).getMatakuliah());
                intent.putExtra("sks", arrayList.get(position).getSks());
                intent.putExtra("na", arrayList.get(position).getNilai_angka());
                intent.putExtra("nh", arrayList.get(position).getNilai_huruf());
                intent.putExtra("predikat", arrayList.get(position).getPredikat());
                context.startActivity(intent);
                Toast.makeText(context, "Memilih : " + arrayList.get(position).matakuliah, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
