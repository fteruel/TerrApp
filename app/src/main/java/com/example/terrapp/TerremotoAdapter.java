package com.example.terrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


//Hago un Contructor que Toma el contexto y una lista de terremotos y crea el ArrayAdapter
public class TerremotoAdapter extends ArrayAdapter<Terrremoto> {
    public TerremotoAdapter(Context context, List<Terrremoto> terremotos) {
        super(context, 0, terremotos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ItemLista = convertView;
        if(ItemLista == null){
            ItemLista = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.terremoto_item_lista,parent, false );
        }

        //Obtengo el terremoto Actual
        Terrremoto terremotoActual = getItem(position);


        //Traigo los TextView
        TextView magnitudTV = ItemLista.findViewById(R.id.magnitudTV);
        TextView lugarTV = ItemLista.findViewById(R.id.lugarTV);
        TextView fechaTv = ItemLista.findViewById(R.id.fechaTV);

        //Seteo los valores correspondientes
        magnitudTV.setText(terremotoActual.getmMagnitud());
        lugarTV.setText(terremotoActual.getmLugar());
        fechaTv.setText(terremotoActual.getmFecha());


        return ItemLista;
    }
}
