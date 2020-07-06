package com.example.terrapp;

import android.content.AsyncTaskLoader;
import android.content.Context;


import java.util.List;

public class TerremotoLoader extends AsyncTaskLoader {


    /** Genero Tag */
    private static final String LOG_TAG = TerremotoLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Contruyo un nuevo {@link TerremotoLoader}.
     *
     * @param context de la activity
     * @param url de donde cargar los datos
     */
    public TerremotoLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * Thread que corre de fondo.
     * Lo mismo que el metodo doInBackGround de la asyntask
     */
    @Override
    public List<Terrremoto> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Terrremoto> earthquakes = QueryUtils.traerDataDeTerremoto(mUrl);
        return earthquakes;
    }
}
