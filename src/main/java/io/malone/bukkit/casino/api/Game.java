package io.malone.bukkit.casino.api;

import org.bukkit.block.Block;

import java.util.List;

public interface Game {

    String getIdentifier();

    Block getBaseBlock();

    void build();

    void play(Gambler player);

    boolean isBeingUsed();

    void tick();

    int getCost();

    List<String> getPayout();
}
