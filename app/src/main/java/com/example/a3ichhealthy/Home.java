package com.example.a3ichhealthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView  bottomNavigationView;
    Float translationY = 100f;
    Boolean isMenuOpen = false;
    Boolean isLayoutShown = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    
    RelativeLayout fabOverlay;

    FloatingActionButton mainFabButton;
    FloatingActionButton weightFAB;
    FloatingActionButton waterFAB;
    FloatingActionButton foodFAB;
    FloatingActionButton exerciseFAB;

    FrameLayout weightFABandText, waterFABandText, foodFABandText, exerciseFABandText;
    View main_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.home);

        main_container = findViewById(R.id.main_container);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        // Closes Fab menu when clicking on bottom nav menu
                        if (isMenuOpen){
                            closeMenu();
                            hideForegroundLayout();
                        }
                        fragment = new HomeFragment();
                        break;
                    case R.id.journal:
                        if (isMenuOpen){
                            closeMenu();
                            hideForegroundLayout();
                        }
                        fragment = new JournalFragment();
                        break;
                    case R.id.stat:
                        if (isMenuOpen){
                            closeMenu();
                            hideForegroundLayout();
                        }
                        fragment = new StatsFragment();
                        break;
                    case R.id.more:
                        if (isMenuOpen){
                            closeMenu();
                            hideForegroundLayout();
                        }
                        fragment = new MoreFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

        // init overlay layout layer
        fabOverlay = findViewById(R.id.fabOverlay);
        fabOverlay.setAlpha(0f);

        // Closes Fab menu when clicking anywhere on the screen
        fabOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMenuOpen){
                    hideForegroundLayout();
                    closeMenu();
                }
            }
        });
        
        initFabMenu();
    }

    private void initFabMenu(){
        mainFabButton = findViewById(R.id.mainFabButton);
        weightFABandText = findViewById(R.id.weightFABandText);
        waterFABandText = findViewById(R.id.waterFABandText);
        foodFABandText = findViewById(R.id.foodFABandText);
        exerciseFABandText = findViewById(R.id.exerciseFABandText);
        weightFAB = findViewById(R.id.weightFAB);
        waterFAB = findViewById(R.id.waterFAB);
        foodFAB = findViewById(R.id.foodFAB);
        exerciseFAB = findViewById(R.id.exerciseFAB);

        weightFABandText.setAlpha(0f);
        waterFABandText.setAlpha(0f);
        foodFABandText.setAlpha(0f);
        exerciseFABandText.setAlpha(0f);

        mainFabButton.setOnClickListener(this);
        weightFAB.setOnClickListener(this);
        waterFAB.setOnClickListener(this);
        foodFAB.setOnClickListener(this);
        exerciseFAB.setOnClickListener(this);
    }

    private void openMenu(){
        isMenuOpen = !isMenuOpen;

        mainFabButton.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

        weightFABandText.animate().translationY(0f).translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        waterFABandText.animate().translationY(0f).translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        foodFABandText.animate().translationY(0f).translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        exerciseFABandText.animate().translationY(0f).translationX(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }
    private void closeMenu(){
        isMenuOpen = !isMenuOpen;

        mainFabButton.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        weightFABandText.animate().translationY(translationY).translationX(50f).setInterpolator(interpolator).alpha(0f).setDuration(300).start();
        waterFABandText.animate().translationY(translationY).translationX(-50f).setInterpolator(interpolator).alpha(0f).setDuration(300).start();
        foodFABandText.animate().translationY(translationY).translationX(100f).setInterpolator(interpolator).alpha(0f).setDuration(300).start();
        exerciseFABandText.animate().translationY(translationY).translationX(-100f).setInterpolator(interpolator).alpha(0f).setDuration(300).start();
    }

    private void hideForegroundLayout(){
        isLayoutShown = !isLayoutShown;
        fabOverlay.setAlpha(0f);
    }

    private void showForegroundLayout(){
        isMenuOpen = !isMenuOpen;
        fabOverlay.setAlpha(0.25f);
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.mainFabButton:
                if (isMenuOpen){
                    closeMenu();
                    //hideForegroundLayout();
                }else{
                    openMenu();
                    //showForegroundLayout();
                }
                break;
            case R.id.weightFAB:
                break;
            case R.id.waterFAB:
                break;
            case R.id.foodFAB:
                break;
            case R.id.exerciseFAB:
                break;
        }
    }

    /* LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = vi.inflate(R.layout.home, null);

    // fill in any details dynamically here
    RelativeLayout overlay = (RelativeLayout) v.findViewById(R.id.fabOverlay); */


    @Override
    public void finish() {
        super.finish();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        /*Intent intent = new Intent(this, page5.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); */
    }
}