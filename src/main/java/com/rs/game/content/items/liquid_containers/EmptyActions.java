package com.rs.game.content.items.liquid_containers;

import com.rs.game.World;
import com.rs.lib.game.WorldTile;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.ItemClickHandler;
@PluginEventHandler

public class EmptyActions {

    public static ItemClickHandler handleWaterBowl = new ItemClickHandler(new Object[] {1921}, e -> {
        if (e.getOption().equalsIgnoreCase("Empty"))
        {
            e.getPlayer().getInventory().removeItems(e.getItem());
            e.getPlayer().getInventory().addItem(1923,1);
            return;
        }
        if (e.getOption().equalsIgnoreCase("drop")) {
            e.getPlayer().getInventory().removeItems(e.getItem());
            World.addGroundItem(e.getItem(), WorldTile.of(e.getPlayer().getTile()), e.getPlayer());
        }
    });

    public static ItemClickHandler handleMilkBucket = new ItemClickHandler(new Object[] {1927}, e -> {
        if (e.getOption().equalsIgnoreCase("Empty"))
        {
            e.getPlayer().getInventory().removeItems(e.getItem());
            e.getPlayer().getInventory().addItem(1925,1);
            return;
        }
        if (e.getOption().equalsIgnoreCase("drop")) {
            e.getPlayer().getInventory().removeItems(e.getItem());
            World.addGroundItem(e.getItem(), WorldTile.of(e.getPlayer().getTile()), e.getPlayer());
        }
    });
}
