package io.malone.bukkit.casino.api;

import org.bukkit.entity.Player;

public class GamePlayer {

    private Player bukkit;
    private boolean playing;

    public GamePlayer(Player bukkit) {
        this.bukkit = bukkit;
        this.playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
