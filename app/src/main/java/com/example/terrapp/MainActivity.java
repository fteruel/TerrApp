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
        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");

        // Encuentro la referencia de lista en el Layout
        ListView earthquakeListView =  findViewById(R.id.lista);

        // Creo un ArrayAdapter de terremotos
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);

        // Seteo el adaptador
               earthquakeListView.setAdapter(adapter);
    }
}
