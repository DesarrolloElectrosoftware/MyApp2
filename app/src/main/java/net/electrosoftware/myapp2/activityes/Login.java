package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;

public class Login extends AppCompatActivity {
    TextView txt_login_app_name;
    Button btn_login_registrarse, btn_login_ingresar;
    EditText et_login_usuario, et_login_contrasena;
    TextView txt_login_recordar_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_usuario = (EditText) findViewById(R.id.et_login_usuario);
        et_login_contrasena = (EditText) findViewById(R.id.et_login_contrasena);
        txt_login_recordar_contrasena = (TextView) findViewById(R.id.txt_login_recordar_contrasena);

        btn_login_ingresar = (Button) findViewById(R.id.btn_login_ingresar);
        btn_login_registrarse = (Button) findViewById(R.id.btn_login_registrarse);
        txt_login_app_name = (TextView) findViewById(R.id.txt_login_app_name);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        txt_login_app_name.setTypeface(custom_font);
        et_login_contrasena.setTypeface(custom_font);
        btn_login_registrarse.setTypeface(custom_font);
        btn_login_ingresar.setTypeface(custom_font);
        et_login_usuario.setTypeface(custom_font);

        btn_login_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Login.this, Registro.class));
            }
        });

        btn_login_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
