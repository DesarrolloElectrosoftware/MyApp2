package net.electrosoftware.myapp2.activityes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.firebaseClases.FirebaseReferences;
import net.electrosoftware.myapp2.firebaseClases.Usuario;

public class Login extends AppCompatActivity {
    TextView txt_login_app_name;
    Button btn_login_registrarse, btn_login_ingresar;
    EditText et_correo_usuario, et_login_contrasena;
    TextView txt_login_recordar_contrasena;

    // DIALOGO RECORDAR PASS
    TextView txt_dial_titulo_recordar;
    EditText et_dial_recordar_correo;
    Button btn_dial_recordar_cancelar, btn_dial_recordar_aceptar;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_correo_usuario = (EditText) findViewById(R.id.et_correo_usuario);
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
        et_correo_usuario.setTypeface(custom_font);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //FirebaseAuth.AuthStateListener mAuthListener;

        btn_login_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });

        btn_login_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_correo_usuario.getText().toString().length()>0 && et_login_contrasena.getText().toString().length()>0){
                mAuth.signInWithEmailAndPassword(et_correo_usuario.getText().toString(), et_login_contrasena.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                if (task.isSuccessful()) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user!=null){

                                        final DatabaseReference userRef = database.getReference(FirebaseReferences.USUARIOS_REFERENCE);

                                        userRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                                                if(usuario.getPerfil().equalsIgnoreCase("Consumidor")){
                                                    startActivity(new Intent(Login.this, MainActivity.class));
                                                }else if(usuario.getPerfil().equalsIgnoreCase("Empresario")){
                                                    startActivity(new Intent(Login.this, Empresarios.class));
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError error) {
                                                // Failed to read value
                                                //Log.w(TAG, "Failed to read value.", error.toException());
                                            }
                                        });


                                    }else {

                                    }

                                }else{
                                    //Log.w(TAG, "signInWithEmail:failed", task.getException());

                                    Toast.makeText(Login.this, "Correo o contraseña incorrecta", Toast.LENGTH_LONG).show();
                                    //Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });
                //startActivity(new Intent(Login.this, MainActivity.class));

            }else{
                Toast.makeText(Login.this, "Correo o contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
            }
        });

        txt_login_recordar_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordarPass("RECORDAR CONTRASEÑA");
            }
        });
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }*/

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

                FirebaseAuth auth = FirebaseAuth.getInstance();
                if(et_dial_recordar_correo.getText().toString().length()>0) {

                    auth.sendPasswordResetEmail(et_dial_recordar_correo.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Se ha enviado una solicitud de reinicio de contraseña a tu correo, revísalo", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                    dialog.dismiss();
                    dialog.cancel();
                }else{
                    Toast.makeText(Login.this, "Es necesario el correo", Toast.LENGTH_LONG).show();
                }


            }
        });

        dialog.show();
    }
}
