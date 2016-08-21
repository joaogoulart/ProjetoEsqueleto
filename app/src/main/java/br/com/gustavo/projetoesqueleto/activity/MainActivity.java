package br.com.gustavo.projetoesqueleto.activity;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.gustavo.projetoesqueleto.R;


public class MainActivity extends BaseActivity implements  NavigationView.OnNavigationItemSelectedListener
{

    private TextView tNome;
    private ImageView imgUser;
    private CoordinatorLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rootView = (CoordinatorLayout) findViewById(R.id.rootView);

        setupToolbar();
        setupNavDrawer(this);
        }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
        {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_opt1)
        {
            //do something
        } else if (id == R.id.nav_opt2)
        {
            //do something

        } else if (id == R.id.nav_logout)
        {
            //logout
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }

    public void setupNavDrawer(NavigationView.OnNavigationItemSelectedListener listener)
        {
        }


}
