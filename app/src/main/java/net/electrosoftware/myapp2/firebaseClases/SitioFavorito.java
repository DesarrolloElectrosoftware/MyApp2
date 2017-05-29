package net.electrosoftware.myapp2.firebaseClases;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by CARLOS MAESTRE on 29/05/2017.
 */

public class SitioFavorito {

    public boolean favorito;

    public SitioFavorito() {
    }

    public void writeNewSitioFavorito(DatabaseReference dataRef) {
        dataRef.setValue(this);
    }

    public SitioFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
