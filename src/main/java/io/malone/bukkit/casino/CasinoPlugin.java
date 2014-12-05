package io.malone.bukkit.casino;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.malone.bukkit.casino.api.Gambler;
import io.malone.bukkit.casino.api.Game;
import io.malone.bukkit.casino.listeners.ConnectionListener;
import io.malone.bukkit.casino.listeners.InteractListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CasinoPlugin extends JavaPlugin {

    private Set<Game> games = Sets.newHashSet();
    private Map<UUID, Gambler> gamblers = Maps.newHashMap();

    @Override
    public void onEnable() {
        // Register all online players
        for (Player online : getServer().getOnlinePlayers()) {
            registerPlayer(online);
        }

        // Register listeners
        (new ConnectionListener(this)).registerEvents();
        (new InteractListener(this)).registerEvents();

        // Load games
        // TODO
    }

    @Override
    public void onDisable() {
        // End all running games
        for (Game game : games) {
            if (game.isBeingUsed()) {
                game.end();
            }
        }

        gamblers.clear();
        games.clear();
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

    public Set<Game> getGames() {
        return games;
    }

    public Map<UUID, Gambler> getGamblers() {
        return gamblers;
    }
}
