package me.asterverse.origins.Stuff;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerOriginChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;

    public PlayerOriginChangeEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
