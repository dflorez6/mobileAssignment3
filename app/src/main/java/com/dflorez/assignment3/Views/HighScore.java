package com.dflorez.assignment3.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dflorez.assignment3.R;

public class HighScore extends AppCompatActivity {

    // References
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, game, high_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);





        //====================
        // Menu
        //====================
        // Drawer Layout
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home); // Inside nav_drawer (menu items id)
        game = findViewById(R.id.game); // Inside nav_drawer (menu items id)
        high_score = findViewById(R.id.high_score); // Inside nav_drawer (menu items id)

        // Menu Handler
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the drawer
                openDrawer(drawerLayout);
            }
        });

        // Home Item
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HighScore.this, Welcome.class); // redirects to Game activity
            }
        });

        // Game
        high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HighScore.this, Game.class); // redirects to Game activity
            }
        });

        // High Score Item
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

    }

    // Methods
    // Method to redirect to another activity
    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish(); // Destroy current activity
    }

    // Drawer Functions
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    // Close Drawer (menu)
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //This method will get called every time the recreate method gets called, activity gets recreated and drawer closes
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

}