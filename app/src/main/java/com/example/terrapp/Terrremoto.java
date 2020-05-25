package com.example.terrapp;

public class Terrremoto {

    private String mMagnitud;
    private String mLugar;
    private String mFecha;

    public Terrremoto(String Magnitud, String Lugar, String Fecha) {
        this.mMagnitud = Magnitud;
        this.mLugar = Lugar;
        this.mFecha = Fecha;
    }

    public String getmMagnitud() {
        return mMagnitud;
    }

    public String getmLugar() {
        return mLugar;
    }

    public String getmFecha() {
        return mFecha;
    }
}
