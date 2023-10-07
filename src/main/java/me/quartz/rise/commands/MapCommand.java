package me.quartz.rise.commands;

import me.quartz.rise.Rise;
import me.quartz.rise.game.Arena;
import me.quartz.rise.game.ArenaType;
import me.quartz.rise.game.Map;
import me.quartz.rise.game.MapCreator;
import me.quartz.rise.utils.BlocksUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class MapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length > 0) {
            if(args[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------");
                commandSender.sendMessage(ChatColor.YELLOW + "/map create");
                commandSender.sendMessage(ChatColor.YELLOW + "/map list");
                commandSender.sendMessage(ChatColor.YELLOW + "/map info");
                commandSender.sendMessage(ChatColor.YELLOW + "/map wand");
                commandSender.sendMessage(ChatColor.YELLOW + "/map waiting " + ChatColor.GREEN + "[id]");
                commandSender.sendMessage(ChatColor.YELLOW + "/map stairs " + ChatColor.GREEN + "[id] [arena]");
                commandSender.sendMessage(ChatColor.YELLOW + "/map door " + ChatColor.GREEN + "[id] [arena]");
                commandSender.sendMessage(ChatColor.YELLOW + "/map location " + ChatColor.GREEN + "[id] [arena] [1-2]");
                commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------");
            } else if (args[0].equalsIgnoreCase("create")) {
                Map map = Rise.getInstance().getMapManager().createMap();
                commandSender.sendMessage(ChatColor.GREEN + "Map " + map.getId() + " has been created.");
            } else if (args[0].equalsIgnoreCase("wand")) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    ItemStack itemStack = new ItemStack(Material.STICK);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.RED + "Map Manager");
                    itemStack.setItemMeta(itemMeta);
                    player.getInventory().addItem(itemStack);
                    commandSender.sendMessage(ChatColor.GREEN + "You received the map wand.");
                } else commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            } else if (args[0].equalsIgnoreCase("list")) {
                commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------");
                if (!Rise.getInstance().getMapManager().getMaps().isEmpty()) {
                    for (Map map : Rise.getInstance().getMapManager().getMaps())
                        commandSender.sendMessage(ChatColor.YELLOW + "Map " + map.getId() + ChatColor.GRAY + " - " + (map.isReady() ? ChatColor.GREEN + "Ready" : ChatColor.RED + "Not Ready"));
                } else commandSender.sendMessage(ChatColor.RED + "There are no existing maps.");
                commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------");
            } else if (args[0].equalsIgnoreCase("info")) {
                if (args.length > 1) {
                    Map map = Rise.getInstance().getMapManager().getMapById(Integer.parseInt(args[1]));
                    if (map != null) {
                        commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------");
                        if (!Rise.getInstance().getMapManager().getMaps().isEmpty()) {
                            commandSender.sendMessage(ChatColor.YELLOW + "Map " + map.getId() + ChatColor.GRAY + " (" + (map.isReady() ? ChatColor.GREEN + "Ready" : ChatColor.RED + "Ready") + ChatColor.GRAY + ")");
                            commandSender.sendMessage(" ");
                            for (Arena arena : map.getArenas()) {
                                commandSender.sendMessage(
                                        ChatColor.YELLOW.toString() + arena.getArenaType() + " Arena " + arena.getId() + ChatColor.GRAY + " - " +
                                                (arena.getArenaType() != ArenaType.FINAL ? (
                                                        (arena.getDoor().isEmpty() ? ChatColor.RED + "Door" : ChatColor.GREEN + "Door") + ChatColor.GRAY + " - " +
                                                        (arena.getStair().isEmpty() ? ChatColor.RED + "Stairs" : ChatColor.GREEN + "Stairs") + ChatColor.GRAY + " - ")
                                                        : "") +
                                                        (arena.getSpawnLocations().stream().filter(Objects::nonNull).count() != 2 ? ChatColor.RED + "Spawn Locations" : ChatColor.GREEN + "Spawn Locations")
                                );
                            }
                        } else commandSender.sendMessage(ChatColor.RED + "There are no existing maps.");
                        commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-------------------");
                    } else commandSender.sendMessage(ChatColor.RED + "Map with id " + args[1] + " wasn't found.");
                } else commandSender.sendMessage(ChatColor.RED + "Usage: /map info [id]");
            } else if (args[0].equalsIgnoreCase("waiting")) {
                if(args.length > 1) {
                    if (commandSender instanceof Player) {
                        Player player = (Player) commandSender;
                        Map map = Rise.getInstance().getMapManager().getMapById(Integer.parseInt(args[1]));
                        if (map != null) {
                            map.setWaitingLocation(player.getLocation());
                            commandSender.sendMessage(ChatColor.GREEN + "Waiting location of map " + map.getId() + " has been set.");
                        } else commandSender.sendMessage(ChatColor.RED + "Map with id " + args[1] + " wasn't found.");
                    } else commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
                } else commandSender.sendMessage(ChatColor.RED + "Usage: /map waiting [id]");
            } else if (args[0].equalsIgnoreCase("location")) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    if (args.length > 3 && Integer.parseInt(args[3]) < 3 && Integer.parseInt(args[3]) > 0) {
                        Map map = Rise.getInstance().getMapManager().getMapById(Integer.parseInt(args[1]));
                        if (map != null) {
                            if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) <= map.getArenas().size()) {
                                Arena arena = map.getArenas().get(Integer.parseInt(args[2]) - 1);
                                if (arena != null) {
                                    arena.setSpawnLocation(Integer.parseInt(args[3]), player.getLocation());
                                    commandSender.sendMessage(ChatColor.GREEN + "Location " + args[3] + " of arena " + arena.getId() + " has been set.");
                                } else
                                    commandSender.sendMessage(ChatColor.RED + "Arena with id " + args[2] + " wasn't found.");
                            } else
                                commandSender.sendMessage(ChatColor.RED + "Arena with id " + args[2] + " wasn't found.");
                        } else commandSender.sendMessage(ChatColor.RED + "Map with id " + args[1] + " wasn't found.");
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "Usage: /map location [id] [arena] [1-2]");
                        commandSender.sendMessage(ChatColor.YELLOW + "1-4" + ChatColor.GRAY + ": Round Arena's");
                        commandSender.sendMessage(ChatColor.YELLOW + "5-6" + ChatColor.GRAY + ": Semi Arena's");
                        commandSender.sendMessage(ChatColor.YELLOW + "7" + ChatColor.GRAY + ": Final Arena");
                    }
                } else commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            } else if (args[0].equalsIgnoreCase("stairs")) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    if (args.length > 1) {
                        Map map = Rise.getInstance().getMapManager().getMapById(Integer.parseInt(args[1]));
                        if (map != null) {
                            if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) <= map.getArenas().size()) {
                                Arena arena = map.getArenas().get(Integer.parseInt(args[2]) - 1);
                                if (arena != null) {
                                    MapCreator mapCreator = Rise.getInstance().getMapManager().getMapCreatorByPlayer(player);
                                    if (mapCreator.getLeftClicked() != null && mapCreator.getRightClicked() != null) {
                                        arena.setStair(BlocksUtil.blocksFromTwoPoints(mapCreator.getLeftClicked(), mapCreator.getRightClicked()));
                                        commandSender.sendMessage(ChatColor.GREEN + "Stairs for arena " + args[2] + " has been set.");
                                    } else
                                        commandSender.sendMessage(ChatColor.RED + "You didn't select 2 blocks. Use '/map wand'.");
                                } else
                                    commandSender.sendMessage(ChatColor.RED + "Arena with id " + args[2] + " wasn't found.");
                            } else
                                commandSender.sendMessage(ChatColor.RED + "Arena with id " + args[2] + " wasn't found.");
                        } else commandSender.sendMessage(ChatColor.RED + "Map with id " + args[1] + " wasn't found.");
                    } else commandSender.sendMessage(ChatColor.RED + "Usage: /map stairs [id] [arena]");
                } else commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            } else if (args[0].equalsIgnoreCase("door")) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    if (args.length > 2) {
                        Map map = Rise.getInstance().getMapManager().getMapById(Integer.parseInt(args[1]));
                        if (map != null) {
                            if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) <= map.getArenas().size()) {
                                Arena arena = map.getArenas().get(Integer.parseInt(args[2]) - 1);
                                if (arena != null) {
                                    MapCreator mapCreator = Rise.getInstance().getMapManager().getMapCreatorByPlayer(player);
                                    if (mapCreator.getLeftClicked() != null && mapCreator.getRightClicked() != null) {
                                        arena.setDoor(BlocksUtil.blocksFromTwoPoints(mapCreator.getLeftClicked(), mapCreator.getRightClicked()));
                                        commandSender.sendMessage(ChatColor.GREEN + "Doors for arena " + args[2] + " has been set.");
                                    } else
                                        commandSender.sendMessage(ChatColor.RED + "You didn't select 2 blocks. Use '/map wand'.");
                                } else
                                    commandSender.sendMessage(ChatColor.RED + "Arena with id " + args[2] + " wasn't found.");
                            } else
                                commandSender.sendMessage(ChatColor.RED + "Arena with id " + args[2] + " wasn't found.");
                        } else commandSender.sendMessage(ChatColor.RED + "Map with id " + args[1] + " wasn't found.");
                    } else commandSender.sendMessage(ChatColor.RED + "Usage: /map door [id] [arena]");
                } else commandSender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            } else commandSender.sendMessage(ChatColor.RED + "Unknown argument.");
        } else commandSender.sendMessage(ChatColor.RED + "Unknown argument.");
        return true;
    }
}
