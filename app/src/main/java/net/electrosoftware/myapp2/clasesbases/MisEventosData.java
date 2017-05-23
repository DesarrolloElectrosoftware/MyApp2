package net.electrosoftware.myapp2.clasesbases;

import android.graphics.Bitmap;

/**
 * Created by Jonathan on 18/05/2017.
 */

public class MisEventosData {

    Bitmap fotoEvento;
    String nombreEvento;
    String tipoevento;

    public MisEventosData(Bitmap fotoevento, String nombreevento, String tipoevento) {
        this.fotoEvento = fotoevento;
        this.nombreEvento = nombreevento;
        this.tipoevento = tipoevento;
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
        return tipoevento;
    }

    public void setDireccionEvento(String direccionEvento) {
        this.tipoevento = direccionEvento;
    }
}
