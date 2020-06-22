package com.example.terrapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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



        //---------------------------Seccion parseo ------------------------------



        //creo un objeto Date con el timepo completo en milisegundos

        Date tiempoObj = new Date(terremotoActual.getmTiempo());

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

        String fechaFormateada =  dateFormat.format(tiempoObj);
        String horaFormateada = timeFormat.format(tiempoObj);


        //parseo los Strings para obtener lo que queda dividido por el separador


        String lugarCompleto = terremotoActual.getmLugar();
        String lugar;
        String distancia;

        //si el String contiene " of " se que estoy en un caso donde esta la distancia esta esctrita como
        // "2k of Lugar"
        if(lugarCompleto.contains(SEPARADOR_LUGAR)){
            String[] partes = lugarCompleto.split(SEPARADOR_LUGAR);
            distancia = partes[0];
            lugar = partes[1];
        }else{
            distancia = getContext().getString(R.string.cerca_de);
            lugar = lugarCompleto;
        }


        //parseo y formateo magnitud

        Double magnitudActual = terremotoActual.getmMagnitud();
        DecimalFormat formatoMagnitud = new DecimalFormat("0.0");
        String magnitudFormateado = formatoMagnitud.format(magnitudActual);



        //---------------------Seccion carga de datos----------------------------------


        //Traigo los TextView
        TextView magnitudTV = ItemLista.findViewById(R.id.magnitudTV);
        TextView lugarTV = ItemLista.findViewById(R.id.lugarTV);
        TextView fechaTv = ItemLista.findViewById(R.id.fechaTV);
        TextView horaTV = ItemLista.findViewById(R.id.horaTV);
        TextView distanciaLugarTV = ItemLista.findViewById(R.id.distanciaLugarTV);



        //Seteo los valores correspondientes
        magnitudTV.setText(magnitudFormateado);
        lugarTV.setText(lugar);
        fechaTv.setText(fechaFormateada);
        horaTV.setText(horaFormateada);
        distanciaLugarTV.setText(distancia);



        //cambio color de ciculo
        GradientDrawable circuloMagnitud = (GradientDrawable) magnitudTV.getBackground();
        int colorMagnitud = getColorMagnitud(terremotoActual.getmMagnitud());

        circuloMagnitud.setColor(colorMagnitud);


        return ItemLista;
    }


    //auxiliar para calcular que color poner en el circulo de fondo
    private int getColorMagnitud(Double Magnitud) {
        int colorIdMagnitud;
        int magnitudeFloor = (int) Math.floor(Magnitud);
        
        switch (magnitudeFloor) {
            case 0:
            case 1:
                colorIdMagnitud = R.color.magnitud1;
                break;
            case 2:
                colorIdMagnitud = R.color.magnitud2;
                break;
            case 3:
                colorIdMagnitud = R.color.magnitud3;
                break;
            case 4:
                colorIdMagnitud = R.color.magnitud4;
                break;
            case 5:
                colorIdMagnitud = R.color.magnitud5;
                break;
            case 6:
                colorIdMagnitud = R.color.magnitud6;
                break;
            case 7:
                colorIdMagnitud = R.color.magnitud7;
                break;
            case 8:
                colorIdMagnitud = R.color.magnitud8;
                break;
            case 9:
                colorIdMagnitud = R.color.magnitud9;
                break;
            default:
                colorIdMagnitud = R.color.magnitud10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), colorIdMagnitud);

    }
}
