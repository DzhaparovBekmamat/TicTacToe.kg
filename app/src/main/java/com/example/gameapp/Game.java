package com.example.gameapp;

/**
 * Author: Dzhaparov Bekmamat
 */
public class Game {
    private final Player[] players;
    private final Square[][] field;
    private Player activePlayer;
    private int filled;
    private int squareCount;
    private final WinnerCheckerInterface[] winnerCheckers;

    public Game() {
        winnerCheckers = new WinnerCheckerInterface[4];
        winnerCheckers[0] = new WinnerCheckerHorizontal(this);
        winnerCheckers[1] = new WinnerCheckerVertical(this);
        winnerCheckers[2] = new WinnerCheckerDiagonalLeft(this);
        winnerCheckers[3] = new WinnerCheckerDiagonalRight(this);
        field = new Square[3][3];
        squareCount = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Square();
                squareCount++;
            }
        }

        players = new Player[2];
        activePlayer = null;
        filled = 0;
    }

    public void start() {
        resetPlayers();
    }

    private void resetPlayers() {
        players[0] = new Player("X");
        players[1] = new Player("O");
        setCurrentActivePlayer(players[0]);
    }

    private void setCurrentActivePlayer(Player player) {
        activePlayer = player;
    }

    public Square[][] getField() {
        return field;
    }

    public boolean makeTurn(int x, int y) {
        if (field[x][y].isFilled()) {
            return false;
        }
        field[x][y].fill(getCurrentActivePlayer());
        filled++;
        switchPlayers();
        return true;
    }

    private void switchPlayers() {
        activePlayer = (activePlayer == players[0]) ? players[1] : players[0];
    }

    public Player getCurrentActivePlayer() {
        return activePlayer;
    }

    public boolean isFieldFilled() {
        return squareCount == filled;
    }

    public void reset() {
        resetField();
        resetPlayers();
    }

    private void resetField() {
        for (Square[] squares : field) {
            for (Square square : squares) {
                square.fill(null);
            }
        }
        filled = 0;
    }

    public Player checkWinner() {
        for (WinnerCheckerInterface winChecker : winnerCheckers) {
            Player winner = winChecker.checkWinner();
            if (winner != null) {
                return winner;
            }
        }
        return null;
    }
}

