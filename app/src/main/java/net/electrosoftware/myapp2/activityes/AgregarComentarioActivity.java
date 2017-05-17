package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import net.electrosoftware.myapp2.clasesbases.Imageutils;

import java.io.File;

public class AgregarComentarioActivity extends AppCompatActivity implements Imageutils.ImageAttachmentListener {
    ImageView imv_comentario_agregar_imagen;

    private Bitmap bitmap;
    private String file_name;
    Button btn_comentario_publicar;
    Imageutils imageutils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_comentario);

        imageutils = new Imageutils(this);

        //imv_comentario_agregar_imagen = (ImageView) findViewById(R.id.imv_comentario_agregar_imagen);
        //imv_comentario_agregar_imagen.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        imageutils.imagepicker(1);
        //    }
        //});

        //imv_comentario_agregar_imagen.setOnLongClickListener(new View.OnLongClickListener() {
        //    @Override
        //    public boolean onLongClick(View view) {
        //        new AlertDialog.Builder(AgregarComentarioActivity.this).setTitle("Confirmación")
        //                .setMessage("¿Está seguro de eliminar su fotografía?")
        //                .setIcon(R.mipmap.ic_launcher)
        //                .setCancelable(true)
        //                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
        //                    @Override
        //                    public void onClick(DialogInterface dialog, int which) {
        //                        imv_comentario_agregar_imagen.setImageBitmap(null);
        //                    }
        //                })
        //                .setNegativeButton("Cancelar", null)
        //                .show();
        //
        //        return false;
        //    }
        //});

        //btn_comentario_publicar = (Button)findViewById(R.id.btn_comentario_publicar);
        //btn_comentario_publicar.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        finish();
        //    }
        //});
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
        // imv_comentario_agregar_imagen.setImageBitmap(file);

        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file, filename, path, false);

    }
}
