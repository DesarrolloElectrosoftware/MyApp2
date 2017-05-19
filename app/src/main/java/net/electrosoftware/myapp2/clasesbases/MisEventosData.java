package net.electrosoftware.myapp2.clasesbases;

import android.graphics.Bitmap;

/**
 * Created by Jonathan on 18/05/2017.
 */

public class MisEventosData {

    Bitmap fotoEvento;
    String nombreEvento;
    String direccionEvento;

    public MisEventosData(Bitmap fotoevento, String nombreevento, String direccionevento) {
        this.fotoEvento = fotoevento;
        this.nombreEvento = nombreevento;
        this.direccionEvento = direccionevento;
    }

    public Bitmap getFotoEvento() {
        return fotoEvento;
    }

    public void setFotoEvento(Bitmap fotoEvento) {
        this.fotoEvento = fotoEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDireccionEvento() {
        return direccionEvento;
    }

    public void setDireccionEvento(String direccionEvento) {
        this.direccionEvento = direccionEvento;
    }
}
