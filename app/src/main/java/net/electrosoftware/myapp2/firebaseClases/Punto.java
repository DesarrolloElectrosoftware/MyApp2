package net.electrosoftware.myapp2.firebaseClases;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by CARLOS MAESTRE on 23/05/2017.
 */

public class Punto {

    public String lat;
    public String lng;

    public Punto() {
    }

    public void writePunto(DatabaseReference dataRef, String key) {
        dataRef.child(key).setValue(this);
    }

    public Punto(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
