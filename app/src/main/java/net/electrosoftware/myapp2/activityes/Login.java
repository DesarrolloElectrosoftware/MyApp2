package net.electrosoftware.myapp2.activityes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.electrosoftware.myapp2.R;

public class Login extends AppCompatActivity {
    TextView txt_login_app_name;
    Button btn_login_registrarse, btn_login_ingresar;
    EditText et_login_usuario, et_login_contrasena;
    TextView txt_login_recordar_contrasena;

    // DIALOGO RECORDAR PASS
    TextView txt_dial_titulo_recordar;
    EditText et_dial_recordar_correo;
    Button btn_dial_recordar_cancelar, btn_dial_recordar_aceptar;

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
                startActivity(new Intent(Login.this, Registro.class));
            }
        });

        btn_login_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        txt_login_recordar_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordarPass("RECORDAR CONTRASEÑA");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }

    public void recordarPass(String texto) {
        final Dialog dialog = new Dialog(Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogo_recordar_pass);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txt_dial_titulo_recordar = (TextView) dialog.findViewById(R.id.txt_dial_titulo_recordar);
        et_dial_recordar_correo = (EditText) dialog.findViewById(R.id.et_dial_recordar_correo);

        btn_dial_recordar_cancelar = (Button) dialog.findViewById(R.id.btn_dial_recordar_cancelar);
        btn_dial_recordar_aceptar = (Button) dialog.findViewById(R.id.btn_dial_recordar_aceptar);

        txt_dial_titulo_recordar.setText(texto);

        btn_dial_recordar_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        btn_dial_recordar_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Se ha enviado una solicitud de reinicio de contraseña a tu correo, revísalo", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                dialog.cancel();

            }
        });

        dialog.show();
    }
}
