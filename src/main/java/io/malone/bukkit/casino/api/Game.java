package io.malone.bukkit.casino.api;

public interface Game {

    void play(Gambler player);

    void tick();
}
