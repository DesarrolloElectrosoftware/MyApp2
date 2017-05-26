package net.electrosoftware.myapp2.firebaseClases;

import android.graphics.Bitmap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by CARLOS MAESTRE on 23/05/2017.
 */

public class Comunicador {

    private static String TipoPerfil;
    private static Object objeto1 = null;
    private static Object objeto2 = null;
    private static Usuario usuario = null;
    private static Evento evento = null;
    private static String idEvento = null;
    private static Bitmap fotoEvento = null;

    public static void setObjeto1(Object newObjeto) {
        objeto1 = newObjeto;
    }

    public static Object getObjeto1() {
        return objeto1;
    }

    public static void setObjeto2(Object newObjeto) {
        objeto2 = newObjeto;
    }

    public static Object getObjeto2() {
        return objeto2;
    }

    public static String getTipoPerfil() {
        return TipoPerfil;
    }

    public static void setTipoPerfil(String tipoPerfil) {
        TipoPerfil = tipoPerfil;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Comunicador.usuario = usuario;
    }

    public static Evento getEvento() {
        return evento;
    }

    public static void setEvento(Evento evento) {
        Comunicador.evento = evento;
    }

    public static String getIdEvento() {
        return idEvento;
    }

    public static void setIdEvento(String idEvento) {
        Comunicador.idEvento = idEvento;
    }

    public static Bitmap getFotoEvento() {
        return fotoEvento;
    }

    public static void setFotoEvento(Bitmap fotoEvento) {
        Comunicador.fotoEvento = fotoEvento;
    }

    public static void actualizarAsistentesEvento(final String idEvento){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dr = database.getReference(FirebaseReferences.ASISTENCIA_REFERENCE).child(idEvento);

        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int cont = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserAsistencia ua = snapshot.getValue(UserAsistencia.class);
                    if(ua.getAsistencia()){
                        cont = cont +1;
                    }
                }
                database.getReference(FirebaseReferences.SITIO_REFERENCE)
                        .child(idEvento).child("asistentes").setValue(cont);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void cargarEvento(String idEvento){

        actualizarAsistentesEvento(idEvento);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dr = database.getReference(FirebaseReferences.SITIO_REFERENCE).child(idEvento);

        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                evento = dataSnapshot.getValue(Evento.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}