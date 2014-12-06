package io.malone.bukkit.casino.util;

import com.google.common.collect.Sets;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.malone.bukkit.casino.CasinoPlugin;
import io.malone.bukkit.casino.api.Game;
import org.bukkit.block.Block;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class GameTypeAdapter extends TypeAdapter<Game> {

    private final CasinoPlugin plugin;

    public GameTypeAdapter(CasinoPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void write(JsonWriter writer, Game game) throws IOException {
        writer.beginObject();

        // Write game type
        writer.name("identifier").value(game.getIdentifier());

        // Write base block
        writer.name("base");
        Block base = game.getBaseBlock();
        writer.beginObject();
        writer.name("world").value(base.getWorld().getUID().toString());
        writer.name("x").value(base.getX());
        writer.name("y").value(base.getY());
        writer.name("z").value(base.getZ());
        writer.endObject();

        // Write all blocks belonging to the game
        writer.name("blocks");
        writer.beginArray();
        for (Block block : game.getBlocks()) {
            writer.beginObject();
            writer.name("world").value(block.getWorld().getUID().toString());
            writer.name("x").value(block.getX());
            writer.name("y").value(block.getY());
            writer.name("z").value(block.getZ());
            writer.endObject();
        }
        writer.endArray();

        writer.endObject();
    }

    @Override
    public Game read(JsonReader reader) throws IOException {
        String identifier;
        Block base;
        Set<Block> blocks = Sets.newHashSet();

        // Read JSON
        reader.beginObject();

        // Read identifier
        identifier = reader.nextString();

        // Read base block
        reader.beginObject();
        base = plugin.getServer().getWorld(UUID.fromString(reader.nextString()))
                .getBlockAt(reader.nextInt(), reader.nextInt(), reader.nextInt());
        reader.endObject();

        // Read blocks
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            Block block = plugin.getServer().getWorld(UUID.fromString(reader.nextString()))
                    .getBlockAt(reader.nextInt(), reader.nextInt(), reader.nextInt());
            blocks.add(block);
            reader.endObject();
        }
        reader.endArray();

        reader.endObject();

        // Return appropriate Game object
        Game game = null;
        if (identifier.equals("slots")) {
            // game = new SlotMachine(...);
        }

        return game;
    }
}
