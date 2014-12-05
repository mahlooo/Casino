package io.malone.bukkit.casino.listeners;

import io.malone.bukkit.casino.CasinoPlugin;
import io.malone.bukkit.casino.api.Gambler;
import io.malone.bukkit.casino.api.Game;
import io.malone.bukkit.casino.util.AbstractListener;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class InteractListener extends AbstractListener {

    public InteractListener(CasinoPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();

        Game game = null;
        for (Game temp : getPlugin().getGames()) {
            if (temp.getBaseBlock().equals(clickedBlock)) {
                if (game == null) {
                    game = temp;
                } else {
                    // More than one found
                    return;
                }
            }
        }

        if (game == null) {
            return;
        }

        event.setCancelled(true);

        UUID uuid = event.getPlayer().getUniqueId();
        Gambler gambler = getPlugin().getGamblers().get(uuid);

        if (gambler.isPlaying()) {
            gambler.printError("You are already playing a game.");
            return;
        }

        if (game.isBeingUsed()) {
            gambler.printError("This game is currently being used.");
            return;
        }

        gambler.setPlaying(true);
        game.play(gambler);
    }
}
