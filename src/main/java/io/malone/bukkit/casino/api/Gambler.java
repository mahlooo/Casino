package io.malone.bukkit.casino.api;

import com.google.common.base.Objects;
import io.malone.bukkit.casino.CasinoPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Gambler {

    private Player bukkit;
    private boolean playing;
    private boolean destroying;

    public Gambler(Player bukkit) {
        this.bukkit = bukkit;
        this.playing = false;
        this.destroying = false;
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
     * Gets if the Gambler can destroy Casino games.
     */
    public boolean isDestroying() {
        return destroying;
    }

    /**
     * Sets the Gambler as destroying (able to destroy Casino games).
     */
    public void setDestroying(boolean destroying) {
        this.destroying = destroying;
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
     * Prints a given message using the default formatting to the Gambler.
     */
    public void print(String msg) {
        printRaw(CasinoPlugin.PREFIX + ChatColor.GREEN + msg);
    }

    /**
     * Prints a debug message to the Gambler.
     */
    public void printDebug(String msg) {
        printRaw(CasinoPlugin.PREFIX + ChatColor.GRAY + msg);
    }

    /**
     * Prints an error to the Gambler.
     */
    public void printError(String msg) {
        printRaw(CasinoPlugin.PREFIX + ChatColor.RED + msg);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("uuid", bukkit.getUniqueId())
                .add("playing", playing)
                .add("destroying", destroying).toString();
    }

    @Override
    public int hashCode() {
        return bukkit.getUniqueId().hashCode();
    }
}
