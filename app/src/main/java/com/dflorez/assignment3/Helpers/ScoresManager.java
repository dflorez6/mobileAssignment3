package com.dflorez.assignment3.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScoresManager {
    private static final String PREFERENCE_NAME = "HighScores";
    private static final String DEFAULT_SCORES = "David 120 pts,Jane 100 pts,Mark 90 pts"; // Default Scores

    //====================
    // Methods
    //====================
    // Add score to the SharedPreferences
    public static void addScore(Context context, String playerName, int score) {
        // Check if playerName is empty
        if (playerName.trim().isEmpty()) {
            return; // Prevents creating a score with an empty name
        }

        // Initializations
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Retrieve current scores
        List<String> highScores = new ArrayList<>(getHighscores(context));

        // Add new scores
        highScores.add(playerName + " " + score + " pts");

        // Save the updated scores
        editor.putString("highScores", TextUtils.join(",",  highScores));
        editor.apply();
    }

    // Retrieve High Scores from the SharedPreferences
    public static List<String> getHighscores(Context context) {
        // Initializations
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        // Retrieve stored scores or default value
        String storedHighScores = sharedPreferences.getString("highScores", DEFAULT_SCORES);
        List<String> highScores = Arrays.asList(storedHighScores.split(","));

        // Sort High Scores (Highest to lowest score)
        highScores.sort((s1, s2) -> Integer.compare(
                Integer.parseInt(s2.split(" ")[1]), Integer.parseInt(s1.split(" ")[1])
        ));

        return highScores.subList(0, Math.min(highScores.size(), 3)); // Returns Top 3 scores
    }

    // Reset Stored Scores (utility to clear stored scores. Used for testing purposes)
    public static void resetHighScores(Context context) {
        // Initializations
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Clear stored values from SharedPreferences
        editor.clear();
        editor.apply();
    }

}
