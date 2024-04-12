package com.dflorez.assignment3.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dflorez.assignment3.R;

import com.dflorez.assignment3.Helpers.ScoresManager; // Import Helper Class

public class Game extends AppCompatActivity {

    // References
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, game, high_score;

    // Constants
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // TODO: Remove after testing
        // Add Scores
        ScoresManager.addScore(getApplicationContext(), "Kanut", 140);
        ScoresManager.addScore(getApplicationContext(), "Bailey", 110);


        //====================
        // Play Game
        //====================
        // Binding
        Log.i("tag", "Play Game Starts: ");

        Button btnPlay = findViewById(R.id.btnPlay);
        Intent intent = getIntent();

        // Get Intent Extras
        String playerName = intent.getStringExtra("playerName");

        // Used for testing
        TextView test = findViewById(R.id.txtPlayerName);
        // Checks if player name is empty (Intent from Welcome activity)
        if (playerName.isEmpty()) {
            test.setText(getString(R.string.game_empty_player_name));
        } else {
            test.setText(playerName);
        }

        Log.i("tag", "Play Game Starts: ");


        // Play Handler
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "Play Game");
            }
        });


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
                redirectActivity(Game.this, Welcome.class); // redirects to Game activity
            }
        });

        // Game
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        // High Score Item
        high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Game.this, HighScore.class); // redirects to Game activity
            }
        });
    }

    //==========
    // Methods
    //==========
    // Method to redirect to another activity
    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("playerName", ""); // TODO: Update with LiveData reflecting Name
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