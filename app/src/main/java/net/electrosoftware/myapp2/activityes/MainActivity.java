package net.electrosoftware.myapp2.activityes;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.CircleImageView;
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
import net.electrosoftware.myapp2.firebaseClases.FirebaseReferences;
import net.electrosoftware.myapp2.firebaseClases.Usuario;
import net.electrosoftware.myapp2.fragments.FragmentMapa;
import net.electrosoftware.myapp2.fragments.FragmentMisEventos;
import net.electrosoftware.myapp2.fragments.FragmentMisFavoritos;
import net.electrosoftware.myapp2.fragments.FragmentUsuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean doubleBackToExitPressedOnce = false;
    TextView txt_encabezado_nombre_usuario_Consumidor, txt_encabezado_correo_Consumidor;
    CircleImageView imv_encabezado_foto_perfil_Consumidor;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseStorage storage = FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.frag_main, new FragmentMapa()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        txt_encabezado_nombre_usuario_Consumidor = (TextView) navigationView.getHeaderView(0)
                .findViewById(R.id.txt_encabezado_nombre_usuario_Consumidor);
        txt_encabezado_correo_Consumidor = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txt_encabezado_correo_Consumidor);
        imv_encabezado_foto_perfil_Consumidor = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.imv_encabezado_foto_perfil_Consumidor);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){

            final DatabaseReference userRef = database.getReference(FirebaseReferences.USUARIOS_REFERENCE);

            userRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    txt_encabezado_nombre_usuario_Consumidor.setText(usuario.getNombre());
                    txt_encabezado_correo_Consumidor.setText(user.getEmail());
                    String Foto = usuario.getRutaFoto();
                    if(!Foto.equalsIgnoreCase("Sin imagen")){
                        StorageReference fotoRef = storage.getReference().child("foto usuarios/"+Foto);
                        fotoRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imv_encabezado_foto_perfil_Consumidor.setImageBitmap(b);

                                // Use the bytes to display the image
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                                //imageCard.setImageResource(R.drawable.no_image_found);
                            }
                        });
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

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Presione ATRÁS de nuevo para salir", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        Class fragmentClass;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mapa) {
            fragment = new FragmentMapa();
            fragmentTransaction = true;
        } else if (id == R.id.nav_eventos) {
            fragment = new FragmentMisEventos();
            fragmentTransaction = true;
        } else if (id == R.id.nav_favoritos) {
            fragment = new FragmentMisFavoritos();
            fragmentTransaction = true;
        } else if (id == R.id.nav_perfil) {
            fragment = new FragmentUsuario();
            fragmentTransaction = true;
        } else if (id == R.id.nav_cerrar) {
            finish();
        }

        if (fragmentTransaction) {
            getFragmentManager().beginTransaction()
                    .add(R.id.frag_main, fragment)
                    .commit();

            //menuItem.setChecked(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
