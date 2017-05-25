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
import net.electrosoftware.myapp2.firebaseClases.Comunicador;

public class EventoDetalle extends AppCompatActivity {
    ToggleButton btn_evento_favoritos;
    Button btn_evento_comentar;
    TextView txt_evento_nombre;
    // COMENTARIO
    TextView txt_dial_comentario_titulo, txt_evento_direccion, text_evento_horario_ini,
            text_evento_asistentes, text_evento_categoria, text_evento_telefono, txt_evento_descripcion,
            text_evento_quien_organiza, text_evento_horario_fin;
    EditText et_dial_comentario_agregar;
    Button btn_dial_comentario_cancelar, btn_dial_comentario_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_evento_detalle);

        txt_evento_nombre = (TextView) findViewById(R.id.txt_evento_nombre);
        txt_evento_nombre.setText(Comunicador.getEvento().getNombre());

        txt_evento_direccion = (TextView) findViewById(R.id.txt_evento_direccion);
        txt_evento_direccion.setText(Comunicador.getEvento().getDireccion());

        text_evento_horario_ini = (TextView) findViewById(R.id.text_evento_horario_ini);
        text_evento_horario_ini.setText("Inicio: "+ Comunicador.getEvento().getFechaIni() + " " +Comunicador.getEvento().getHoraIni());

        text_evento_horario_fin = (TextView) findViewById(R.id.text_evento_horario_fin);
        text_evento_horario_fin.setText("Fin: "+ Comunicador.getEvento().getFechaFin() + " " +Comunicador.getEvento().getHoraFin());

        text_evento_asistentes = (TextView) findViewById(R.id.text_evento_asistentes);
        text_evento_asistentes.setText("Asistentes: "+Comunicador.getEvento().getAsistentes());

        text_evento_categoria = (TextView) findViewById(R.id.text_evento_categoria);
        text_evento_categoria.setText(Comunicador.getEvento().getTipo());

        text_evento_telefono = (TextView) findViewById(R.id.text_evento_telefono);
        text_evento_telefono.setText(Comunicador.getEvento().getTelefono());

        text_evento_quien_organiza = (TextView) findViewById(R.id.text_evento_quien_organiza);
        text_evento_quien_organiza.setText(Comunicador.getEvento().getPatrocinador());

        txt_evento_descripcion = (TextView) findViewById(R.id.txt_evento_descripcion);
        txt_evento_descripcion.setText(Comunicador.getEvento().getDescripcion());

        btn_evento_favoritos = (ToggleButton) findViewById(R.id.btn_evento_favoritos);
        btn_evento_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_evento_favoritos.isChecked()) {
                    Toast.makeText(EventoDetalle.this, "Guardado en tus Favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EventoDetalle.this, "Eliminado de tus Favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_evento_comentar = (Button) findViewById(R.id.btn_evento_comentar);
        btn_evento_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LugarDetalle.this, AgregarComentarioActivity.class));
                comentarPromo(txt_evento_nombre.getText().toString());
            }
        });
    }

    public void comentarPromo(String nombreSitio) {
        final Dialog dialog = new Dialog(EventoDetalle.this);
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
                Toast.makeText(EventoDetalle.this, "Comentario agregado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
