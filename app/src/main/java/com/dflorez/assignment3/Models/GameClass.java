package com.dflorez.assignment3.Models;

public class GameClass {
    //====================
    // Global Variables / Constants
    //====================

    //====================
    // Attributes
    //====================
    private String[] GameBoard;
    private String PlayerName;
    // TODO: Fields
    // Round
    // Difficulty -> number of squares that light up
    // WonRoundCounter -> Every 3 won rounds, difficulty increases == more squares light up
    // Score -> Earn 10 points per won round

    //====================
    // Setters/Getters
    //====================
    public String[] getGameBoard() {
        return GameBoard;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }


    //====================
    // Constructors
    //====================
    // Default
    public GameClass() {
        GameBoard = new String[] {
            "tileA1", "tileA2", "tileA3", "tileA4", "tileA5", "tileA6",
            "tileB1", "tileB2", "tileB3", "tileB4", "tileB5", "tileB6",
            "tileC1", "tileC2", "tileC3", "tileC4", "tileC5", "tileC6",
            "tileD1", "tileD2", "tileD3", "tileD4", "tileD5", "tileD6",
            "tileE1", "tileE2", "tileE3", "tileE4", "tileE5", "tileE6",
            "tileF1", "tileF2", "tileF3", "tileF4", "tileF5", "tileF6"
        };


    }

    // Non-default

    //====================
    // Methods
    //====================

}
