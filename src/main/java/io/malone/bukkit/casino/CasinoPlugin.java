package io.malone.bukkit.casino;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.malone.bukkit.casino.api.Gambler;
import io.malone.bukkit.casino.api.Game;
import io.malone.bukkit.casino.listeners.ConnectionListener;
import io.malone.bukkit.casino.listeners.InteractListener;
import io.malone.bukkit.casino.util.GameTypeAdapter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CasinoPlugin extends JavaPlugin {

    public static final String PREFIX = ChatColor.WHITE + "[" + ChatColor.GRAY + "Casino" + ChatColor.WHITE + "] ";

    private Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .registerTypeAdapter(Game.class, new GameTypeAdapter(this))
            .create();

    private File gamesFile = new File(getDataFolder(), "games.json");
    private Set<Game> games = Sets.newHashSet();
    private Map<UUID, Gambler> gamblers = Maps.newHashMap();

    @Override
    public void onEnable() {
        // Register all online players
        for (Player online : getServer().getOnlinePlayers()) {
            registerPlayer(online);
        }

        // Register listeners
        (new ConnectionListener(this)).registerEvents();
        (new InteractListener(this)).registerEvents();

        // Load games
        loadGames();
    }

    @Override
    public void onDisable() {
        // End all running games
        for (Game game : games) {
            if (game.isBeingUsed()) {
                game.end();
            }
        }

        // Save games
        saveGames();

        gamblers.clear();
        games.clear();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("casino")) {
            return false;
        }

        Gambler gambler = null;
        if (sender instanceof Player) {
            gambler = gamblers.get(((Player) sender).getUniqueId());
        }

        if (args.length == 0) {
            args = new String[1];
            args[0] = "";
        }

        String sub = args[0].toLowerCase();

        if (sub.equals("ver") || sub.equals("version")) {
            sender.sendMessage(PREFIX
                    + ChatColor.DARK_GREEN + "Running version " + ChatColor.GREEN + getDescription().getVersion()
                    + ChatColor.DARK_GREEN + " authored by " + ChatColor.GREEN + getDescription().getAuthors());
        } else if (sub.equals("stats")) {
            if (sender.hasPermission("casino.stats")) {
                sender.sendMessage(PREFIX
                        + ChatColor.DARK_GREEN + "There are currently "
                        + ChatColor.GREEN + games.size() + (games.size() == 1 ? " game" : " games")
                        + ChatColor.DARK_GREEN + " registered.");
            } else {
                sender.sendMessage(PREFIX + ChatColor.RED + "You don't have permission.");
            }
        } else if (sub.equals("destroy")) {
            if (gambler == null) {
                sender.sendMessage(PREFIX + ChatColor.RED + "Only players can use this command!");
            } else {
                if (sender.hasPermission("casino.destroy")) {
                    // Toggle destroying
                    gambler.setDestroying(!gambler.isDestroying());
                    gambler.print("You are now " + (gambler.isDestroying() ? "able" : "unable") + " to destroy Casino games.");
                } else {
                    gambler.printError("You don't have permission.");
                }
            }
        } else {
            sender.sendMessage(PREFIX + ChatColor.GRAY + "Usage: /casino [version|stats|destroy]");
        }

        return true;
    }

    public void loadGames() {
        getLogger().info("Loading games...");
        try {
            if (gamesFile.exists()) {
                JsonReader reader = new JsonReader(new FileReader(gamesFile));

                List<Game> gameList = gson.fromJson(reader, new TypeToken<List<Game>>() {
                }.getType());
                games.addAll(gameList);
                getLogger().info(gameList.size() + (gameList.size() == 1 ? " game" : " games") + " loaded.");

                reader.close();
            } else {
                getLogger().info("No games.json file found.");
            }
        } catch (FileNotFoundException e) {
            getLogger().warning(gamesFile.getName() + " file not found, unable to load games.");
        } catch (IOException e) {
            getLogger().warning("Unable to close games.json file reader.");
        }
    }

    public void saveGames() {
        getLogger().info("Saving games...");
        try {
            JsonWriter writer = new JsonWriter(new FileWriter(gamesFile));

            // Write all existing games
            writer.beginArray();
            for (Game game : games) {
                writer.value(gson.toJson(game));
            }
            writer.endArray();

            writer.flush();
            writer.close();

            getLogger().info(games.size() + (games.size() == 1 ? " game" : " games") + " saved.");
        } catch (IOException e) {
            getLogger().warning("Unable to save games.json file");
        }
    }

    public void registerPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        Gambler gambler = new Gambler(player);

        gamblers.put(uuid, gambler);
    }

    public void removePlayer(Player player) {
        UUID uuid = player.getUniqueId();

        gamblers.remove(uuid);
    }

    public void registerGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public Set<Game> getGames() {
        return games;
    }

    public Map<UUID, Gambler> getGamblers() {
        return gamblers;
    }
}
