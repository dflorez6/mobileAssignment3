package com.dflorez.assignment3.Views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.dflorez.assignment3.R;

import java.util.List;

import com.dflorez.assignment3.Helpers.ScoresManager; // Import Helper Class
import com.dflorez.assignment3.ViewModels.GameViewModel;

public class HighScore extends AppCompatActivity {

    // ViewModel
    GameViewModel viewModel;

    // References
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, game, high_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //====================
        // Display Saved HighScores (SharedPreferences)
        //====================
        // TODO: Only used during development
        // Reset Stored Scores
        // ScoresManager.resetHighScores(getApplicationContext());

        // Instantiate the ViewModel with a reference to the ViewModel Class
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        // Retrieve the high scores
        List<String> highScores = ScoresManager.getHighscores(getApplicationContext());

        // Display Stored Scores
        TextView txtScore = findViewById(R.id.txtHighScores);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < highScores.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(highScores.get(i)).append("\n\n");
        }
        txtScore.setText(stringBuilder.toString());


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
                redirectActivity(HighScore.this, Welcome.class, viewModel.getPlayerName().getValue());
            }
        });

        // Game
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HighScore.this, Game.class, viewModel.getPlayerName().getValue()); // redirects to Game activity
            }
        });

        // High Score Item
        high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

    }

    // Methods
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