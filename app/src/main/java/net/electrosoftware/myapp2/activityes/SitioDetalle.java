package net.electrosoftware.myapp2.activityes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.clasesbases.ComentariosAdapter;
import net.electrosoftware.myapp2.clasesbases.ComentariosData;

import java.util.ArrayList;
import java.util.List;

public class SitioDetalle extends AppCompatActivity {
    ToggleButton btn_favoritos, btn_asistencia, btn_calificar;
    Button btn_comentar;
    List<ComentariosData> dataModels;
    ComentariosAdapter adapter;
    RecyclerView rv_comentarios;
    TextView text_nombre_sitio, text_direccion_sitio, text_calificacion_sitio, text_horario_sitio, text_categoria_sitio, text_telefono_sitio;
    ImageView imv_foto_lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_detalle);

        btn_favoritos = (ToggleButton) findViewById(R.id.btn_favoritos);
        btn_asistencia = (ToggleButton) findViewById(R.id.btn_asistencia);
        btn_calificar = (ToggleButton) findViewById(R.id.btn_calificar);
        btn_comentar = (Button) findViewById(R.id.btn_comentar);

        btn_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_favoritos.isChecked()) {
                    Toast.makeText(SitioDetalle.this, "Guardado en tus Favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SitioDetalle.this, "Eliminado de tus Favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_asistencia.isChecked()) {
                    Toast.makeText(SitioDetalle.this, "Asistirás a este Evento", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SitioDetalle.this, "No asistirás a este Evento", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_calificar.isChecked()) {
                    Toast.makeText(SitioDetalle.this, "Califica este Lugar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SitioDetalle.this, "Calificación eliminada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rv_comentarios = (RecyclerView) findViewById(R.id.rv_comentarios);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(SitioDetalle.this);
        rv_comentarios.setLayoutManager(linearlayoutmanager);
        rv_comentarios.setHasFixedSize(true);
        initializeData();
        initializeAdapter();

        text_nombre_sitio = (TextView) findViewById(R.id.text_nombre_sitio);
        text_nombre_sitio.setText(getIntent().getStringExtra("NombreSitio"));

        text_direccion_sitio = (TextView) findViewById(R.id.text_direccion_sitio);
        text_direccion_sitio.setText(getIntent().getStringExtra("DireccionSitio"));

        text_calificacion_sitio = (TextView) findViewById(R.id.text_calificacion_sitio);
        text_calificacion_sitio.setText(getIntent().getStringExtra("RatingSitio"));

        text_horario_sitio = (TextView) findViewById(R.id.text_horario_sitio);
        text_categoria_sitio = (TextView) findViewById(R.id.text_categoria_sitio);
        text_telefono_sitio = (TextView) findViewById(R.id.text_telefono_sitio);

        imv_foto_lugar = (ImageView) findViewById(R.id.imv_foto_lugar);
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
        rv_comentarios.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

