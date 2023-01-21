package com.rs.game.content.quests.ghostsahoy.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.content.world.areas.port_phasmatys.PortPhasmatys;
import com.rs.game.engine.dialogue.Conversation;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.engine.dialogue.Options;
import com.rs.game.engine.quest.Quest;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.NPCClickHandler;
import jdk.jshell.execution.Util;

@PluginEventHandler
public class OldCroneD extends Conversation {
    private static final int npcId = 1695;

    public static NPCClickHandler OldCroneD = new NPCClickHandler(new Object[] { npcId }, e -> {
            if (e.getOption().equalsIgnoreCase("talk-to")) {
                e.getPlayer().startConversation(new OldCroneD(e.getPlayer()));
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

    public OldCroneD(Player player) {
        super(player);
        if (player.getQuestManager().notStarted(Quest.GHOSTS_AHOY) && player.getQuestManager().notStarted(Quest.ANIMAL_MAGNETISM)) {
            String[] preQuest = new String[]{
                    "I lived here when this was all just fields, you know.",
                    "When 100 years old you reach, look like a prune you will.",
                    "I'm over 100 years old, you know.",
            };

            addPlayer(HeadE.CONFUSED, "What is this place?");
            addNPC(npcId, HeadE.LAUGH, preQuest[(Utils.random(3))]);
            create();
            return;

        }
        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 3) {
            addOptions("I'm here about Necrovarus.", new Options() {
                @Override
                public void create() {
                    option("I'm here about Necrovarus.", new Dialogue()
                            .addPlayer(HeadE.CALM, "I'm here about Necrovarus.")
                            .addNPC(npcId, HeadE.SAD, " I don't remember; I am so old and my memory goes back only so far...")
                            .addPlayer(HeadE.CALM, "Is there anything that can help to refresh your memory?")
                            .addNPC(npcId, HeadE.HAPPY_TALKING, "Yes, I would love a nice hot cup of nettle tea.")
                            .addNext(() -> {

                                if (!player.getInventory().containsItem(4239) && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTea") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaP") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaM") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaCompleted")) {
                                    addPlayer(HeadE.CONFUSED, "Do you know where I can find nettles around here?")
                                            .addNPC(npcId, HeadE.CALM_TALK, "I believe they grow wild in the Haunted Forest.");

                                } else if (player.getInventory().containsItem(4239) && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTea") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaP") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaM") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaCompleted")) {
                                    addPlayer(HeadE.CALM, "Here's some tea for you, like you asked.")
                                            .addNPC(npcId, HeadE.SHAKING_HEAD, "Yes, but it's not in my special cup! I only ever drink from my special cup!!")
                                            .addPlayer(HeadE.SKEPTICAL, "I see. Can I have this special cup, then?")
                                            .addNPC(npcId, HeadE.HAPPY_TALKING, "It's here somewhere..")
                                            .addNext(() -> {
                                                if (player.getInventory().hasFreeSlots()) {
                                                    player.getInventory().addItem(4244);
                                                    player.sendMessage("The old crone gives you her special cup.");
                                                    player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setB("OldCrowNettleTea", true);
                                                } else {
                                                    addPlayer(HeadE.SHAKING_HEAD, "Actually, I don't have room for it.");
                                                }
                                            });
                                } else if (player.getInventory().containsItem(4245) && player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTea") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaP") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaM") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaCompleted")) {
                                    addPlayer(HeadE.CALM, "Here's a lovely cup of tea for you, in your own special cup.")
                                            .addNPC(npcId, HeadE.SHAKING_HEAD, "Oh no, it hasn't got milk in it. I only drink tea with milk, I'm afraid.")
                                            .addNext(() -> player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setB("OldCrowNettleTeaP", true));

                                } else if (player.getInventory().containsItem(4246) && player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTea") && player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaP") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaM") && !player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getB("OldCrowNettleTeaCompleted")) {
                                    addPlayer(HeadE.CALM, "Here's a lovely cup of milky tea for you, in your own special cup.")
                                            .addNext(() -> {
                                                player.getInventory().deleteItem(4246, 1);
                                                player.sendMessage("As the old woman drinks the cup of milky tea, enlightenment glows from within her eyes.");
                                                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 4);
                                                player.startConversation(new OldCroneD(player));
                                            });
                                }
                            })
                    );
                    option("I'm here about the farmers east of here.", new Dialogue()
                            .addPlayer(HeadE.SHAKING_HEAD, "I'm here about the farmers east of here. ")
                            .addNPC(npcId, HeadE.SHAKING_HEAD, "What farmers?") //TODO
                    );
                }
            });
            return;
        }
        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 4) {
            addNPC(npcId, HeadE.HAPPY_TALKING, "Ah, that's better. Now, let me see... Yes, I was once a disciple of Necrovarus.");
            addPlayer(HeadE.CALM_TALK, "I have come from Velorina. She needs your help.");
            addNPC(npcId, HeadE.CONFUSED, "Velorina? That name sounds familiar... Yes, Velorina was once a very good friend! It has been many years since we spoke last. How is she?");
            addPlayer(HeadE.CALM_TALK, "She's a ghost, I'm afraid.");
            addNPC(npcId, HeadE.CALM_TALK, "They are all dead, then. Even Velorina. I should have done something to stop what was happening, instead of running away.");
            addPlayer(HeadE.CALM_TALK, "She and many others want to pass on into the next world, but Necrovarus will not let them. Do you know of any way to help them?");
            addNPC(npcId, HeadE.SKEPTICAL, "There might be one way...");
            addNPC(npcId, HeadE.CALM_TALK, "Do you have such a thing as a ghostspeak amulet?");

            if (!ghostEquipped(player) && !player.getInventory().containsItem(4250)) {
                //(If the player doesn't have a ghostspeak amulet:)
                addPlayer(HeadE.CALM_TALK, "No I don't, I'm afraid.");
                addNPC(npcId, HeadE.CALM_TALK, "Well, you'll need to find one.");
            } else if (!ghostEquipped(player)  && player.getInventory().containsItem(4250)) {
                //(If the player has a ghostspeak amulet in the backpack:)
                addPlayer(HeadE.CALM_TALK, "Yes, I'm carrying one in my bag.");
                addNPC(npcId, HeadE.CALM_TALK, "Well, that's a stroke of luck.");
                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 5);
                player.startConversation(new OldCroneD(player));
            } else if (ghostEquipped(player) && !player.getInventory().containsItem(4250)) {
                //(If the player is wearing a ghostspeak amulet:)
                addPlayer(HeadE.CALM_TALK, "Yes, I'm wearing one right now.");
                addNPC(npcId, HeadE.CALM_TALK, "Well, that's a stroke of luck.");
                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 5);
                player.startConversation(new OldCroneD(player));
            }
            create();
            return;
        }
        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 5) {
            addNPC(npcId, HeadE.CALM_TALK, "There is an enchantment that I can perform on such an amulet that will give it the power of command over ghosts. It will work only once, but it will enable you to command Necrovarus to let the ghosts pass on.");
            addPlayer(HeadE.CALM_TALK, "What do you need to perform the enchantment?");
            addNPC(npcId, HeadE.CALM_TALK, "Necrovarus had a magical robe for which he must have no more use. Only these robes hold the power needed to perform the enchantment. All his rituals came from a book written by an ancient sorcerer from the East called Haricanto.");
            addNPC(npcId, HeadE.CALM_TALK, "Bring me this strange book. I cannot read the strange letters of the eastern lands. I will need something to help me translate the book.");
            addOptions("Select an option.", new Options() {
                @Override
                public void create() {
                    option("You are doing so much for me - is there anything I can do for you?", new Dialogue()
                            .addPlayer(HeadE.CALM_TALK, "You are doing so much for me - is there anything I can do for you?")
                            .addNPC(npcId, HeadE.CALM_TALK, "I have lived here on my own for many years, but not always. When I left Port Phasmatys, I took my son with me. He grew up to be a fine boy, always in love with the sea. ")
                            .addNPC(npcId, HeadE.CALM_TALK, "He was about twelve years old when he ran away with some pirates to be a cabin boy. I never saw him again.")
                            .addPlayer(HeadE.CALM_TALK, "That's the second saddest story I have heard today.")
                            .addNPC(npcId, HeadE.CALM_TALK, "If you ever see him, please give him this...and tell him that his mother still loves him.")
                            //Player receives model ship.
                            .addNext(() -> {
                                if (player.getInventory().hasFreeSlots()) {

                                    player.getInventory().addItem(4253);
                                    player.sendMessage("The old woman gives you a toy boat.");
                                } else {
                                    addPlayer(HeadE.SHAKING_HEAD, "Sorry, I don't have the space for that");
                                }
                            })
                            .addPlayer(HeadE.CALM_TALK, "Was this his boat?")
                            .addNPC(npcId, HeadE.CALM_TALK, "Yes, he made it himself. It is a model of the very ship in which he sailed away. The paint has peeled off and it has lost its flag, but I could never throw it away.")
                            .addPlayer(HeadE.CALM_TALK, "If I find him, I will pass it on.")
                    );

                }
            });
            return;
        }
    }
}
                    /*
                    Player:


                    Remind me - what can I do about Necrovarus?
                    Player:
                    Remind me - what can I do about Necrovarus?
                    (Continues above.)
                    What did you want me to get for you?
                            Player:
                            What did you want me to get for you?
                            (Continues above.)
                    I'll go and search for those items you asked for.
                    Player:
                    I'll go and search for those items you need.
                    (If the player has the model ship:)
                    I am afraid I have not found your son yet.
                    Player:
                    I am afraid I have not found your son yet.
                    Old crone:
                    I never expected that you would find him - although if you do, please let me know.
                    (If the player has lost the model ship:)
                    I am afraid I have lost the boat you gave to me.
                    Player:
                    I am afraid I have lost the boat you gave to me.
                    Player receives model ship.
                    Old crone:
                    No, I've found it - here you go.

                     */




    /*
    Custom Order
Player: I'm here about Necrovarus. Were you once a disciple of Necrovarus in the Temple of Phasmatys, old woman?
        Old crone: I don't remember; I am so old and my memory goes back only so far...
        Player: Is there anything that can help to refresh your memory?
        Old crone: Yes, I would love a nice hot cup of nettle tea.
        Player: Do you know where I can find nettles around here?
        Old crone: I believe they grow wild in the Haunted Forest.
        After obtaining some nettle tea
        Player: Here's some tea for you, like you asked.
        Old crone: Yes, but it's not in my special cup! I only ever drink from my special cup!
        Player: I see. Can I have this special cup, then?
        The old crone gives you her special cup.
        After pouring the tea into her special cup
        Player: Here's a lovely cup of tea for you, in your own special cup.
        Old crone: Oh no, it hasn't got milk in it. I only drink tea with milk, I'm afraid.
        After adding milk to the tea
        Player: Here's a lovely cup of milky tea for you, in your own special cup.
        As the old woman drinks the cup of milky tea, enlightenment glows from within her eyes.
        Old crone: Ah, that's better. Now, let me see... Yes, I was once a disciple of Necrovarus.
        Player: I have come from Velorina. She needs your help.
        Old crone: Velorina? That name sounds familiar... Yes, Velorina was once a very good friend! It has been many years since we spoke last. How is she?
        Player: She's a ghost, I'm afraid.
        Old crone: They are all dead, then. Even Velorina. I should have done something to stop what was happening, instead of running away.
        Player: She and many others want to pass on into the next world, but Necrovarus will not let them. Do you know of any way to help them?
        Old crone: There might be one way... Do you have such a thing as a ghostspeak amulet?
        Player: Yes, I'm wearing one right now. [If you're wearing the amulet]
        Old crone: Well, that's a stroke of luck. There is an enchantment that I can perform on such an amulet that will give it the power of command over ghosts. It will work only once, but it will enable you to command Necrovarus to let the ghosts pass on.
        Player: What do you need to perform the enchantment?
        Old crone: Necrovarus had a magical robe for which he must have no more use. Only these robes hold the power needed to perform the enchantment. All his rituals come from a book written by an ancient sorcerer from the East called Haricanto. Bring me this strange book. I cannot read the strange letters of the eastern lands. I will need something to help me translate the book.
        Player: You are doing so much for me – is there anything I can do for you?
        Old crone: I have lived here on my own for many years, but not always. When I left Port Phasmatys, I took my son with me. He grew up to be a fine boy, always in love with the sea. He was about twelve years old when he ran away with some pirates to be a cabin boy. I never saw him again.
        Player: That's the second saddest story I have heard today.
        Old crone: If you ever see him, please give him this...and tell him that his mother still loves him.
        The old woman gives you a toy boat.
        Player: Was this his boat?
        Old crone: Yes, he made it himself. It is a model of the very ship in which he sailed away. The paint has peeled off and it has lost its flag, but I could never throw it away.
        Player: If I find him, I will pass it on.
        Player: Remind me – what can I do about Necrovarus?
        [Repeats conversation about the ghostspeak amulet]
        Player: What did you want me to get for you?
        [Repeats conversation about the items she needs for the enchantment]
        Player: I'll go and search for those items you asked for.
        Speaking to the crone again
        Player: I'm here about Necrovarus
        Player: I am afraid I have not found your son yet. [If she has already given you the toy boat]
        Old crone: I never expected that you would find him – although if you do, please let me know.
        [Same 3 options next as before]
*/
/*
Player: Good news! I have found your son!
        Old crone: Goodness! Where is he?
        Player: He lives on a shipwreck to the east of here. He remembers you and wishes you well.
        Old crone: Oh thank you! I will travel to see him as soon as I am able!!
        */

/*
The Crone-Made Amulet
When handing in items
Player: I'm here about Necrovarus. I have something for you.
Old crone: A translation manual – yes, this should do the job.
Old crone: The Book of Haricanto! I have no idea how you came by this, but well done!
Old crone: Good – the robes of Necrovarus.
After giving the old crone the last item
Old crone: Wonderful; that's everything I need. I will now perform the ritual of enchantment.
The ghostspeak amulet emits a green glow from its gem.
Speaking to the crone again
Player: I'm here about Necrovarus.
Old crone: Did it work?
Player: Actually, I haven't tried it yet.
 */