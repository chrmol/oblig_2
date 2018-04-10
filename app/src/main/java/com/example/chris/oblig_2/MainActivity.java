package com.example.chris.oblig_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public SharedPreferences sharedPreferences;

    Fragment fragment1 = null;
    Fragment fragment2 = null;
    Fragment fragment3 = null;
    Fragment fragment4 = null;

    ArrayList<String> landliste = new ArrayList<>();
    ArrayList<countries> countriesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO Slett ubrukt kode
        //SharedPreferences.Editor myEditor = sharedPreferences.edit();
        /* §§§§§§§§§§§§§§§§§§§§§§§
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Oppretter objekter av land
        countries sverige = new countries("Sverige","Stockholm","Hotell Plaza",100.00,300.00);
        countries korea = new countries("Sør-Korea","Seoul","Hilton Hotel",200.00,500.00);
        countries tyskland = new countries("Tyskland","Berlin","Das Überhotel",300.00,800.00);
        countries storbritannia = new countries("Storbritannia","London","Hotel Britain",400.00,1000.00);
        countries russland = new countries("Russland","Moskva","лениндом",500.00,1300.00);

        countriesArrayList.add(sverige);
        countriesArrayList.add(korea);
        countriesArrayList.add(tyskland);
        countriesArrayList.add(storbritannia);
        countriesArrayList.add(russland);

        landliste.add(sverige.toString());
        countriesArrayList.add(sverige);
        countriesArrayList.add(korea);
        countriesArrayList.add(tyskland);
        countriesArrayList.add(storbritannia);
        countriesArrayList.add(russland);
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
        // Handle navigation view item clicks here.

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
       // Bundle bundle = new Bundle();

        //bundle.putStringArrayList("countries",landliste);
        int id = item.getItemId();

        if (id == R.id.drawer_item1) {
            // Handle the action


                fragment1 = new fragmentCountry();

            ft.replace(R.id.screen,fragment1);
            ft.commit();
        } else if (id == R.id.drawer_item2) {

                fragment2 = new fragmentTravel();

            ft.replace(R.id.screen,fragment2);
            ft.commit();
        } else if (id == R.id.drawer_item3) {

                fragment3 = new fragmentExpenditures();

            ft.replace(R.id.screen,fragment3);
            ft.commit();
        } else if (id == R.id.drawer_item4) {
            fragment4 = new fragmentCurrency();

            ft.replace(R.id.screen,fragment4);
            ft.commit();
        }
        //if (fragment != null){


        //}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
