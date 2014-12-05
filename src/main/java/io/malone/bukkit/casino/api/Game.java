package io.malone.bukkit.casino.api;

import java.util.List;

public interface Game {

    String getIdentifier();

    void play(Gambler player);

    boolean isBeingUsed();

    void tick();

    int getCost();

    List<String> getPayout();
}
