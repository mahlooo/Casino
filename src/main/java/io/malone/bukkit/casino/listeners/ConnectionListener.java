package io.malone.bukkit.casino.listeners;

import io.malone.bukkit.casino.CasinoPlugin;
import io.malone.bukkit.casino.util.AbstractListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener extends AbstractListener {

    public ConnectionListener(CasinoPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        getPlugin().registerPlayer(event.getPlayer());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        getPlugin().removePlayer(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        getPlugin().removePlayer(event.getPlayer());
    }
}
