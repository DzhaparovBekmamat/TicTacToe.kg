package com.example.gameapp;

/**
 * Author: Dzhaparov Bekmamat
 */
public class WinnerCheckerVertical implements WinnerCheckerInterface {
    private final Game game;

    public WinnerCheckerVertical(Game game) {
        this.game = game;
    }

    public Player checkWinner() {
        Square[][] field = game.getField();
        Player currentPlayer;
        Player lastPlayer;
        for (int i = 0; i < field.length; i++) {
            lastPlayer = null;
            int successCounter = 1;
            for (int j = 0; j < field[i].length; j++) {
                currentPlayer = field[j][i].getPlayer();
                if (currentPlayer == lastPlayer && currentPlayer != null) {
                    successCounter++;
                    if (successCounter == field[i].length) {
                        return currentPlayer;
                    }
                }
                lastPlayer = currentPlayer;
            }
        }
        return null;
    }
}