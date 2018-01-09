package com.example.sherry.ezpark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ImageButton buttonA, buttonB, buttonC;
    EditText editTextSearch;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getSharedPreferences(SettingsFragment.APP_SETTINGS, Context.MODE_PRIVATE);
        String appTheme = sharedPref.getString("App_Theme", "AppTheme");
        applyCustomTheme(appTheme);

        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        displayScreen(R.id.fragment_main);
    }

    public void onClick(View view) {
        if (view == buttonA) {
            Intent intentA = new Intent(MainActivity.this, LocationAActivity.class);
            startActivity(intentA);

        // }else if (view == buttonB) {

        // }else if (view == buttonC) {

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void displayScreen(int id){
        Fragment fragment = null;

        if (id == R.id.nav_account) {
            fragment = new EditAccountFragment();

        } else if (id == R.id.nav_whatsnew) {
            fragment = new WhatsNewFragment();

        } else if (id == R.id.nav_watchlist) {
            fragment = new WatchListFragment();

        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();

        } else if (id == R.id.nav_userguide) {
            fragment = new UserGuideFragment();

        } else if (id == R.id.fragment_main) {
            fragment = new MainFragment();
        }

        if(fragment != null && id != R.id.fragment_main){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.addToBackStack(null);
            ft.commit();
        } else if (fragment != null && id == R.id.fragment_main) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        // problem!
        int id = item.getItemId();

        displayScreen(id);

        return true;
    }

    public void applyCustomTheme(String appTheme){
        if (appTheme.equals("AppTheme")){
            setTheme(R.style.AppTheme);
        } else if (appTheme.equals("LightTheme")){
            setTheme(R.style.LightTheme);
        }
    }
}