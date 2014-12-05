package io.malone.bukkit.casino.api;

import org.bukkit.entity.Player;

public class Gambler {

    private Player bukkit;
    private boolean playing;

    public Gambler(Player bukkit) {
        this.bukkit = bukkit;
        this.playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void reward() {
        // TODO: How to reward items / currency
    }
}
