package net.electrosoftware.myapp2.activityes;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.fragments.FragmentMapa;
import net.electrosoftware.myapp2.fragments.FragmentMisEventos;
import net.electrosoftware.myapp2.fragments.FragmentUsuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.frag_main, new FragmentMapa()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        } else if (id == R.id.nav_perfil) {
            fragment = new FragmentUsuario();
            fragmentTransaction = true;
            Log.i("NavigationView", "Perfil");
        } else if (id == R.id.nav_cerrar) {
            finish();
        }
        if (fragmentTransaction) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.frag_main, fragment)
                    .commit();

            //menuItem.setChecked(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
