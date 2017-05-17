package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;


public class Registro extends AppCompatActivity {
    EditText et_registro_contrasena, et_registro_telefono, et_registro_email, et_registro_usuario;
    TextView txt_registro_app_name;
    Button btn_registro_registrarse;
    ImageButton btn_registro_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_registro_usuario = (EditText) findViewById(R.id.et_registro_usuario);
        et_registro_contrasena = (EditText) findViewById(R.id.et_registro_contrasena);
        et_registro_email = (EditText) findViewById(R.id.et_registro_email);
        et_registro_telefono = (EditText) findViewById(R.id.et_registro_telefono);
        btn_registro_registrarse = (Button) findViewById(R.id.btn_registro_registrarse);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        et_registro_email.setTypeface(custom_font);
        et_registro_telefono.setTypeface(custom_font);
        et_registro_contrasena.setTypeface(custom_font);
        et_registro_usuario.setTypeface(custom_font);
        btn_registro_registrarse.setTypeface(custom_font);

        btn_registro_registrarse.setOnClickListener(new View.OnClickListener() {
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
