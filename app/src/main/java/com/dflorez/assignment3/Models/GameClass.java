package com.dflorez.assignment3.Models;

import java.util.ArrayList;
import java.util.List;

public class GameClass {
    //====================
    // Global Variables / Constants
    //====================


    //====================
    // Attributes
    //====================
    private String[] GameBoard;
    private String PlayerName;
    private Integer Round; // Starts at round 1
    private Integer Difficulty; // Default values
    private Integer VictoryCounter; // Every 3 won rounds, difficulty increases == more squares light up
    private Integer SquaresToRemember; // Number of squares to remember
    private Integer Score; // Earn 10 points per won round
    private List<String> ActiveTiles; // Stores the highlighted tiles. Useful to check if the user clicked the correct tile

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

    public Integer getRound() {
        return Round;
    }

    public void setRound(Integer round) {
        Round = round;
    }

    public Integer getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        Difficulty = difficulty;
    }

    public Integer getVictoryCounter() {
        return VictoryCounter;
    }

    public void setVictoryCounter(Integer victoryCounter) {
        VictoryCounter = victoryCounter;
    }

    public Integer getSquaresToRemember() {
        return SquaresToRemember;
    }

    public void setSquaresToRemember(Integer squaresToRemember) {
        SquaresToRemember = squaresToRemember;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Integer getActiveTiles() {
        return Score;
    }

    public void setActiveTiles(List<String> activeTiles) {
        ActiveTiles = activeTiles;
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
        setRound(1); // Starts at round 1
        setDifficulty(1); // Starts on easy
        setVictoryCounter(0); // No wins yet
        setScore(0);
        squaresToLightUp(getDifficulty());
        setActiveTiles(new ArrayList<>()); // Initializes List to hold ActiveTiles
    }

    // Non-default

    //====================
    // Methods
    //====================
    public void squaresToLightUp(Integer difficulty) {
        // Depending on the difficulty, the number of squares to remember will increase
        switch (difficulty) {
            case 2:
                setSquaresToRemember(3);
                break;
            case 3:
                setSquaresToRemember(4);
                break;
            case 4:
                setSquaresToRemember(5);
                break;
            case 5:
                setSquaresToRemember(6);
                break;
            default:
                setSquaresToRemember(2);
                break;
        }

    }
}
