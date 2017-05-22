package net.electrosoftware.myapp2.firebaseClases;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by CARLOS MAESTRE on 22/05/2017.
 */

public class Usuario {

    String perfil;
    String nombre;
    String rutaFoto;

    public Usuario() {
    }

    public Usuario(String perfil, String nombre, String rutaFoto) {
        this.perfil = perfil;
        this.nombre = nombre;
        this.rutaFoto = rutaFoto;
    }

    public void writeNewUser(DatabaseReference dataRef, String idUser) {
        dataRef.child("usuarios").child(idUser).setValue(this);
    }

}
