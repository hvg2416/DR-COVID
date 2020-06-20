package com.developerhvg24.drcovid;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.developerhvg24.drcovid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //REQUIRED FOR DRAWER LAYOUT
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    //REQUIRED FOR TABLAYOUT
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3;
    HomeFragment homeFragment;
    NewsFragment newsFragment;
    AnalyzerFragment analyzerFragment;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    //FOR CHECKING CONNECTIVITY
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    //FAB for analyzer fragment
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //RETRIEVING SAVED LANGUAGE USING SHARED PREFERENCES
        SharedPreferences sharedPreferences = getSharedPreferences("saved_language",this.MODE_PRIVATE);
        String lang = sharedPreferences.getString("lang","en");

        if(lang.equals("en"))
        {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Resources resources = this.getResources();
            Configuration config = new Configuration(resources.getConfiguration());
            config.locale = locale;
            resources.updateConfiguration(config,resources.getDisplayMetrics());
        }
        else
        {
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Resources resources = this.getResources();
            Configuration config = new Configuration(resources.getConfiguration());
            config.locale = locale;
            resources.updateConfiguration(config,resources.getDisplayMetrics());
        }


        //INITIALIZING ALL FRAGMENTS
        homeFragment = new HomeFragment();
        newsFragment = new NewsFragment();
        analyzerFragment = new AnalyzerFragment();

        //TABLAYOUT RELATED INITIALISATION
        tabLayout = findViewById(R.id.tablayout2);
        tabItem1 = findViewById(R.id.monday);
        tabItem2 = findViewById(R.id.tuesday);
        tabItem3 = findViewById(R.id.wednesday);
        toolbar = findViewById(R.id.toolbar_1);

        //SETTING OWN CUSTOM TOOLBAR
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //NAVIGATION DRAWER RELATED INITIALISATION
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        //SETTING UP NAVIGATION DRAWER
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        //VIEW PAGER RELATED WORK
        viewPager = findViewById(R.id.viewpager2);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        //LIMITATION : ON SLIDING THE TABLAYOUT CHANGES BUT TAB TITLE REMAINS PREVIOUS ONE
        //SO FOR THAT WE ARE USING BELOW DEPRICATED METHOD
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        checkNetwork();

        floatingActionButton = findViewById(R.id.btn_FAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TO JUMP TO ANALYZER FRAGMENT, THIS LOGIC IS USED
                viewPager.setCurrentItem(2);
            }
        });
    }

    public void checkNetwork() {
        //CHECKING NETWORK CONNECTIVITY
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.no_internet_advice_text);
        builder.setTitle(R.string.no_internet_text);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        if(!(networkInfo!=null && networkInfo.isConnected()))
        {
            dialog.show();
        }
    }

    //TO CREATE OPTIONS MENU AND TO INFLATE IT's LAYOUT WITH ANY DESIRED MENU LAYOUT FILE
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //This if statement will check if HamBurger like button is clicked or not
        //and responds in accordance to that to open and close navigation drawer
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }

        //This else block will help us to set onClick Listeners to items in the Options Menu
        else
        {
            //IF LANGUAGE OPTION GOT SELECTED, CHANGE THE LANGUAGE RESPECTIVELY
            if (item.getItemId() == R.id.lang_option) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setNegativeButton(R.string.lang_Hindi_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //SAVING THE SELECTED USING SHARED PREFERENCES
                        SharedPreferences sharedPreferences = getSharedPreferences("saved_language",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("lang","hi");
                        editor.commit();
                        Locale locale = new Locale("hi");
                        Locale.setDefault(locale);
                        Resources resources = getResources();
                        Configuration config = new Configuration(resources.getConfiguration());
                        config.locale = locale;
                        resources.updateConfiguration(config,resources.getDisplayMetrics());
                        recreate();
                    }
                });
                alertDialogBuilder.setPositiveButton(R.string.lang_English_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //SAVING THE SELECTED USING SHARED PREFERENCES
                        SharedPreferences sharedPreferences = getSharedPreferences("saved_language",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("lang","en");
                        editor.commit();

                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Resources resources = getResources();
                        Configuration config = new Configuration(resources.getConfiguration());
                        config.locale = locale;
                        resources.updateConfiguration(config,resources.getDisplayMetrics());
                        recreate();
                    }
                });
                alertDialogBuilder.setTitle(R.string.Select_language_text);
                alertDialogBuilder.setMessage(R.string.lang_alert_box_message);
                alertDialogBuilder.create().show();
            }
            return super.onOptionsItemSelected(item);
        }
    }

    //TO SET onClick LISTENER TO ITEMS IN NAVIGATION MENU
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.nav_helpline:
                Toast.makeText(this,"Helpline Clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1075"));
                if(intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                    break;
                }
            case R.id.nav_app_support:
                Toast.makeText(this,"App Support Clicked",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Intent.ACTION_SENDTO);
                intent1.setData(Uri.parse("mailto:hvgautam24@gmail.com"));
                if(intent1.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent1);
                    break;
                }
        }
        return false;
    }
}