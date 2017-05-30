package net.electrosoftware.myapp2.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
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
import net.electrosoftware.myapp2.activityes.AgregarEvento;
import net.electrosoftware.myapp2.clasesbases.MisEventosAdapter;
import net.electrosoftware.myapp2.clasesbases.MisEventosData;
import net.electrosoftware.myapp2.firebaseClases.Comunicador;
import net.electrosoftware.myapp2.firebaseClases.FirebaseReferences;
import net.electrosoftware.myapp2.firebaseClases.Usuario;
import net.electrosoftware.myapp2.firebaseClases.itemListaSitio;

import java.util.ArrayList;
import java.util.List;

public class FragmentMisEventos extends Fragment {
    View view;
    TextView txt_mis_eventos_crear;


    List<MisEventosData> dataModels;
    MisEventosAdapter adapter;
    Toolbar mToolbar;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseStorage storage = FirebaseStorage.getInstance();
    Bitmap icon = null;

    private RecyclerView rv_mis_eventos;
    private FirebaseRecyclerAdapter<itemListaSitio, PostViewHolder> mPostAdapter;
    private DatabaseReference listaEventosRef;

    public FragmentMisEventos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_mis_eventos, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbarMisEventos);
        mToolbar.setTitle("Mis Eventos");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        txt_mis_eventos_crear = (TextView) view.findViewById(R.id.txt_mis_eventos_crear);
        txt_mis_eventos_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), AgregarEvento.class));

                final DatabaseReference userRef = database.getReference(FirebaseReferences.USUARIOS_REFERENCE);

                userRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);

                        if (usuario.getPerfil().equalsIgnoreCase("Consumidor")) {
                            Comunicador.setTipoPerfil("Consumidor");
                        } else if (usuario.getPerfil().equalsIgnoreCase("Empresario")) {
                            Comunicador.setTipoPerfil("Empresario");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                startActivity(new Intent(getActivity(), AgregarEvento.class));
            }
        });


        //LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getActivity());
        //rv_mis_eventos.setLayoutManager(linearlayoutmanager);
        //rv_mis_eventos.setHasFixedSize(true);


        //initializeData();
        //initializeAdapter();
        initialiseScreen();

        return view;
    }

    private void initializeData() {
        dataModels = new ArrayList<>();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference listaRef = database.getReference(FirebaseReferences.LISTA_REFERENCE)
                .child(user.getUid())
                .child(FirebaseReferences.EVENTO_REFERENCE);
        icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.no_image_found);

        listaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.kamran);
                dataModels.clear();


                String tipo = "";
                //Iterable<DataSnapshot> items = dataSnapshot.getChildren();
                try {
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        String idEvento = i.getKey();
                        itemListaSitio item = i.getValue(itemListaSitio.class);

                        if (!item.getFoto().equalsIgnoreCase("Sin imagen")) {
                            StorageReference fotoRef = storage.getReference().child("foto usuarios/" + item.getFoto());
                            fotoRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    icon = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    // Use the bytes to display the image
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                    //imageCard.setImageResource(R.drawable.no_image_found);
                                }
                            });

                            switch (item.getTipo()) {
                                case "restaurante":
                                    tipo = "Restaurante y Gastronomía";
                                    break;
                                case "rumba":
                                    tipo = "Rumba, Bares y Discotecas";
                                    break;
                                case "cultura":
                                    tipo = "Arte y Cultura";
                                    break;
                                case "musica":
                                    tipo = "Música y Conciertos";
                                    break;
                                case "deporte":
                                    tipo = "Deporte y Salud";
                                    break;
                                case "ropa":
                                    tipo = "Ropa y Accesorios";
                                    break;
                                case "religion":
                                    tipo = "Religión";
                                    break;
                            }
                            dataModels.add(new MisEventosData(icon, item.getNombre(), tipo));


                            if (dataModels.size() == 1) {
                                adapter = new MisEventosAdapter(dataModels);
                                rv_mis_eventos.setAdapter(adapter);
                            } else if (dataModels.size() > 1) {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        /*dataModels.add(new MisEventosData(icon, "Twitterwire", "8 Arrowood Drive"));
        dataModels.add(new MisEventosData(icon, "Oyoloo", "34 Jay Hill"));
        dataModels.add(new MisEventosData(icon, "Avavee", "9 Forest Lane"));
        dataModels.add(new MisEventosData(icon, "Twinder", "511 Sauthoff Pass"));
        dataModels.add(new MisEventosData(icon, "Thoughtstorm", "06444 Columbus Pass"));
        dataModels.add(new MisEventosData(icon, "Skajo", "3699 Sunfield Crossing"));
        dataModels.add(new MisEventosData(icon, "Twitternation", "840 Parkside Terrace"));
        dataModels.add(new MisEventosData(icon, "Youspan", "74 Doe Crossing Hill"));
        dataModels.add(new MisEventosData(icon, "Quire", "8 Nevada Plaza"));*/
    }

    private void initializeAdapter() {
        rv_mis_eventos.setAdapter(adapter);
    }



    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public ImageView imv_dial_mis_eventos_foto;
        public TextView txt_dial_mis_eventos_nombre;
        public TextView txt_dial_mis_eventos_tipo;


        public PostViewHolder(View itemView) {
            super(itemView);

            imv_dial_mis_eventos_foto = (ImageView) itemView.findViewById(R.id.imv_dial_mis_eventos_foto);
            txt_dial_mis_eventos_nombre = (TextView) itemView.findViewById(R.id.txt_dial_mis_eventos_nombre);
            txt_dial_mis_eventos_tipo = (TextView) itemView.findViewById(R.id.txt_dial_mis_eventos_tipo);
        }

        public void setTxt_dial_mis_eventos_nombre(String txt_dial_mis_eventos_nombre) {
            this.txt_dial_mis_eventos_nombre.setText(txt_dial_mis_eventos_nombre);
        }

        public void setTxt_dial_mis_eventos_tipo(String txt_dial_mis_eventos_tipo) {
            String tipo = "";
            switch (txt_dial_mis_eventos_tipo) {
                case "restaurante":
                    tipo = "Restaurante y Gastronomía";
                    break;
                case "rumba":
                    tipo = "Rumba, Bares y Discotecas";
                    break;
                case "cultura":
                    tipo = "Arte y Cultura";
                    break;
                case "musica":
                    tipo = "Música y Conciertos";
                    break;
                case "deporte":
                    tipo = "Deporte y Salud";
                    break;
                case "ropa":
                    tipo = "Ropa y Accesorios";
                    break;
                case "religion":
                    tipo = "Religión";
                    break;
            }
            this.txt_dial_mis_eventos_tipo.setText(tipo);
        }
    }

    private void initialiseScreen() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        rv_mis_eventos = (RecyclerView) view.findViewById(R.id.rv_mis_eventos);
        rv_mis_eventos.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaEventosRef = database.getReference(FirebaseReferences.LISTA_REFERENCE)
                .child(user.getUid())
                .child(FirebaseReferences.EVENTO_REFERENCE);
        setupAdapter();
        rv_mis_eventos.setAdapter(mPostAdapter);
    }

    private void setupAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<itemListaSitio, PostViewHolder>(
                itemListaSitio.class,
                R.layout.item_mis_eventos,
                PostViewHolder.class,
                listaEventosRef
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, final itemListaSitio model, int position) {
                StorageReference storageReference = storage.getReference().child("foto sitios/evento/" + model.getFoto());
               // StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.getFoto());
                Glide.with(getActivity())
                        .using(new FirebaseImageLoader())
                        .load(storageReference)
                        .into(viewHolder.imv_dial_mis_eventos_foto);

                viewHolder.setTxt_dial_mis_eventos_nombre(model.getNombre());
                viewHolder.setTxt_dial_mis_eventos_tipo(model.getTipo());

            }
        };
    }



}
