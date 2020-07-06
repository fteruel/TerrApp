package com.example.terrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Terrremoto>> {


    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    //Asigno un valor al ID que voy a darle al Loader
    private static final int TERREMOTO_LOADER_ID = 1;

    private TextView view_vacio;

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

        //Agrego un empty State

        view_vacio = findViewById(R.id.view_vacio);
        earthquakeListView.setEmptyView(view_vacio);


        // Creo un ArrayAdapter de terremotos

        // modifico el pedido para primero pedir un adaptador con una lista vacia que completo en la AsynTask
        miAdaptador = new TerremotoAdapter(this, new ArrayList<Terrremoto>());

        // Seteo el adaptador
        earthquakeListView.setAdapter(miAdaptador);


        //llamo Asyn Task

      //  TerremotoAsynTask task = new TerremotoAsynTask();
       // task.execute(USGS_REQUEST_URL);

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(TERREMOTO_LOADER_ID, null, this);


        //Creo un intent para llamar la pagina almacenada en la URL en cada item apretado


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // encuentro el terremoto clickeado
                Terrremoto terremotoActual = miAdaptador.getItem(position);

                // convierto la URL a URI para pasarle al constructor
                Uri earthquakeUri = Uri.parse(terremotoActual.getmURL());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                startActivity(websiteIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, preferenciasActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Terrremoto>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        return new TerremotoLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Terrremoto>> loader, List<Terrremoto> terrremotos) {

        //oculto el indicador porque ya cargue el View

        View indicadorPB = findViewById(R.id.indicadorPB);
        indicadorPB.setVisibility(View.GONE);

        view_vacio.setText(R.string.not_found);

        miAdaptador.clear();

        if(terrremotos != null && !terrremotos.isEmpty()){

            miAdaptador.addAll(terrremotos);

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Terrremoto>> loader) {
        miAdaptador.clear();
    }





    /*private class TerremotoAsynTask extends AsyncTask<String, Void, List<Terrremoto>>{

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
*/



}
