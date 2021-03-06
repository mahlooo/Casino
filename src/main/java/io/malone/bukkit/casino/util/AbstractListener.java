package io.malone.bukkit.casino.util;

import io.malone.bukkit.casino.CasinoPlugin;
import org.bukkit.event.Listener;

import static com.google.common.base.Preconditions.checkNotNull;

public class AbstractListener implements Listener {

    private final CasinoPlugin plugin;

    public AbstractListener(CasinoPlugin plugin) {
        checkNotNull(plugin);
        this.plugin = plugin;
    }

    public CasinoPlugin getPlugin() {
        return plugin;
    }

    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
