package io.malone.bukkit.casino.api;

import io.malone.bukkit.casino.CasinoPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {

    private final CasinoPlugin plugin;
    private final Game game;

    public GameRunnable(CasinoPlugin plugin, Game game) {
        this.plugin = plugin;
        this.game = game;

        // Run this
        this.runTaskTimer(this.plugin, 0L, 1L);
    }

    @Override
    public void run() {
        if (!game.tick()) {
            cancel();
        }
    }
}
