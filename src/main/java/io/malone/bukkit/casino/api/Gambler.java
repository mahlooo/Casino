package io.malone.bukkit.casino.api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Gambler {

    private Player bukkit;
    private boolean playing;

    public Gambler(Player bukkit) {
        this.bukkit = bukkit;
        this.playing = false;
    }

    /**
     * Gets if the Gambler is playing a game.
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Sets the Gambler as playing.
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * Tests the Gambler for the given permission.
     */
    public boolean testPermission(String permission) {
        return bukkit.hasPermission(permission);
    }

    /**
     * Prints a given message to the Gambler.
     */
    public void printRaw(String msg) {
        bukkit.sendMessage(msg);
    }

    /**
     * Prints an error to the Gambler.
     */
    public void printError(String msg) {
        printRaw(ChatColor.RED + msg);
    }
}
