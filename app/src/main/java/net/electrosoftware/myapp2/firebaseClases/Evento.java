package net.electrosoftware.myapp2.firebaseClases;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by CARLOS MAESTRE on 23/05/2017.
 */

public class Evento {

    public String nombre;
    public String telefono;
    public String fechaIni;
    public String fehcaFin;
    public String horaIni;
    public String horaFin;
    public String patrocinador;
    public String tipo;
    public String descripcion;
    public String rutaFoto;
    //public Bitmap foto;

    public Evento() {
    }

    public String writeNewEvento(DatabaseReference dataRef) {
        String key = dataRef.push().getKey();
        dataRef.child(key).setValue(this);
        return key;
    }

    public Evento(String nombre, String telefono, String fechaIni, String fehcaFin, String horaIni, String horaFin, String patrocinador, String tipo, String descripcion, String rutaFoto) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaIni = fechaIni;
        this.fehcaFin = fehcaFin;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.patrocinador = patrocinador;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.rutaFoto = rutaFoto;
    }

    /*public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }*/

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFehcaFin() {
        return fehcaFin;
    }

    public void setFehcaFin(String fehcaFin) {
        this.fehcaFin = fehcaFin;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
