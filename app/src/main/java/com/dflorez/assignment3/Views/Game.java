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
import androidx.lifecycle.ViewModelProvider;

import com.dflorez.assignment3.R;

import com.dflorez.assignment3.Models.GameClass; // Import Game Class
import com.dflorez.assignment3.Helpers.ScoresManager; // Import Helper Class
import com.dflorez.assignment3.ViewModels.GameViewModel;

public class Game extends AppCompatActivity {

    // ViewModel
    GameViewModel viewModel;

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
        Log.i("tag", "Play Game Starts");
        // TODO: All logs -> package:com.dflorez.assignment3

        // Instantiate the ViewModel with a reference to the ViewModel Class
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        // Binding
        TextView playerNameTV = findViewById(R.id.txtPlayerName);
        Button btnPlay = findViewById(R.id.btnPlay);
        Intent intent = getIntent();

        // Get Intent Extras
        String playerNameFromIntent = intent.getStringExtra("playerName");

        // Pass playerNameFromIntent to the ViewModel (MutableLiveData)
        viewModel.setPlayerName(playerNameFromIntent);

        // Observe playerName LiveData
        viewModel.getPlayerName().observe(this, playerName -> {

            // Checks if player name is empty
            if (playerName.isEmpty()) {
                playerNameTV.setText(getString(R.string.game_empty_player_name));
            } else {
                playerNameTV.setText(playerName);
            }
        });


        viewModel.getPlayerName().observe(this, name -> {
            Log.i("tag", "playerName observed -> " + name);
        });



        // TODO: Testing Game class
        /*
        GameClass gameClass = new GameClass(); // Initialize Game
        String[] gameBoard = gameClass.getGameBoard();
        for (String tile : gameBoard) {
            Log.i("tag", tile);
        }
        */

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
                redirectActivity(Game.this, Welcome.class, viewModel.getPlayerName().getValue()); // redirects to Game activity
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
                redirectActivity(Game.this, HighScore.class, viewModel.getPlayerName().getValue()); // redirects to Game activity
            }
        });
    }

    //==========
    // Methods
    //==========
    // Method to redirect to another activity
    public static void redirectActivity(Activity activity, Class secondActivity, String playerName) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("playerName", playerName); // TODO: Update with LiveData reflecting Name
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