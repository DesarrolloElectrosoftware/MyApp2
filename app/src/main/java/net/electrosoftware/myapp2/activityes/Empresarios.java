package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.firebaseClases.Comunicador;

public class Empresarios extends AppCompatActivity {
    CardView cv_empreseario_perfil, cv_empreseario_lugares, cv_empresario_eventos, cv_empresario_promociones, cv_empresario_cerrar_sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresario);
        cv_empreseario_perfil = (CardView) findViewById(R.id.cv_empreseario_perfil);
        cv_empreseario_lugares = (CardView) findViewById(R.id.cv_empreseario_lugares);
        cv_empresario_eventos = (CardView) findViewById(R.id.cv_empresario_eventos);
        cv_empresario_promociones = (CardView) findViewById(R.id.cv_empresario_promociones);
        cv_empresario_cerrar_sesion = (CardView) findViewById(R.id.cv_empresario_cerrar_sesion);

        cv_empreseario_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cv_empreseario_lugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicador.setTipoSitio("lugar");
                startActivity(new Intent(Empresarios.this, ListaSitiosEmpresario.class));
            }
        });

        cv_empresario_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicador.setTipoSitio("evento");
                startActivity(new Intent(Empresarios.this, ListaSitiosEmpresario.class));
            }
        });

        cv_empresario_promociones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cv_empresario_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
