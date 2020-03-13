package com.puffizza;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.puffizza.Utills.utils;
import com.puffizza.fragment.AboutusFragment;
import com.puffizza.fragment.CartFragment;
import com.puffizza.fragment.HomeFragment;
import com.puffizza.fragment.MenuFragment;
import com.puffizza.fragment.ProfileFragment;
import com.puffizza.helper.BottomNavigationBehavior;


public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    SharedPreferences sharedpreferences;
    private ImageView menu_icon,home_icon;
    private TextView header_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        sharedpreferences = getSharedPreferences(utils.MyPREFERENCES, Context.MODE_PRIVATE);

        Log.e("ID", sharedpreferences.getString(utils.ID, ""));
        Log.e("NAME", sharedpreferences.getString(utils.F_Name, ""));

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());

        menu_icon = findViewById(R.id.menu_icon);
        home_icon = findViewById(R.id.home_icon);

        header_name = findViewById(R.id.header_name);
        header_name.setText(toolbar.getTitle());

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                toolbar.setTitle("Contact Us");
                header_name.setText(toolbar.getTitle());
                fragment = new CartFragment();
                loadFragment(fragment);
            }
        });

        /*menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, menu_icon);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.right_menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.title_logout:
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(utills.ID, "");
                                editor.putString(utills.F_Name, "");
                                editor.putString(utills.L_Name, "");
                                editor.putString(utills.Phone, "");
                                editor.putString(utills.Email, "");
                                editor.commit();
                                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(logout);
                                finishAffinity();
                                return true;
                            default:
                                return MainActivity.super.onContextItemSelected(item);
                        }
                    }
                });

                popup.show();
            }
        });*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    header_name.setText(toolbar.getTitle());
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_menu:
                    toolbar.setTitle("Menu");
                    header_name.setText(toolbar.getTitle());
                    fragment = new MenuFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_contact:
                    toolbar.setTitle("Contact Us");
                    header_name.setText(toolbar.getTitle());
                    fragment = new AboutusFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    header_name.setText(toolbar.getTitle());
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }
}
