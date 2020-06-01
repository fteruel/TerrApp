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
    //Creo un separador Auxiliar para manipular Strings
    private final String SEPARADOR_LUGAR = " of ";

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

        //parceo los Strings para obtener lo que queda dividido por el separador

        String lugarCompleto = terremotoActual.getmLugar();

        String lugar;
        String distancia;

        if(lugarCompleto.contains(SEPARADOR_LUGAR)){
            String[] partes = lugarCompleto.split(SEPARADOR_LUGAR);
            distancia = partes[0];
            lugar = partes[1];
        }else{
            distancia = getContext().getString(R.string.cerca_de);
            lugar = lugarCompleto;
        }


        //Traigo los TextView
        TextView magnitudTV = ItemLista.findViewById(R.id.magnitudTV);
        TextView lugarTV = ItemLista.findViewById(R.id.lugarTV);
        TextView fechaTv = ItemLista.findViewById(R.id.fechaTV);
        TextView distanciaLugarTV = ItemLista.findViewById(R.id.distanciaLugarTV);

        //Seteo los valores correspondientes
        magnitudTV.setText(terremotoActual.getmMagnitud());
        lugarTV.setText(lugar);
        fechaTv.setText(terremotoActual.getmFecha());
        distanciaLugarTV.setText(distancia);





        return ItemLista;
    }
}
