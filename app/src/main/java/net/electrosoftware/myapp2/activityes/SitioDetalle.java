package net.electrosoftware.myapp2.activityes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.clasesbases.ComentariosAdapter;
import net.electrosoftware.myapp2.clasesbases.ComentariosData;

import java.util.ArrayList;
import java.util.List;

public class SitioDetalle extends AppCompatActivity {
    private static final String TAG = "TAG";
    ToggleButton btn_sitio_favoritos, btn_sitio_asistencia, btn_sitio_calificar;
    Button btn_sitio_comentar;
    List<ComentariosData> dataModels;
    ComentariosAdapter adapter;
    RecyclerView rv_sitio_comentarios;
    TextView txt_sitio_nombre, txt_sitio_direccion, text_sitio_calificacion, text_sitio_horario, text_sitio_categoria, text_sitio_telefono, txt_sitio_descripcion, txt_sitio_ver_mas_comentarios;
    ImageView imv_sitio_foto;

    // CALIFICACION
    TextView txt_dial_titulo_rate, txt_dial_puntaje_rate;
    SmileRating rtng_dial_smile_rate;
    Button btn_dial_cancelar_rate, btn_dial_aceptar_rate;

    // COMENTARIO
    TextView txt_dial_comentario_titulo;
    EditText et_dial_comentario_agregar;
    Button btn_dial_comentario_cancelar, btn_dial_comentario_aceptar, btn_sitio_eventos, btn_sitio_promociones;
    ImageView imv_sitio_ruteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_sitio_detalle);

        btn_sitio_favoritos = (ToggleButton) findViewById(R.id.btn_sitio_favoritos);
        btn_sitio_asistencia = (ToggleButton) findViewById(R.id.btn_sitio_asistencia);
        btn_sitio_calificar = (ToggleButton) findViewById(R.id.btn_sitio_calificar);
        btn_sitio_comentar = (Button) findViewById(R.id.btn_sitio_comentar);

        btn_sitio_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_sitio_favoritos.isChecked()) {
                    Toast.makeText(SitioDetalle.this, "Guardado en tus Favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SitioDetalle.this, "Eliminado de tus Favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_sitio_asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_sitio_asistencia.isChecked()) {
                    Toast.makeText(SitioDetalle.this, "Asistirás a este Evento", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SitioDetalle.this, "No asistirás a este Evento", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_sitio_calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_sitio_calificar.isChecked()) {
                    calificarSitio(txt_sitio_nombre.getText().toString());
                    //Toast.makeText(SitioDetalle.this, "Califica este Lugar", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(SitioDetalle.this).setTitle("Confirmación")
                            .setMessage("¿Está seguro de eliminar su calificación?")
                            .setIcon(R.mipmap.ic_launcher)
                            .setCancelable(true)
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    btn_sitio_calificar.setChecked(false);
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    btn_sitio_calificar.setChecked(true);
                                }
                            })
                            .show();
                }
            }
        });

        btn_sitio_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(SitioDetalle.this, AgregarComentarioActivity.class));
                comentarSitio(txt_sitio_nombre.getText().toString());
            }
        });

        rv_sitio_comentarios = (RecyclerView) findViewById(R.id.rv_sitio_comentarios);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(SitioDetalle.this);
        rv_sitio_comentarios.setLayoutManager(linearlayoutmanager);
        rv_sitio_comentarios.setHasFixedSize(true);
        initializeData();
        initializeAdapter();

        txt_sitio_nombre = (TextView) findViewById(R.id.txt_sitio_nombre);
        txt_sitio_nombre.setText(getIntent().getStringExtra("NombreSitio"));

        txt_sitio_direccion = (TextView) findViewById(R.id.txt_sitio_direccion);
        txt_sitio_direccion.setText(getIntent().getStringExtra("DireccionSitio"));

        text_sitio_calificacion = (TextView) findViewById(R.id.text_sitio_calificacion);
        text_sitio_calificacion.setText("Calificación: " + getIntent().getStringExtra("RatingSitio"));

        text_sitio_horario = (TextView) findViewById(R.id.text_sitio_horario);
        text_sitio_categoria = (TextView) findViewById(R.id.text_sitio_categoria);
        text_sitio_telefono = (TextView) findViewById(R.id.text_sitio_telefono);
        txt_sitio_descripcion = (TextView) findViewById(R.id.txt_sitio_descripcion);

        imv_sitio_foto = (ImageView) findViewById(R.id.imv_sitio_foto);

        btn_sitio_eventos = (Button) findViewById(R.id.btn_sitio_eventos);
        btn_sitio_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SitioDetalle.this, "No hay eventos en este Lugar", Toast.LENGTH_SHORT).show();
            }
        });

        btn_sitio_promociones = (Button) findViewById(R.id.btn_sitio_promociones);
        btn_sitio_promociones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SitioDetalle.this, "No hay promociones de este lugar", Toast.LENGTH_SHORT).show();
            }
        });

        txt_sitio_ver_mas_comentarios = (TextView) findViewById(R.id.txt_sitio_ver_mas_comentarios);
        txt_sitio_ver_mas_comentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initializeData() {
        dataModels = new ArrayList<>();

        Bitmap icon = BitmapFactory.decodeResource(SitioDetalle.this.getResources(),
                R.drawable.usuario);

        dataModels.add(new ComentariosData(icon, "Charles Manson", "13 Mayo 2017", "Un muy buen sitio ideal para beber en compañía"));
        dataModels.add(new ComentariosData(icon, "Marynlin Monroe", "2 Junio 2016", "Tiene un ambiente agradable, me agradó"));
        dataModels.add(new ComentariosData(icon, "Michael Jackson", "23 Marzo 2015", "La música es muy buena y variada"));
        dataModels.add(new ComentariosData(icon, "Davie Bowie", "7 Febrero 2015", "El servicio es un poco demorado, pero de resto muy bien"));
        dataModels.add(new ComentariosData(icon, "David Copperfield", "15 Julio 2014", "No lo recomiendo para nada, meseros muy despistados y hacen todo de mala gana"));
        dataModels.add(new ComentariosData(icon, "Mariah Carey", "7 Mayo 2014", "Es un poco costoso el sitio pero está muy bien"));
        dataModels.add(new ComentariosData(icon, "Michelle Obama", "9 Agosto 2013", "Genial!!!... Me encantó, voy cada fin de mes allí"));
        dataModels.add(new ComentariosData(icon, "Lara Croft", "29 Abril 2013", "Allí tuve mucha suerte y encontré a una persona especial"));
        dataModels.add(new ComentariosData(icon, "Vicent McMahon", "25 Diciembre 2012", "Un lugar muy básico, no está mal pero no es nada de otro mundo"));
    }

    private void initializeAdapter() {
        ComentariosAdapter adapter = new ComentariosAdapter(dataModels);
        rv_sitio_comentarios.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void calificarSitio(String nombreSitio) {

        final Dialog dialog = new Dialog(SitioDetalle.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogo_calificacion);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txt_dial_titulo_rate = (TextView) dialog.findViewById(R.id.txt_dial_titulo_rate);
        txt_dial_puntaje_rate = (TextView) dialog.findViewById(R.id.txt_dial_puntaje_rate);

        rtng_dial_smile_rate = (SmileRating) dialog.findViewById(R.id.rtng_dial_smile_rate);

        btn_dial_cancelar_rate = (Button) dialog.findViewById(R.id.btn_dial_cancelar_rate);
        btn_dial_aceptar_rate = (Button) dialog.findViewById(R.id.btn_dial_aceptar_rate);

        txt_dial_titulo_rate.setText(nombreSitio);
        //txt_dial_puntaje_rate.setText("Tu calificación: " + level);

        rtng_dial_smile_rate.setNameForSmile(BaseRating.TERRIBLE, "Muy Malo");
        rtng_dial_smile_rate.setNameForSmile(BaseRating.BAD, "Malo");
        rtng_dial_smile_rate.setNameForSmile(BaseRating.OKAY, "Normal");
        rtng_dial_smile_rate.setNameForSmile(BaseRating.GOOD, "Bueno");
        rtng_dial_smile_rate.setNameForSmile(BaseRating.GREAT, "Muy Bueno");

        rtng_dial_smile_rate.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                int level = rtng_dial_smile_rate.getRating(); //level is from 1 to 5

                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        txt_dial_puntaje_rate.setText("Tu calificación: " + level);
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        txt_dial_puntaje_rate.setText("Tu calificación: " + level);
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        txt_dial_puntaje_rate.setText("Tu calificación: " + level);
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        txt_dial_puntaje_rate.setText("Tu calificación: " + level);
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        txt_dial_puntaje_rate.setText("Tu calificación: " + level);
                        break;
                }
            }
        });

        btn_dial_cancelar_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
                btn_sitio_calificar.setChecked(false);
            }
        });

        btn_dial_aceptar_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SitioDetalle.this, "Sitio Calificado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();
                btn_sitio_calificar.setChecked(true);
            }
        });

        dialog.show();
    }

    public void comentarSitio(String nombreSitio) {
        final Dialog dialog = new Dialog(SitioDetalle.this);
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
                //btn_sitio_calificar.setChecked(false);
            }
        });

        btn_dial_comentario_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SitioDetalle.this, "Comentario agregado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();
                //btn_sitio_calificar.setChecked(true);
            }
        });

        dialog.show();
    }
}

