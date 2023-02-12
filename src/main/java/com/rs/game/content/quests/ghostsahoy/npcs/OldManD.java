package com.rs.game.content.quests.ghostsahoy.npcs;

import com.rs.game.engine.dialogue.Conversation;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.engine.dialogue.Options;
import com.rs.game.engine.quest.Quest;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.ItemClickHandler;
import com.rs.plugin.handlers.NPCClickHandler;

@PluginEventHandler
public class OldManD extends Conversation {
    private static int npcId = 1696;

    public static NPCClickHandler OldManD = new NPCClickHandler(new Object[] { npcId }, e -> {
        if (e.getOption().equalsIgnoreCase("talk-to")) {
            e.getPlayer().startConversation(new OldManD(e.getPlayer()));
        }
    });

    public static ItemClickHandler handleShipRepair = new ItemClickHandler(new Object[]{4253}, e -> {
        if (e.getOption().equalsIgnoreCase("Repair"))
        {

        }
    });

    public OldManD(Player player) {
        super(player);
        if (player.getQuestManager().notStarted(Quest.GHOSTS_AHOY) && player.getQuestManager().notStarted(Quest.ANIMAL_MAGNETISM)) {
            String[] preQuest = new String[]{
                    "The Captain is waiting on the winds to change.",
                    "We are just waiting for the right time to set sail.",
                    "I do as the Captain tells me.",
            };

            addPlayer(HeadE.CONFUSED, "Why are you still on this shipwreck?");
            addNPC(npcId, HeadE.HAPPY_TALKING, preQuest[(Utils.random(1, 4))]);
            create();
            return;

        }
        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 6) {
            addOptions("I'm here about Necrovarus.", new Options() {
                @Override
                public void create() {
                    option("I'm here about Necrovarus.", new Dialogue()
                            .addPlayer(HeadE.CALM, "I'm here about Necrovarus.")
                            .addNPC(npcId, HeadE.SAD, " I don't remember; I am so old and my memory goes back only so far...")
                            .addPlayer(HeadE.CALM, "Is there anything that can help to refresh your memory?")
                            .addNPC(npcId, HeadE.HAPPY_TALKING, "Yes, I would love a nice hot cup of nettle tea.")
                            .addNext(() -> {
                            })
                    );
                }
            });
        }
    }

}

/*
Shipwrecked Surprise
Player: What is your name?
Old man: I don't remember. Everyone around here just calls me 'boy'.
Player: You're the cabin boy?!?
Old man: Yes, and proud of it.
Player: Can I have the key to the chest?
Old man: Hang on, let me ask the Captain...
The old man cocks an ear towards the Pirate Captain's skeleton.
Old man: The Captain says no.
Player: Is this your toy boat?
The old man inspects the toy boat.
If there is no flag
Old man: No – I made a toy boat a long while ago, but that one had a flag.
If the flag is the wrong colours
Old man: No – I made a toy boat a long while ago, but the colours on the flag were different.
If the flag is the correct colours
Old man: My word – so it is! I never thought I would see it again! Where did you get it from?
Player: Your mother gave it to me to pass on to you.
Old man: My mother? She still lives?
Player: Yes, in a shack to the west of here.
Old man: After all these years...
Player: Can I have the key to the chest, then?
Old man: Hang on, let me ask the Captain...
The old man cocks an ear towards the Pirate Captain's skeleton.
Old man: The Captain says yes.
The old man gives you the chest key.
Talking to the old man after getting the chest key
Player: How is it going?
Old man: Wonderful, wonderful! Mother's coming to get me!
 */
