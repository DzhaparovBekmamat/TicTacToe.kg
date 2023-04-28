package com.example.gameapp;

/**
 * Author: Dzhaparov Bekmamat
 */
public class WinnerCheckerDiagonalLeft implements WinnerCheckerInterface {
    private final Game game;

    public WinnerCheckerDiagonalLeft(Game game) {
        this.game = game;
    }

    public Player checkWinner() {
        Square[][] field = game.getField();
        Player currentPlayer;
        Player lastPlayer = null;
        int successCounter = 1;
        for (int i = 0; i < field.length; i++) {
            currentPlayer = field[i][i].getPlayer();
            if (currentPlayer != null) {
                if (lastPlayer == currentPlayer) {
                    successCounter++;
                    if (successCounter == field.length) {
                        return currentPlayer;
                    }
                }
            }
            lastPlayer = currentPlayer;
        }
        return null;
    }
}