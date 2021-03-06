package io.malone.bukkit.casino.api;

import com.google.common.collect.Sets;
import io.malone.bukkit.casino.CasinoPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractGame implements Game {

    protected transient final CasinoPlugin plugin;
    protected transient Gambler gambler;
    protected transient boolean beingUsed;
    protected Block baseBlock;
    protected Set<Block> blocks;

    public AbstractGame(CasinoPlugin plugin, Block baseBlock) {
        checkNotNull(plugin);
        checkNotNull(baseBlock);
        this.plugin = plugin;
        this.baseBlock = baseBlock;
        this.gambler = null;
        this.beingUsed = false;

        this.blocks = Sets.newHashSet();
        registerBlock(this.baseBlock);
    }

    @Override
    public void play(Gambler player) {
        if (isBeingUsed()) {
            throw new IllegalStateException("Game cannot start when it is already being used!");
        }

        beingUsed = true;
        gambler = player;

        new GameRunnable(plugin, this);
    }

    @Override
    public Block getBaseBlock() {
        return baseBlock;
    }

    @Override
    public boolean isBeingUsed() {
        return beingUsed;
    }

    @Override
    public Set<Block> getBlocks() {
        return blocks;
    }

    public void registerBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public void destroy() {
        for (Block block : getBlocks()) {
            block.setType(Material.AIR);
        }

        plugin.getGames().remove(this);
    }
}
