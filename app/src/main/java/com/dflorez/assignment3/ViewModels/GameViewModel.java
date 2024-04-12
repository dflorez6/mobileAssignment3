package com.dflorez.assignment3.ViewModels;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dflorez.assignment3.Models.GameClass;
import com.dflorez.assignment3.R;
import com.dflorez.assignment3.Views.Game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    public void playGame(Context context, ImageView[] imageViews) {
        // Choose random tiles (ImageViews)
        int squaresToRemember = gameClass.getSquaresToRemember();
        Random random = new Random();
        Set<Integer> chosenArrayIndices = new HashSet<>();
        while (chosenArrayIndices.size() < squaresToRemember) {
            chosenArrayIndices.add(random.nextInt(imageViews.length));
        }

        // Change tile color (Light up)
        for (int index : chosenArrayIndices) {
            imageViews[index].setBackgroundColor(ContextCompat.getColor(context, R.color.tileOn));
        }
    }
}
