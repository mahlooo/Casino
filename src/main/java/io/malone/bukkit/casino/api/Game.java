package io.malone.bukkit.casino.api;

import org.bukkit.block.Block;

import java.util.List;

public interface Game {

    /**
     * Gets the identifier of this type of game.
     */
    String getIdentifier();

    /**
     * Gets the base block for this game. Use for identification and interaction of games.
     */
    Block getBaseBlock();

    /**
     * Builds the game in the world.
     */
    void build();

    /**
     * Plays the game with the given gambler. This will result in the game being used ({@link Game#isBeingUsed()}).
     *
     * @param player the gambler to play the game
     */
    void play(Gambler player);

    /**
     * Ends the game forcefully. Used when the plugin is being disabled.
     */
    void end();

    /**
     * Returns if the game is being used.
     */
    boolean isBeingUsed();

    /**
     * Called every tick (1/20th of a second) when the game is running.
     */
    void tick();

    /**
     * Gets the cost of this game.
     */
    int getCost();

    /**
     * Gets a list of commands to be executed when this game is won.
     */
    List<String> getPayout();
}
