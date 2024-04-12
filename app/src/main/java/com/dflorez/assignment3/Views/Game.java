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

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game extends AppCompatActivity {

    // ViewModel
    GameViewModel viewModel;

    // References
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, game, high_score;
    TextView playerNameTV, scoreTV, roundTV, squaresToRememberTV;
    Button btnPlay;

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

        // Get Intent Extras
        Intent intent = getIntent();
        String playerNameFromIntent = intent.getStringExtra("playerName");

        // Pass playerNameFromIntent to the ViewModel (MutableLiveData)
        viewModel.setPlayerName(playerNameFromIntent);

        // Binding
        playerNameTV = findViewById(R.id.txtPlayerName);
        scoreTV = findViewById(R.id.txtScore);
        roundTV = findViewById(R.id.txtRound);
        squaresToRememberTV = findViewById(R.id.txtSquares);
        btnPlay = findViewById(R.id.btnPlay);

        // Observe LiveData and update UI
        viewModel.getGameData().observe(this, gameData -> {
            playerNameTV.setText(gameData.getPlayerName());
            scoreTV.setText((String.valueOf(gameData.getScore())));
            roundTV.setText(String.valueOf(gameData.getRound()));
            squaresToRememberTV.setText(String.valueOf(gameData.getSquaresToRemember()));
        });

        // TODO: Testing Game class
        /*
        GameClass gameClass = new GameClass(); // Initialize Game
        String[] gameBoard = gameClass.getGameBoard();
        for (String tile : gameBoard) {
            Log.i("tag", tile);
        }
        */

        // Add references to the tiles (ImageViews)
        ImageView tileA1 = findViewById(R.id.tileA1);
        ImageView tileA2 = findViewById(R.id.tileA2);
        ImageView tileA3 = findViewById(R.id.tileA3);
        ImageView tileA4 = findViewById(R.id.tileA4);
        ImageView tileA5 = findViewById(R.id.tileA5);
        ImageView tileA6 = findViewById(R.id.tileA6);
        ImageView tileB1 = findViewById(R.id.tileB1);
        ImageView tileB2 = findViewById(R.id.tileB2);
        ImageView tileB3 = findViewById(R.id.tileB3);
        ImageView tileB4 = findViewById(R.id.tileB4);
        ImageView tileB5 = findViewById(R.id.tileB5);
        ImageView tileB6 = findViewById(R.id.tileB6);
        ImageView tileC1 = findViewById(R.id.tileC1);
        ImageView tileC2 = findViewById(R.id.tileC2);
        ImageView tileC3 = findViewById(R.id.tileC3);
        ImageView tileC4 = findViewById(R.id.tileC4);
        ImageView tileC5 = findViewById(R.id.tileC5);
        ImageView tileC6 = findViewById(R.id.tileC6);
        ImageView tileD1 = findViewById(R.id.tileD1);
        ImageView tileD2 = findViewById(R.id.tileD2);
        ImageView tileD3 = findViewById(R.id.tileD3);
        ImageView tileD4 = findViewById(R.id.tileD4);
        ImageView tileD5 = findViewById(R.id.tileD5);
        ImageView tileD6 = findViewById(R.id.tileD6);
        ImageView tileE1 = findViewById(R.id.tileE1);
        ImageView tileE2 = findViewById(R.id.tileE2);
        ImageView tileE3 = findViewById(R.id.tileE3);
        ImageView tileE4 = findViewById(R.id.tileE4);
        ImageView tileE5 = findViewById(R.id.tileE5);
        ImageView tileE6 = findViewById(R.id.tileE6);
        ImageView tileF1 = findViewById(R.id.tileF1);
        ImageView tileF2 = findViewById(R.id.tileF2);
        ImageView tileF3 = findViewById(R.id.tileF3);
        ImageView tileF4 = findViewById(R.id.tileF4);
        ImageView tileF5 = findViewById(R.id.tileF5);
        ImageView tileF6 = findViewById(R.id.tileF6);

        // Store tiles in an array (used for randomizing tiles that light up)
        ImageView[] imageViews = {
                tileA1, tileA2, tileA3, tileA4, tileA5, tileA6,
                tileB1, tileB2, tileB3, tileB4, tileB5, tileB6,
                tileC1, tileC2, tileC3, tileC4, tileC5, tileC6,
                tileD1, tileD2, tileD3, tileD4, tileD5, tileD6,
                tileE1, tileE2, tileE3, tileE4, tileE5, tileE6,
                tileF1, tileF2, tileF3, tileF4, tileF5, tileF6
        };

        // Play Handler
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "Play Game");
               /*
                // Choose random tiles
                int squaresToRemember = viewModel.getGameData().getValue().getSquaresToRemember(); // Get the number of squares to remember from the ViewModel

                // Choose random ImageViews to change their background color
                Random random = new Random();
                Set<Integer> chosenIndices = new HashSet<>();
                while (chosenIndices.size() < squaresToRemember) {
                    chosenIndices.add(random.nextInt(imageViews.length));
                }

                // Change background color of the chosen ImageViews
                for (int index : chosenIndices) {
                    imageViews[index].setBackgroundColor(ContextCompat.getColor(Game.this, R.color.tileOn));
                }
                */
                viewModel.playGame(getApplicationContext(), imageViews);

                // TODO: Implement logic to remember which ImageViews were chosen and compare with the user's input

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