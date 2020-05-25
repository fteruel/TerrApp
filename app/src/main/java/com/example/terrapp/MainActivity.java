package com.example.terrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo una lista falsa de Lugares de terremotos
        ArrayList<Terrremoto> terremotos = new ArrayList<>();
        terremotos.add(new Terrremoto("8.5", "San Francisco", "Feb 2 2016"));
        terremotos.add(new Terrremoto("5.5", "Londres", "May 2 2016"));
        terremotos.add(new Terrremoto("6.5", "Tokio", "Oct 2 2016"));
        terremotos.add(new Terrremoto("7.5", "Madrid", "Dec 2 2018"));
        terremotos.add(new Terrremoto("7.0", "Mexico City", "Ocy 2 2017"));


        // Encuentro la referencia de lista en el Layout
        ListView earthquakeListView =  findViewById(R.id.lista);

        // Creo un ArrayAdapter de terremotos
        TerremotoAdapter adapter = new TerremotoAdapter(this, terremotos);

        // Seteo el adaptador
               earthquakeListView.setAdapter(adapter);
    }
}
