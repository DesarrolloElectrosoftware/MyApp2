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

public class Login extends AppCompatActivity {
    TextView txt_login_appname, btn_login_registro, btn_login_acceso;
    EditText et_login_usuario, et_login_clave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_usuario = (EditText) findViewById(R.id.et_login_usuario);
        et_login_clave = (EditText) findViewById(R.id.et_login_clave);
        btn_login_acceso = (TextView) findViewById(R.id.btn_login_acceso);
        btn_login_registro = (TextView) findViewById(R.id.btn_login_registro);
        txt_login_appname = (TextView) findViewById(R.id.txt_login_appname);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        txt_login_appname.setTypeface(custom_font);
        et_login_clave.setTypeface(custom_font);
        btn_login_registro.setTypeface(custom_font);
        btn_login_acceso.setTypeface(custom_font);
        et_login_usuario.setTypeface(custom_font);
        btn_login_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
        btn_login_acceso.setOnClickListener(new View.OnClickListener() {
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
