package net.electrosoftware.myapp2.fragments;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.electrosoftware.myapp2.Objetos.Empresa;
import net.electrosoftware.myapp2.Objetos.FirebaseReferences;
import net.electrosoftware.myapp2.Objetos.GeoPunto;
import net.electrosoftware.myapp2.Objetos.InfoCorta;
import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.activityes.SitioDetalle;
import net.electrosoftware.myapp2.clasesbases.DownloadTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class FragmentMapa extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    View view, dialogo_filtro;
    private GoogleMap miMapa;
    Button btnBuscarDireccion;
    EditText DireccionBuscar;
    double latitud = 0.0;
    double longitud = 0.0;
    double latitudDestino = 0.0;
    double longitudDestino = 0.0;
    List<Empresa> empresas = new ArrayList<>();
    List<String> tipos = new ArrayList<>();

    RelativeLayout cardView;
    ImageView imageCard;
    TextView NameCard, DirectionCard, RatingCard;

    FloatingActionsMenu fab_menu;
    FloatingActionButton fabLugares, fabEventos, fabPromociones;

    TextView txt_titulo_filtro;
    TextView btn_filtro_fecha;
    static TextView txt_fecha_filtro;
    ArrayAdapter adapterCategorias;
    MaterialSpinner spinner_categoria;
    TextView btn_cancelar, btn_aceptar;
    LinearLayout linear_filtro_calendario;

    public FragmentMapa() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_mapa, container, false);
        dialogo_filtro = getActivity().getLayoutInflater().inflate(R.layout.dialogo_filtro, null);

        imageCard = (ImageView) view.findViewById(R.id.img_card_detail);
        NameCard = (TextView) view.findViewById(R.id.text_name);
        DirectionCard = (TextView) view.findViewById(R.id.text_direction);
        RatingCard = (TextView) view.findViewById(R.id.text_rating);

        mMapView = (MapView) view.findViewById(R.id.frag_mapa);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);

        cardView = (RelativeLayout) view.findViewById(R.id.RLCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SitioDetalle.class);
                intent.putExtra("NombreSitio", NameCard.getText().toString());
                intent.putExtra("DireccionSitio", DirectionCard.getText().toString());
                intent.putExtra("RatingSitio", RatingCard.getText().toString());
                startActivity(intent);
            }
        });

        DireccionBuscar = (EditText) view.findViewById(R.id.frag_textoBuscar);
        btnBuscarDireccion = (Button) view.findViewById(R.id.frag_btnBuscar);
        btnBuscarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String g = DireccionBuscar.getText().toString();

                Geocoder geocoder = new Geocoder(getActivity());
                List<Address> addresses = null;

                try {
                    addresses = geocoder.getFromLocationName(g, 3);
                    if (addresses != null && !addresses.equals(""))
                        search(addresses);

                } catch (Exception e) {

                }
            }
        });

        // FAB MENU
        fab_menu = (FloatingActionsMenu) view.findViewById(R.id.fab_menu);
        fabLugares = (FloatingActionButton) view.findViewById(R.id.fabLugares);
        fabEventos = (FloatingActionButton) view.findViewById(R.id.fabEventos);
        fabPromociones = (FloatingActionButton) view.findViewById(R.id.fabPromociones);

        fabLugares.setColorNormal(Color.parseColor("#3f51b5"));
        fabLugares.setColorPressed(Color.parseColor("#303f9f"));

        fabEventos.setColorNormal(Color.parseColor("#3f51b5"));
        fabEventos.setColorPressed(Color.parseColor("#303f9f"));

        fabPromociones.setColorNormal(Color.parseColor("#3f51b5"));
        fabPromociones.setColorPressed(Color.parseColor("#303f9f"));

        fabLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_menu.collapse();
                mostrarFiltro("Filtro de Lugares", 1);
            }
        });

        fabEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_menu.collapse();
                mostrarFiltro("Filtro de Eventos", 0);
            }
        });

        fabPromociones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_menu.collapse();
                mostrarFiltro("Filtro de Promociones", 0);
            }
        });

        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        miMapa = googleMap;

        miMapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.60971, -74.08175), 5f));
        miMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(4.60971, -74.08175), 5f));

        miMapa.getUiSettings().setZoomControlsEnabled(true);
        miMapa.getUiSettings().setCompassEnabled(true);
        miMapa.getUiSettings().setMyLocationButtonEnabled(true);
        miMapa.getUiSettings().setAllGesturesEnabled(true);
        miMapa.getUiSettings().setMapToolbarEnabled(true);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        miMapa.setMyLocationEnabled(true);

        miMapa.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                if (cardView.getVisibility() == View.VISIBLE) {
                    fade_out();
                }
            }
        });

        miMapa.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {

            @Override
            public boolean onMyLocationButtonClick() {
                Location miUbicacion = miMapa.getMyLocation();
                miMapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(miUbicacion.getLatitude(), miUbicacion.getLongitude()), 16f));

                if (cardView.getVisibility() == View.VISIBLE) {
                    fade_out();
                }

                return false;
            }
        });

        miMapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng Destino = marker.getPosition();
                latitudDestino = Destino.latitude;
                longitudDestino = Destino.longitude;

                DownloadTask.Parametros(getActivity(), new LatLng(latitud, longitud), Destino, miMapa);
                new DownloadTask().execute();
                return false;
            }
        });

        miMapa.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseStorage storage = FirebaseStorage.getInstance();

        empresas = new ArrayList<>();
        tipos.add("business");
        tipos.add("clothings");
        tipos.add("hotels");
        tipos.add("shopping");

        for (final String t : tipos) {
            final DatabaseReference EmpresasTipoRef = database.getReference(
                    FirebaseReferences.EMPRESAS_REFERENCE)
                    .child(FirebaseReferences.TIPOS_REFERENCE)
                    .child(t);
            EmpresasTipoRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        GeoPunto g = snapshot.getValue(GeoPunto.class);
                        Empresa e = new Empresa();
                        e.setKey(snapshot.getKey());
                        e.setGeoPunto(g);
                        empresas.add(e);

                        e.setMarker(miMapa.addMarker(new MarkerOptions()
                                .position(new LatLng(g.getLat(), g.getLng()))
                                .title(e.getKey())
                                .icon(BitmapDescriptorFactory
                                        .fromBitmap(resizedBitmap("map-icons/" + t + ".png", 80, 100)))));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        final DatabaseReference EmpresaInfoCortaRef = database.getReference(
                FirebaseReferences.EMPRESAS_REFERENCE).child(FirebaseReferences.INFOCORTA_REFERENCE);
        final ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                InfoCorta ic = dataSnapshot.getValue(InfoCorta.class);
                if (ic != null) {
                    //Toast.makeText(getActivity(), ic.getNombre(), Toast.LENGTH_SHORT).show();

                    imageCard.setImageResource(R.drawable.loading);
                    if (ic.getFoto_presentacion() != null) {
                        StorageReference imagesRef = storage.getReference(ic.getFoto_presentacion());
                        imagesRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imageCard.setImageBitmap(b);
                                // Use the bytes to display the image
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                                imageCard.setImageResource(R.drawable.no_image_found);
                            }
                        });
                    } else {
                        imageCard.setImageResource(R.drawable.no_image_found);
                    }

                    NameCard.setText(ic.getNombre());
                    DirectionCard.setText(ic.getDireccion());
                    RatingCard.setText(ic.getCalificacion() + "");

                } else {
                    imageCard.setImageResource(R.drawable.no_image_found);
                    NameCard.setText("Lo sentimos, no tenemos información de este punto");
                    DirectionCard.setText("X_X");
                    RatingCard.setText("0");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        miMapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (Empresa e : empresas) {
                    if (e.getMarker().getTitle().equalsIgnoreCase(marker.getTitle())) {
                        EmpresaInfoCortaRef.child(e.getKey()).addValueEventListener(eventListener);
                    } else {
                        EmpresaInfoCortaRef.child(e.getKey()).removeEventListener(eventListener);
                    }
                }

                if (cardView.getVisibility() == View.GONE) {
                    fade_in();
                }

                return true;
            }
        });

        adapterCategorias = ArrayAdapter.createFromResource(getActivity(), R.array.Categorias, android.R.layout.simple_spinner_item);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
        /*if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, getActivity());
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public Bitmap resizedBitmap(String archiveName, int x, int y) {
        InputStream is = null;
        AssetManager mngr = getActivity().getAssets();
        try {
            is = mngr.open(archiveName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Bitmap bitmap = null;
        try {
            //BitmapFactory is an Android graphics utility for images
            bitmap = BitmapFactory.decodeStream(is);

        } finally {
            //Always clear and close
            try {
                is.close();
                is = null;
            } catch (IOException e) {
            }
        }
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, x, y, false);
        return resizedBitmap;
    }

    protected void search(List<Address> addresses) {

        Address address = (Address) addresses.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

        String addressText = String.format(
                "%s, %s",
                address.getMaxAddressLineIndex() > 0 ? address
                        .getAddressLine(0) : "", address.getCountryName());

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);
        markerOptions.title(addressText);
        markerOptions.rotation(1);
        markerOptions.draggable(true);
        miMapa.addMarker(markerOptions);
        miMapa.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        miMapa.animateCamera(CameraUpdateFactory.zoomTo(15));

    }

    public void fade_in() {
        cardView.setVisibility(View.VISIBLE);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
        cardView.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out);
        fab_menu.startAnimation(animation2);
        fab_menu.setVisibility(View.GONE);
    }

    public void fade_out() {
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out);
        cardView.startAnimation(animation1);
        cardView.setVisibility(View.GONE);

        fab_menu.setVisibility(View.VISIBLE);
        Animation animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
        fab_menu.startAnimation(animation2);
    }

    public void mostrarFiltro(String nombreFiltro, int ocultarSpiner) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogo_filtro);

        txt_titulo_filtro = (TextView) dialog.findViewById(R.id.txt_titulo_filtro);
        btn_filtro_fecha = (TextView) dialog.findViewById(R.id.btn_filtro_fecha);
        txt_fecha_filtro = (TextView) dialog.findViewById(R.id.txt_fecha_filtro);
        spinner_categoria = (MaterialSpinner) dialog.findViewById(R.id.spinner_categoria);
        btn_cancelar = (TextView) dialog.findViewById(R.id.btn_cancelar);
        btn_aceptar = (TextView) dialog.findViewById(R.id.btn_aceptar);
        linear_filtro_calendario = (LinearLayout) dialog.findViewById(R.id.linear_filtro_calendario);

        txt_titulo_filtro.setText(nombreFiltro);
        spinner_categoria.setAdapter(adapterCategorias);

        if (ocultarSpiner == 1) {
            linear_filtro_calendario.setVisibility(View.GONE);
        }

        btn_filtro_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                txt_fecha_filtro.setText(new StringBuilder().append(monthOfYear + 1).append("/").append(dayOfMonth).append("/").append(year).append(" "));
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
                dpd.show();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Filtro Aplicado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
}
