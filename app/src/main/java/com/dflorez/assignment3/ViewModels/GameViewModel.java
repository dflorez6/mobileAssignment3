package com.dflorez.assignment3.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dflorez.assignment3.Models.GameClass;

public class GameViewModel extends ViewModel {

    //==========
    // Global
    //==========

    //==========
    // LiveData
    //==========
    private GameClass gameClass = new GameClass();
    private MutableLiveData<GameClass> GameData;
    private MutableLiveData<String> PlayerNameData = new MutableLiveData<>();

    //==========
    // Getter/Setters
    //==========
    public MutableLiveData<String> getPlayerName() {
        return PlayerNameData;
    }

    public void setPlayerName(String name) {
        PlayerNameData.setValue(name);
        gameClass.setPlayerName(name); // Update the GameClass instance attribute
    }

    //==========
    // Constructor
    //==========
    public GameViewModel() {
        setPlayerName("");
        GameData = new MutableLiveData<>();
        // GameData.setValue(game); // Initialize GameData with a Game instance (empty game board)

    }

    //====================
    // Methods
    //====================
}
