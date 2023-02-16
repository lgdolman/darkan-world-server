// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Copyright (C) 2021 Trenton Kress
//  This file is part of project: Darkan
//
package com.rs.game.content.quests.ghostsahoy.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.engine.dialogue.Conversation;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.engine.dialogue.Options;
import com.rs.game.engine.quest.Quest;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.NPCClickHandler;

@PluginEventHandler
public class NecrovarusD extends Conversation {
    private static int npcId = 1684;

    public static NPCClickHandler NecrovarusD = new NPCClickHandler(new Object[] { npcId }, e -> {
        //Handle Right-Click
            switch (e.getOption()) {
                //Start Conversation
                case "Talk-To" -> e.getPlayer().startConversation(new NecrovarusD(e.getPlayer()));
            }
        });

    public static boolean ghostEquipped(Player player) {
        int neckId = player.getEquipment().getNeckId();
        if (neckId == -1)
            return false;
        else {
            return ItemDefinitions.getDefs(neckId).getName().contains("Ghostspeak");
        }
    }

    public NecrovarusD(Player player) {
        super(player);
        if(!ghostEquipped(player))
        {
            //Handle no ghostspeak
            addNPC(npcId, HeadE.FRUSTRATED, "Woooo wooo wooooo woooo");
            create();
            player.sendMessage("You cannot understand the ghost.");
            return;
        }
        if (player.getQuestManager().notStarted(Quest.GHOSTS_AHOY)) {
            String[] preGA = new String[]{
                    "I do not answer questions, mortal fool !!",
                    "I will answer questions when you are dead !!",
                    "Nobody speaks to me unless I speak to them !!!",
                    "Speak to me again and I will rend the soul from your flesh.",
                    "You dare to speak to me???Have you lost your wits ????",
            };
            addOptions("Select an option.", new Options() {
                @Override
                public void create() {
                    option("What is this place?", new Dialogue()
                            .addPlayer(HeadE.CONFUSED, "What is this place?")
                            .addNPC(npcId, HeadE.ANGRY, preGA[(Utils.random(5))])
                    );
                    option("What happened to everyone here?", new Dialogue()
                            .addPlayer(HeadE.CONFUSED, "What happened to everyone here?")
                            .addNPC(npcId, HeadE.ANGRY, preGA[(Utils.random(5))])
                    );
                    option("How do I get into the town?", new Dialogue()
                            .addPlayer(HeadE.CONFUSED, "How do I get into the town?")
                            .addNPC(npcId, HeadE.ANGRY, preGA[(Utils.random(5))])
                    );
                }

            });
        }
        /*
        if(player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 1) {
            addPlayer(HeadE.CALM_TALK, "I must speak with you on behalf of Velorina.");
            addNPC(npcId, HeadE.ANGRY, "You dare to speak that name in this place?????");
            addPlayer(HeadE.CALM_TALK, "She wants to pass-");
            addNPC(npcId, HeadE.ANGRY, "Silence! Or I will incinerate the flesh from your bones!!");
            addPlayer(HeadE.CALM_TALK, "But she-");
            addNPC(npcId, HeadE.ANGRY, "Get out of my sight!! Or I promise you that you will regret your insolence for the rest of eternity!!");
            player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("NecrovarusS1");
            player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 2);
        }
        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 2) {
            //Speaking to Necrovarus again
            addPlayer(HeadE.CALM_TALK, "Please, listen to me-");
            addNPC(npcId, HeadE.ANGRY, "No – listen to me. Go from this place and do not return, or I will remove your head.;");
        }
         */

    }
}


/*
Pleading for Phasmatys
Player: I must speak with you on behalf of Velorina.
Necrovarus: You dare to speak that name in this place?????
Player: She wants to pass-
Necrovarus: Silence! Or I will incinerate the flesh from your bones!!
Player: But she-
Necrovarus: Get out of my sight!! Or I promise you that you will regret your insolence for the rest of eternity!!
Speaking to Necrovarus again
Player: Please, listen to me-
Necrovarus: No – listen to me. Go from this place and do not return, or I will remove your head.
 */

    /*
The Wheels are In Motion [Misc dialogue]
        Talking to Necrovarus
        Player: Wheels have been set in motion, Necrovarus, wheels that will set the citizens of Port Phasmatys free.
        Necrovarus: Oh goody goody. I just can't wait.

     */

/*
Aggressive Negotiations
Player: Necrovarus, I am presenting you with a petition form that has been signed by 10 citizens of Port Phasmatys.
Necrovarus: A petition you say? Continue, mortal.
Player: It says the citizens of Port Phasmatys should have the right to choose whether they pass over into the next world or not, and not have this decided by the powers that be on their behalf.
Necrovarus: I see.
Player: So you will let them pass over if the wish?
Necrovarus: Oh yes.
Player: Really?
Necrovarus: NO!!!! Get out of my sight before I burn every ounce of flesh from your bones!!!!!
The petition form turns to ashes in your hand. In his rage, Necrovarus drops a key on the floor.
Speaking to Necrovarus again
Player: It matters not that you ignore your citizens' wishes, Necrovarus. Wheels have been set in motion, wheels that will set them free.
Necrovarus: I have almost completely lost patience with you, mortal. Another word, and every threat I have uttered will be made real for you.
 */

/*
The Last Command
Necrovarus: You dare to face me again – you must be truly insane!!!
Player: No, Necrovarus, I am not insane. With this enchanted amulet of ghostspeak I have the power to command you to do my will!
What do you want to command Necrovarus to do?
Let any ghost who so wishes pass on into the next world.
Player: Let any ghost who so wishes pass on into the next world.
A beam of intense light radiates out from the amulet of ghostspeak.
Necrovarus: I – will – let ...
Player: Carry on...
Necrovarus: ...any...
Player: Yes?
Necrovarus: ... ghost who so wishes ...
Player: I think we're almost getting there...
Necrovarus: ... pass into the next world.
Tell me a joke.
Player: Tell me a joke.
A beam of intense light radiates out from the amulet of ghostspeak.
Necrovarus: Knock knock.
Player: Who's there?
Necrovarus: Egbert.
Player: Egbert who?
Necrovarus: Egbert no bacon.
Luckily the amulet of ghostspeak does not seem to have fully discharged.
Do a chicken impression.
Player: Do a chicken impression.
A beam of intense light radiates out from the amulet of ghostspeak.
Necrovarus: Cluck cluck squuuaaaakkkk cluck cluck. I think I've laid an egg...
Luckily the amulet of ghostspeak does not seem to have fully discharged.
Speaking to Necrovarus again after commanding him to let the ghosts pass on freely
Player: Told you I'd defeat you, Necrovarus. My advice to you is to pass over to the next world yourself with everybody else.
Necrovarus: I should fry you for what you have done...
Player: Quiet, evil priest!! If you try anything I will command you again, but this time it will be to throw yourself into the Endless Void for the rest of eternity.
Necrovarus: Please no! I will do whatever you say!!
 */