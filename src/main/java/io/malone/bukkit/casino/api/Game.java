package io.malone.bukkit.casino.api;

public interface Game {

    void play(GamePlayer player);

    void tick();
}
