package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexzh.circleimageview.CircleImageView;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.clasesbases.Imageutils;

import java.io.File;


public class Registro extends AppCompatActivity implements Imageutils.ImageAttachmentListener {
    EditText et_registro_contrasena, /*et_registro_telefono,*/
            et_registro_email, et_registro_usuario;
    TextView txt_registro_app_name;
    Button btn_registro_registrarse;
    ImageButton btn_registro_foto;

    CircleImageView imv_registro_foto;

    //For Image Attachment
    private Bitmap bitmap;
    private String file_name;
    Imageutils imageutils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_registro_usuario = (EditText) findViewById(R.id.et_registro_usuario);
        et_registro_contrasena = (EditText) findViewById(R.id.et_registro_contrasena);
        et_registro_email = (EditText) findViewById(R.id.et_registro_email);
        //et_registro_telefono = (EditText) findViewById(R.id.et_registro_telefono);
        btn_registro_registrarse = (Button) findViewById(R.id.btn_registro_registrarse);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        et_registro_email.setTypeface(custom_font);
        //et_registro_telefono.setTypeface(custom_font);
        et_registro_contrasena.setTypeface(custom_font);
        et_registro_usuario.setTypeface(custom_font);
        btn_registro_registrarse.setTypeface(custom_font);

        btn_registro_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this, Empresarios.class));
            }
        });

        imageutils = new Imageutils(this);
        imv_registro_foto = (CircleImageView) findViewById(R.id.imv_registro_foto);
        imv_registro_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageutils.imagepicker(1);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        startActivity(new Intent(Registro.this, Login.class));
        finish();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap = file;
        this.file_name = filename;
        imv_registro_foto.setImageBitmap(file);

        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file, filename, path, false);

    }
}
