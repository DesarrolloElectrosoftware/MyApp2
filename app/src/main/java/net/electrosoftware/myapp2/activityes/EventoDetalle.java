package net.electrosoftware.myapp2.activityes;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.firebaseClases.Comunicador;
import net.electrosoftware.myapp2.firebaseClases.Evento;
import net.electrosoftware.myapp2.firebaseClases.FirebaseReferences;
import net.electrosoftware.myapp2.firebaseClases.RutaRef;

public class EventoDetalle extends AppCompatActivity {
    ToggleButton btn_evento_favoritos;
    Button btn_evento_comentar, btn_evento_act;
    ToggleButton btn_evento_asistencia;
    TextView txt_evento_nombre;
    // COMENTARIO
    TextView txt_dial_comentario_titulo, txt_evento_direccion, text_evento_horario_ini,
            text_evento_asistentes, text_evento_categoria, text_evento_telefono, txt_evento_descripcion,
            text_evento_quien_organiza, text_evento_horario_fin;
    EditText et_dial_comentario_agregar;
    Button btn_dial_comentario_cancelar, btn_dial_comentario_aceptar;
    ImageView imv_evento_foto;
    RutaRef rutaRefSitio = null;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseStorage storage = FirebaseStorage.getInstance();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_evento_detalle);

        imv_evento_foto = (ImageView) findViewById(R.id.imv_evento_foto);

        txt_evento_nombre = (TextView) findViewById(R.id.txt_evento_nombre);
        txt_evento_direccion = (TextView) findViewById(R.id.txt_evento_direccion);
        text_evento_horario_ini = (TextView) findViewById(R.id.text_evento_horario_ini);
        text_evento_horario_fin = (TextView) findViewById(R.id.text_evento_horario_fin);
        text_evento_asistentes = (TextView) findViewById(R.id.text_evento_asistentes);
        text_evento_categoria = (TextView) findViewById(R.id.text_evento_categoria);
        text_evento_telefono = (TextView) findViewById(R.id.text_evento_telefono);
        text_evento_quien_organiza = (TextView) findViewById(R.id.text_evento_quien_organiza);
        txt_evento_descripcion = (TextView) findViewById(R.id.txt_evento_descripcion);


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
                comentarPromo(txt_evento_nombre.getText().toString());
            }
        });

        btn_evento_act = (Button) findViewById(R.id.btn_evento_act);
        btn_evento_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_evento_asistencia = (ToggleButton) findViewById(R.id.btn_evento_asistencia);
        btn_evento_asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference AsistenciaRef = database.getReference(FirebaseReferences.ASISTENCIA_REFERENCE)
                        .child(Comunicador.getIdEvento())
                        .child(user.getUid());
                if (btn_evento_asistencia.isChecked()) {
                    UserAsistencia ua = new UserAsistencia(true);
                    ua.writeNewUserAsistencia(AsistenciaRef);

                } else {
                    UserAsistencia ua = new UserAsistencia(false);
                    ua.writeNewUserAsistencia(AsistenciaRef);
                }

            }
        });

        if (rutaRefSitio != null) {
            rutaRefSitio.getdatabaseReference().removeEventListener(rutaRefSitio.getvalueEventListener());
            rutaRefSitio = null;
        }
        rutaRefSitio = new RutaRef();
        rutaRefSitio.setdatabaseReference(database.getReference(FirebaseReferences.SITIO_REFERENCE).child(Comunicador.getIdEvento()));
        rutaRefSitio.setvalueEventListener(
                rutaRefSitio.getdatabaseReference().addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                Evento ev = dataSnapshot.getValue(Evento.class);
                                if (ev != null) {
                                    //Toast.makeText(getActivity(), ic.getNombre(), Toast.LENGTH_SHORT).show();
                                    imv_evento_foto.setImageResource(R.drawable.loading);
                                    if (ev.getRutaFoto() != null && !(ev.getRutaFoto().equalsIgnoreCase("Sin imagen"))) {
                                        StorageReference imagesRef = storage.getReference("foto sitios/evento/" + ev.getRutaFoto());
                                        imagesRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                            @Override
                                            public void onSuccess(byte[] bytes) {
                                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                                imv_evento_foto.setImageBitmap(b);
                                                // Use the bytes to display the image
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception exception) {
                                                // Handle any errors
                                                imv_evento_foto.setImageResource(R.drawable.no_image_found);
                                            }
                                        });
                                    } else {
                                        imv_evento_foto.setImageResource(R.drawable.no_image_found);
                                    }

                                    txt_evento_nombre.setText(ev.getNombre());
                                    txt_evento_direccion.setText(ev.getDireccion());
                                    text_evento_horario_ini.setText("Inicio: " + ev.getFechaIni() + " " + ev.getHoraIni());
                                    text_evento_horario_fin.setText("Fin: " + ev.getFechaFin() + " " + ev.getHoraFin());
                                    text_evento_asistentes.setText("Asistentes: " + ev.getAsistentes());
                                    text_evento_categoria = (TextView) findViewById(R.id.text_evento_categoria);
                                    text_evento_categoria.setText(ev.getTipo());
                                    text_evento_telefono.setText(ev.getTelefono());
                                    text_evento_quien_organiza.setText(ev.getPatrocinador());
                                    txt_evento_descripcion.setText(ev.getDescripcion());


                                } else {

                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }));

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
