package io.malone.bukkit.casino.api;

import org.bukkit.entity.Player;

public class Gambler {

    private Player bukkit;
    private boolean playing;
    private boolean rigged;

    public Gambler(Player bukkit) {
        this.bukkit = bukkit;
        this.playing = false;
        this.rigged = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isRigged() {
        return rigged;
    }

    public void setRigged(boolean rigged) {
        this.rigged = rigged;
    }

    public boolean testPermission(String permission) {
        return bukkit.hasPermission(permission);
    }

    public void message(String msg) {
        bukkit.sendMessage(msg);
    }
}
