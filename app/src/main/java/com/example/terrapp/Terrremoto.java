package com.example.terrapp;

public class Terrremoto {

    private Double mMagnitud;
    private String mLugar;
    private Long mTiempo;
    private String mURL;

    public Terrremoto(Double Magnitud, String Lugar, Long mTiempo, String mURL) {
        this.mMagnitud = Magnitud;
        this.mLugar = Lugar;
        this.mTiempo = mTiempo;
        this.mURL = mURL;
    }

    public Double getmMagnitud() {
        return mMagnitud;
    }

    public String getmLugar() {
        return mLugar;
    }

    public Long getmTiempo() {
        return mTiempo;
    }

    public String getmURL() {return mURL; }
}
