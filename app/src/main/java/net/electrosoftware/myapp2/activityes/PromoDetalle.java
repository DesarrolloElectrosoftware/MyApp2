package net.electrosoftware.myapp2.activityes;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import net.electrosoftware.myapp2.R;

public class PromoDetalle extends AppCompatActivity {
    ToggleButton btn_promo_favoritos;
    Button btn_promo_comentar;
    TextView txt_promo_nombre;
    // COMENTARIO
    TextView txt_dial_comentario_titulo;
    EditText et_dial_comentario_agregar;
    Button btn_dial_comentario_cancelar, btn_dial_comentario_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_promo_detalle);

        txt_promo_nombre = (TextView) findViewById(R.id.txt_promo_nombre);

        btn_promo_favoritos = (ToggleButton) findViewById(R.id.btn_promo_favoritos);
        btn_promo_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_promo_favoritos.isChecked()) {
                    Toast.makeText(PromoDetalle.this, "Guardado en tus Favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PromoDetalle.this, "Eliminado de tus Favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_promo_comentar = (Button) findViewById(R.id.btn_promo_comentar);
        btn_promo_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LugarDetalle.this, AgregarComentarioActivity.class));
                comentarPromo(txt_promo_nombre.getText().toString());
            }
        });
    }

    public void comentarPromo(String nombreSitio) {
        final Dialog dialog = new Dialog(PromoDetalle.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogo_comentario);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txt_dial_comentario_titulo = (TextView) dialog.findViewById(R.id.txt_dial_comentario_titulo);
        et_dial_comentario_agregar = (EditText) dialog.findViewById(R.id.et_dial_comentario_agregar);

        btn_dial_comentario_cancelar = (Button) dialog.findViewById(R.id.btn_dial_comentario_cancelar);
        btn_dial_comentario_aceptar = (Button) dialog.findViewById(R.id.btn_dial_comentario_aceptar);

        txt_dial_comentario_titulo.setText(nombreSitio);

        btn_dial_comentario_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        btn_dial_comentario_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PromoDetalle.this, "Comentario agregado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
