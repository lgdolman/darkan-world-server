package com.rs.game.content.quests.ghostsahoy;

import com.rs.game.engine.quest.Quest;
import com.rs.lib.game.Item;
import com.rs.plugin.handlers.ItemClickHandler;
import com.rs.plugin.handlers.ItemOnItemHandler;

public class ItemHandlers {
    public static ItemClickHandler handlePetition = new ItemClickHandler(new Object[]{4283}, e -> {
        if (e.getOption().equalsIgnoreCase("count"))
            e.getPlayer().sendMessage("You have obtained " + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") + " of 10 signatures so far.");
        if (e.getOption().equalsIgnoreCase("drop")) {
            if (e.getPlayer().getQuestManager().isComplete(Quest.GHOSTS_AHOY)) {
                e.getPlayer().getInventory().deleteItem(4283, 1);
            } else {
                e.getPlayer().sendOptionDialogue("Drop it? It will be destroyed and you will have to get new signatures.", ops -> {
                    ops.add("Yes, drop it.", (() -> {
                        e.getPlayer().getInventory().deleteItem(4283, 1);
                        e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("signatures", 0);
                    }));
                    ops.add("Nevermind.");
                });
            }

        }
    });
    public static ItemClickHandler handleKeyOnComplete = new ItemClickHandler(new Object[]{4274}, e -> {
        if (e.getOption().equalsIgnoreCase("take")) {
            if (e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F1") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") <= 3) {
                e.getPlayer().getInventory().deleteItem(4273, 1);
                e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F3" ,1 );
                e.getPlayer().sendMessage("The key crumbles in your bag.");
            }
        }
    });

    public static ItemClickHandler handleTreasureMap = new ItemClickHandler(new Object[]{4277}, e -> {
        if (e.getOption().equalsIgnoreCase("read")) {
            e.getPlayer().getInterfaceManager().sendInterface(8);
        }
        if (e.getOption().equalsIgnoreCase("follow")) {
            e.getPlayer().sendMessage("Not implemented."); //TODO Map follow
        }
    });

    public static ItemOnItemHandler handleMap = new ItemOnItemHandler(new int[]{4274, 4274}, e -> {
        if (e.getPlayer().getInventory().containsItem(4274, 3)) {
            e.getPlayer().getInventory().removeItems(new Item(4274, 3));
            e.getPlayer().getInventory().addItem(new Item(4277, 1), true);
        }
    });

    public static ItemClickHandler handleShipRepair = new ItemClickHandler(new Object[]{4253}, e -> {
        if (e.getOption().equalsIgnoreCase("Repair"))
        {

        }
    });
}
