package com.example.terrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



/*        //Creo una lista falsa de Lugares de terremotos
        ArrayList<Terrremoto> terremotos = new ArrayList<>();
        terremotos.add(new Terrremoto("8.5", "San Francisco", "Feb 2 2016"));
        terremotos.add(new Terrremoto("5.5", "Londres", "May 2 2016"));
        terremotos.add(new Terrremoto("6.5", "Tokio", "Oct 2 2016"));
        terremotos.add(new Terrremoto("7.5", "Madrid", "Dec 2 2018"));
        terremotos.add(new Terrremoto("7.0", "Mexico City", "Ocy 2 2017"));
 */

        //reemplazo la carga manual por una que usar QueryUtils

        ArrayList<Terrremoto> terremotos = QueryUtils.extractTerremotos();


        // Encuentro la referencia de lista en el Layout
        ListView earthquakeListView =  findViewById(R.id.lista);

        // Creo un ArrayAdapter de terremotos
        final TerremotoAdapter adapter = new TerremotoAdapter(this, terremotos);

        // Seteo el adaptador
        earthquakeListView.setAdapter(adapter);


        //Creo un intent para llamar la pagina almacenada en la URL en cada item apretado


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Terrremoto terremotoActual = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(terremotoActual.getmURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });




    }





}
