package com.example.terrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    //hago el adapter una variable global para poder usarlo en onPostExecute

    private TerremotoAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //reemplazo la carga manual por una que usar QueryUtils

       // ArrayList<Terrremoto> terremotos = QueryUtils.extractTerremotos();


        // Encuentro la referencia de lista en el Layout
        ListView earthquakeListView =  findViewById(R.id.lista);

        // Creo un ArrayAdapter de terremotos

        // modifico el pedido para primero pedir un adaptador con una lista vacia que completo en la AsynTask
        miAdaptador = new TerremotoAdapter(this, new ArrayList<Terrremoto>());

        // Seteo el adaptador
        earthquakeListView.setAdapter(miAdaptador);


        //llamo Asyn Task

        TerremotoAsynTask task = new TerremotoAsynTask();
        task.execute(USGS_REQUEST_URL);


        //Creo un intent para llamar la pagina almacenada en la URL en cada item apretado


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Terrremoto terremotoActual = miAdaptador.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(terremotoActual.getmURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


    }

    private class TerremotoAsynTask extends AsyncTask<String, Void, List<Terrremoto>>{

        @Override
        protected List<Terrremoto> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] ==  null){

                return null;


            }

            List<Terrremoto> resultado = QueryUtils.traerDataDeTerremoto(urls[0]);

            return resultado;
        }

        @Override
        protected void onPostExecute(List<Terrremoto> terrremotos) {
            //limpio el adaptador de la data anterior
            miAdaptador.clear();

            if(terrremotos != null && !terrremotos.isEmpty()){

                miAdaptador.addAll(terrremotos);
            }

        }
    }






}
