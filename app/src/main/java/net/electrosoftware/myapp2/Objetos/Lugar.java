package net.electrosoftware.myapp2.Objetos;

import android.graphics.Bitmap;

/**
 * Created by CARLOS MAESTRE on 13/03/2017.
 */

public class Lugar {

    String nombre;
    String direccion;
    String telefono;
    String descripcion;
    Float calificacion;
    String foto_presentacion;
    String geolocalizacion;
    //List<String> horario;
    String pagina;
    //List<String> tipo;
    Bitmap fotoP;

    public Lugar(){

    }

    public Lugar(String nombre, String direccion, String telefono, String descripcion, Float calificacion, String foto_presentacion, String geolocalizacion, String pagina) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.foto_presentacion = foto_presentacion;
        this.geolocalizacion = geolocalizacion;
        this.pagina = pagina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public String getFoto_presentacion() {
        return foto_presentacion;
    }

    public void setFoto_presentacion(String foto_presentacion) {
        this.foto_presentacion = foto_presentacion;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    /*public List<String> getHorario() {
        return horario;
    }

    public void setHorario(List<String> horario) {
        this.horario = horario;
    }*/

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    /*public List<String> getTipo() {
        return tipo;
    }

    public void setTipo(List<String> tipo) {
        this.tipo = tipo;
    }*/

    @Override
    public String toString() {
        return "Lugar{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", calificacion=" + calificacion +
                ", foto_presentacion='" + foto_presentacion + '\'' +
                ", geolocalizacion='" + geolocalizacion + '\'' +
                //", horario=" + horario +
                ", pagina='" + pagina + '\'' +
                //", tipo=" + tipo +
                '}';
    }

    public Bitmap getFotoP() {
        return fotoP;
    }

    public void setFotoP(Bitmap fotoP) {
        this.fotoP = fotoP;
    }
}
