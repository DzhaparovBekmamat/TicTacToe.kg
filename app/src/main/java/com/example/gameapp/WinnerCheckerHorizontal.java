package com.example.gameapp;

/**
 * Author: Dzhaparov Bekmamat
 */
public class WinnerCheckerHorizontal implements WinnerCheckerInterface {
    private final Game game;

    public WinnerCheckerHorizontal(Game game) {
        this.game = game;
    }

    public Player checkWinner() {
        Square[][] field = game.getField();
        Player currentPlayer;
        Player lastPlayer;
        for (Square[] squares : field) {
            lastPlayer = null;
            int successCounter = 1;
            for (Square square : squares) {
                currentPlayer = square.getPlayer();
                if (currentPlayer == lastPlayer && currentPlayer != null) {
                    successCounter++;
                    if (successCounter == squares.length) {
                        return currentPlayer;
                    }
                }
                lastPlayer = currentPlayer;
            }
        }
        return null;
    }
}

