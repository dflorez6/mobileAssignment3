package com.dflorez.assignment3.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dflorez.assignment3.Models.GameClass;
import com.dflorez.assignment3.R;
import com.dflorez.assignment3.Views.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.os.Handler;
import android.os.Looper;

public class GameViewModel extends ViewModel {

    //==========
    // Global
    //==========
    final Integer SCORE = 10;

    //==========
    // LiveData
    //==========
    private GameClass gameClass = new GameClass(); // GameClass instance
    private MutableLiveData<GameClass> GameData = new MutableLiveData<>(); // Holds the GameClass instance
    private MutableLiveData<String> PlayerNameData = new MutableLiveData<>();
    private MutableLiveData<Integer> ScoreData = new MutableLiveData<>();
    private MutableLiveData<Integer> RoundData = new MutableLiveData<>();
    private MutableLiveData<Integer> SquaresToRememberData = new MutableLiveData<>();

    //==========
    // Getter/Setters
    //==========
    public MutableLiveData<GameClass> getGameData() {
        return GameData;
    }

    public MutableLiveData<String> getPlayerName() {
        return PlayerNameData;
    }

    public void setPlayerName(String name) {
        PlayerNameData.setValue(name);
        gameClass.setPlayerName(name); // Update the GameClass instance attribute
    }

    public MutableLiveData<Integer> getScore() {
        return ScoreData;
    }

    public void setScore(Integer score) {
        ScoreData.setValue(score);
        gameClass.setScore(score); // Update the GameClass instance attribute
    }

    public MutableLiveData<Integer> getRound() {
        return RoundData;
    }

    public void setRound(Integer round) {
        RoundData.setValue(round);
        gameClass.setRound(round); // Update the GameClass instance attribute
    }

    public MutableLiveData<Integer> getSquaresToRemember() {
        return SquaresToRememberData;
    }

    public void setSquaresToRemember(Integer squares) {
        SquaresToRememberData.setValue(squares);
        gameClass.setSquaresToRemember(squares); // Update the GameClass instance attribute
    }

    //==========
    // Constructor
    //==========
    public GameViewModel() {
        setPlayerName("");
        GameData.setValue(gameClass);
        // GameData.setValue(game); // Initialize GameData with a Game instance (empty game board)

    }

    //====================
    // Methods
    //====================
    public void playGame(Context context, ImageView[] imageViews, TextView countDownTV) {
        // Choose random tiles (ImageViews)
        int squaresToRemember = gameClass.getSquaresToRemember();
        Random random = new Random();
        List<String> activeTileIds = new ArrayList<>(); // Will hold the activeTiles. Used to check if the user clicked the correct ones

        Log.i("tag", "Play Game: After List");

        // Pick X number of tiles (set by the difficulty) that need to be remember by the user. That means that they will change bg color (light up)
        while (activeTileIds.size() < squaresToRemember) {
            int randomIndex = random.nextInt(imageViews.length);
            String tileId = context.getResources().getResourceEntryName(imageViews[randomIndex].getId());
            activeTileIds.add(tileId);
        }

        Log.i("tag", "Play Game: After While");

        // Add the randomly selected tileIds to the GameClass instance
        gameClass.setActiveTiles(activeTileIds);

        Log.i("tag", "-----------------");
        for (String t : activeTileIds) {
            Log.i("tag", "activeTileIds " + t);
        }
        Log.i("tag", "-----------------");

        Log.i("tag", "Play Game: Add to instance ActiveTiles");

        // Trying New Code - Ran out of time - Ran into issues trying to get the ImageView Id to change the BG
        // Change Tile bg color
            /*
        for (String tileId : activeTileIds) {
            Log.i("tag", "Play Game: For change BG Color - Start");

            ImageView imageView = null;
            for (ImageView iv : imageViews) {
                if (iv.getTag() != null && iv.getTag().equals(tileId)) {
                    imageView = iv;
                    break;
                }
            }
            if (imageView != null) {
                imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.tileOn));
                Log.i("tag", "Play Game: For change BG Color - set BG");
                Log.i("tag", "Stored randomly generated tiles");
                Log.i("tag", tileId);
            } else {
                Log.e("tag", "ImageView not found for tile ID: " + tileId);
            }

            /*
            int resourceId = context.getResources().getIdentifier(tileId, "id", context.getPackageName());
            Log.i("tag", "Play Game: For change BG Color - resourceId " + resourceId);
            Log.i("tag", "-----------------");
            ImageView imageView = ((Activity) context).findViewById(resourceId);
            Log.i("tag", "Play Game: For change BG Color - imageView");
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.tileOn));
            Log.i("tag", "Play Game: For change BG Color - set BG");
            Log.i("tag", "Stored randomly generated tiles");
            Log.i("tag", tileId);
        }
            */

        // Old Code, working
        Set<Integer> chosenArrayIndices = new HashSet<>();
        while (chosenArrayIndices.size() < squaresToRemember) {
            chosenArrayIndices.add(random.nextInt(imageViews.length));
        }

        // Change tile color (Light up)
        // Old Code working
        for (int index : chosenArrayIndices) {
            imageViews[index].setBackgroundColor(ContextCompat.getColor(context, R.color.tileOn));
        }

        // Runnable to show the tiles light up for 5 seconds and the countdown timer going down to from 5 to 0 seconds
        //  Once its over, reset tiles back to the original color
        final int[] counter = {5}; // 5 seconds to play
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Checks if count has reached 0 "seconds"
                if (counter[0] >= 0) {
                    countDownTV.setText(String.valueOf(counter[0]));
                    counter[0]--;
                    // Repeat every second
                    handler.postDelayed(this, 1000);
                } else {
                    // Go back to the original tile color after 5 seconds
                    for (int index : chosenArrayIndices) {
                        imageViews[index].setBackgroundColor(ContextCompat.getColor(context, R.color.tileOff));

                        // Part of the new code I was trying - Ran out of Time
                        // for (String tileId : activeTileIds) {
                        // int resourceId = context.getResources().getIdentifier(tileId, "id", context.getPackageName());
                        // ImageView imageView = ((Activity) context).findViewById(resourceId);
                        // imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.tileOff));
                    }

                    // Reset countdown timer
                    countDownTV.setText("5");
                }
            }
        }, 1000);

    }
}
