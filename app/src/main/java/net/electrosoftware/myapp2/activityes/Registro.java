package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;


public class Registro extends AppCompatActivity {
    EditText txt_registro_clave, txt_registro_telefono, txt_registro_correo, txt_registro_nombreusuario;
    TextView btn_registro_acceder, btn_registro_registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_registro_nombreusuario = (EditText) findViewById(R.id.txt_registro_nombreusuario);
        txt_registro_clave = (EditText) findViewById(R.id.txt_registro_clave);
        txt_registro_correo = (EditText) findViewById(R.id.txt_registro_correo);
        txt_registro_telefono = (EditText) findViewById(R.id.txt_registro_telefono);
        btn_registro_acceder = (TextView) findViewById(R.id.btn_registro_acceder);
        btn_registro_registrarse = (TextView) findViewById(R.id.btn_registro_registrarse);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        btn_registro_registrarse.setTypeface(custom_font);
        txt_registro_correo.setTypeface(custom_font);
        txt_registro_telefono.setTypeface(custom_font);
        txt_registro_clave.setTypeface(custom_font);
        txt_registro_nombreusuario.setTypeface(custom_font);
        btn_registro_acceder.setTypeface(custom_font);
        btn_registro_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Registro.this, Login.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        startActivity(new Intent(Registro.this, Login.class));
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
