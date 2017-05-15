package net.electrosoftware.myapp2.activityes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.fragments.FragmentMapa;
import net.electrosoftware.myapp2.fragments.FragmentPerfilUsuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        } else {
            super.onBackPressed();
        }
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
            //fragment = new EventosFragment();
            //fragmentTransaction = true;
            Log.i("NavigationView", "Eventos");
        } else if (id == R.id.nav_perfil) {
            fragment = new FragmentPerfilUsuario();
            fragmentTransaction = true;
            Log.i("NavigationView", "Perfil");
        } else if (id == R.id.nav_cerrar) {
            Log.i("NavigationView", "Cerrar");
        } else if (id == R.id.nav_share) {
            Log.i("NavigationView", "Share");
        } else if (id == R.id.nav_send) {
            Log.i("NavigationView", "PSend");
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
