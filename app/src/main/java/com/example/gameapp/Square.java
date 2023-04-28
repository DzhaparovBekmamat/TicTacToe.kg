package com.example.gameapp;

/**
 * Author: Dzhaparov Bekmamat
 */
public class Square {
    private Player player = null;

    public void fill(Player player) {
        this.player = player;
    }

    public boolean isFilled() {
        return player != null;
    }

    public Player getPlayer() {
        return player;
    }
}
