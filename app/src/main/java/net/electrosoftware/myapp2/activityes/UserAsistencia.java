package net.electrosoftware.myapp2.activityes;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by CARLOS MAESTRE on 26/05/2017.
 */

public class UserAsistencia {

    public boolean asistencia;

    public UserAsistencia() {
    }

    public UserAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public void writeNewUserAsistencia(DatabaseReference dataRef) {
        dataRef.setValue(this);
    }

}
