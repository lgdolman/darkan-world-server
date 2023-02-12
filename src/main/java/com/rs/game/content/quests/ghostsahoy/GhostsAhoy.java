package com.rs.game.content.quests.ghostsahoy;

import com.rs.game.engine.quest.Quest;
import com.rs.game.engine.quest.QuestHandler;
import com.rs.game.engine.quest.QuestOutline;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.Constants;
import com.rs.lib.game.Item;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.ItemClickHandler;
import com.rs.plugin.handlers.ItemOnItemHandler;
import com.rs.plugin.handlers.ObjectClickHandler;

import java.util.ArrayList;

@QuestHandler(Quest.GHOSTS_AHOY)
@PluginEventHandler
public class GhostsAhoy extends QuestOutline {

    public int getCompletedStage() {
        return 99;
    }

    @Override
    public ArrayList<String> getJournalLines(Player player, int stage) {
        ArrayList<String> lines = new ArrayList<>();
        switch (stage) {
            case 0:
                lines.add("I can start this quest by speaking to Velorina");
                lines.add("in Port Phasmatys.");
                break;
            case 1:
                lines.add("");
                lines.add("I have spoken with Velorina,");
                lines.add("who has told me the sad history of the ghosts of Port Phasmatys.");
                lines.add("She has asked me to plead with Necrovarus in the Phasmatyan Temple");
                lines.add("to let any ghost who so wishes pass over into the next world.");
                break;
            case 2:
                lines.add("");
                lines.add("I pleaded with Necrovarus, to no avail.");
                break;
            case 3:
                lines.add("");
                lines.add("Velorina was crestfallen at Necrovarus' refusal to lift his ban, ");
                lines.add("and she told me of a woman who fled Port Phasmatys before the townsfolk died,");
                lines.add("and to seek her out, as she may know of a way around Necrovarus' stubbornness.");
                break;
            case 4:
                lines.add("I brought the old woman the Book of Haricanto, the Robes of Necrovarus, and a translation manual.");
                lines.add("");
                break;
            case 5:
                lines.add("The old woman used the items I brought her to perform the enchantment on the Amulet of Ghostspeak.");
                lines.add("");
                break;
            case 6:
                lines.add("I have commanded Necrovarus to remove his ban.");
                lines.add("");
                break;
            case 7:
                lines.add("I have told Velorina that Necrovarus has been commanded to remove his ban,");
                lines.add("and to allow any ghost who so desires to pass over into the next plane of existence.");
                lines.add("Velorina gave me the Ectophial in return, which I can use to teleport to the Temple of Phasmatys.");
                lines.add("");
                break;
            case 8:
                lines.add("");
                lines.add("");
                lines.add("QUEST COMPLETE!");
                break;
            default:
                lines.add("Invalid quest stage. Report this to an administrator.");
                break;
        }
        return lines;
    }

    @Override
    public void complete(Player player) {
        player.getSkills().addXpQuest(Constants.PRAYER, 2400);
        getQuest().sendQuestCompleteInterface(player, 4251, "The Ectophail", "2400 Prayer XP");
    }

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
}





