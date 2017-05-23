package net.electrosoftware.myapp2.firebaseClases;

/**
 * Created by CARLOS MAESTRE on 23/05/2017.
 */

public class Comunicador {
        private static Object objeto1 = null;
        private static Object objeto2 = null;

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
    }