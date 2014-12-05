package io.malone.bukkit.casino;

import com.google.common.collect.Maps;
import io.malone.bukkit.casino.api.Gambler;
import io.malone.bukkit.casino.listeners.ConnectionListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class CasinoPlugin extends JavaPlugin {

    private Map<UUID, Gambler> gamblers = Maps.newHashMap();

    @Override
    public void onEnable() {
        // Register listeners
        (new ConnectionListener(this)).registerEvents();
    }

    @Override
    public void onDisable() {
        // End all running games

        // Clear gamblers map
        gamblers.clear();
    }

    public void registerPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        Gambler gambler = new Gambler(player);

        gamblers.put(uuid, gambler);
    }

    public void removePlayer(Player player) {
        UUID uuid = player.getUniqueId();

        gamblers.remove(uuid);
    }
}
