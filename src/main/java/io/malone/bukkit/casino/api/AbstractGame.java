package io.malone.bukkit.casino.api;

import org.bukkit.block.Block;

public abstract class AbstractGame implements Game {

    protected Block baseBlock;
    protected boolean beingUsed;

    public AbstractGame(Block baseBlock, boolean beingUsed) {
        this.baseBlock = baseBlock;
        this.beingUsed = beingUsed;
    }

    @Override
    public Block getBaseBlock() {
        return baseBlock;
    }

    @Override
    public boolean isBeingUsed() {
        return beingUsed;
    }
}
