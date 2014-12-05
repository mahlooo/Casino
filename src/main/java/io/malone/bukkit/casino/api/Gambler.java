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

    public boolean testPermission(String permission) {
        return bukkit.hasPermission(permission);
    }

    public void message(String msg) {
        bukkit.sendMessage(msg);
    }
}
