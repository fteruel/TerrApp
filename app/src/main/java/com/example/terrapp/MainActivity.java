package com.example.terrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        TerremotoAdapter adapter = new TerremotoAdapter(this, terremotos);

        // Seteo el adaptador
        earthquakeListView.setAdapter(adapter);

        try {
            caramelos();
        } catch (JSONException e) {
            e.printStackTrace();
    }


    }

    private void caramelos() throws JSONException {
        //estamos parceando este Json:

/*        {
            "caramelos":[
            {
                "nombre": "tita",
                "cantidad ": 10
            }

            ]
        }
        */


        String caramelosJson = "{\"caramelos\":[{\"nombre\": \"Tita\", \"cantidad\":10}]}";

        JSONObject carameloRoot = new JSONObject(caramelosJson);

        JSONArray carameloArray = carameloRoot.getJSONArray("caramelos");

        JSONObject primerCaramelo = carameloArray.getJSONObject(0);

        String nombre = primerCaramelo.getString("nombre");
        int cantidad = primerCaramelo.getInt("cantidad");




    }
}
