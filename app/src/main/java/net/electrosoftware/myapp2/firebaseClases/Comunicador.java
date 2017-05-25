package net.electrosoftware.myapp2.firebaseClases;

/**
 * Created by CARLOS MAESTRE on 23/05/2017.
 */

public class Comunicador {
    private static String TipoPerfil;

    private static Object objeto1 = null;
    private static Object objeto2 = null;
    private static Usuario usuario = null;
    private static Evento evento = null;

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
}