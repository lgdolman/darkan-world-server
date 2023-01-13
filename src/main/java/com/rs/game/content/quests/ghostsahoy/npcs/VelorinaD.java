package com.rs.game.content.quests.ghostsahoy.npcs;

import com.rs.game.engine.dialogue.Conversation;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.engine.dialogue.Options;
import com.rs.game.engine.quest.Quest;
import com.rs.game.model.entity.player.Player;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

@PluginEventHandler
public class VelorinaD extends Conversation {
    private static int npcId = 1683;

    public static NPCClickHandler VelorinaD = new NPCClickHandler(new Object[]{npcId}) {
        @Override
        //Handle Right-Click
        public void handle(NPCClickEvent e) {
            switch (e.getOption()) {
                //Start Conversation
                case "Talk-To" -> e.getPlayer().startConversation(new VelorinaD(e.getPlayer()));
            }
        }
    };


    public VelorinaD(Player player) {
        super(player);
        if (player.getEquipment().GhostEquipped()) {
            addOptions("What would you like to say?", new Options() {
                @Override
                public void create() {
                    if (player.isQuestComplete(Quest.GHOSTS_AHOY)) {
                        option("I thought you were going to pass over to the next world.", new Dialogue()
                                .addPlayer(HeadE.CONFUSED, "I thought you were going to pass over to the next world.")
                                .addNPC(npcId, HeadE.CALM_TALK, "All in good time, " + player.getDisplayName() + ". We stand forever in your debt, and will certainly put in a good word for you when we pass over."));
                        option("Can I have another ectophial?", new Dialogue()
                                .addPlayer(HeadE.CONFUSED, "Can I have an ectophial?" + (player.getBool("recTokkulZo") ? " I've lost mine." : "")));
                        if (!player.getInventory().containsItem(4251)) {
                            addNPC(npcId, HeadE.CALM_TALK, "Of course you can, you have helped us more than we could ever have hoped.")
                                    .addItem(4251, "Velorina gives you a vial of bright green ectoplasm.", () -> {
                                        if (!player.getInventory().hasFreeSlots()) {
                                            player.sendMessage("You don't have enough inventory space.");
                                            return;
                                        }
                                        player.getInventory().addItem(4251);
                                    });
                        } else {
                            addPlayer(HeadE.CALM, "Can I have another Ectophial?")
                                    .addNPC(npcId, HeadE.CALM_TALK, "Really? You've already got an Ectophial.")
                                    .addPlayer(HeadE.CALM, "I know, but can I have another one anyway?")
                                    .addNPC(npcId, HeadE.CALM_TALK, "Of course you can, you have helped us more than we could ever have hoped.")
                                    .addItem(4251, "Velorina gives you a vial of bright green ectoplasm.", () -> {
                                        if (!player.getInventory().hasFreeSlots()) {
                                            player.sendMessage("You don't have enough inventory space.");
                                            return;
                                        }
                                        player.getInventory().addItem(4251);
                                    });
                        }
                    } else {
                        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 0) {
                            addNPC(npcId, HeadE.SAD_CRYING, "Take pity on me, please – eternity stretches out before me and I am helpless in its grasp.");
                            addPlayer(HeadE.CONFUSED, "Why, what is the matter?");
                            addNPC(npcId, HeadE.CALM, " Oh, I'm sorry – I was just wailing out loud. I didn't mean to scare you.");
                            addPlayer(HeadE.CALM, "No, that's okay – it takes more than a ghost to scare me. What is wrong?");
                            addNPC(npcId, HeadE.CALM, " Do you know the history of our town?");
                            addOptions(new Options() {
                                @Override
                                public void create() {

                                    option("Yes, I do. It is a very sad story.", new Dialogue()
                                            .addPlayer(HeadE.CALM, "Yes, I do. It is a very sad story.")
                                            .addNPC(npcId, HeadE.CALM, " Would you help us obtain our release into the next world?")
                                            .addOptions(new Options() {
                                                @Override
                                                public void create() {
                                                    option("Accept quest", new Dialogue()
                                                            .addPlayer(HeadE.CALM, "Yes, of course I will. Tell me what you want me to do.")
                                                            .addNPC(npcId, HeadE.AMAZED_MILD, "Oh, thank you!")
                                                            .addNPC(npcId, HeadE.AMAZED_MILD, "Necrovarus will not listen to those of us who are already dead.")
                                                            .addNPC(npcId, HeadE.AMAZED_MILD, "He might rethink his position if someone with a mortal soul pleaded our cause.")
                                                            .addNPC(npcId, HeadE.AMAZED_MILD, "If he declines, there may yet be another way.")
                                                            .addNext(() -> {
                                                                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 1);
                                                            }));

                                                    option("Not right now", new Dialogue()
                                                            .addPlayer(HeadE.SHAKING_HEAD, "I'm sorry, but it isn't really my problem.")
                                                            .addNPC(npcId, HeadE.SKEPTICAL, "No, you're right - it's our own fault. ")
                                                            .addNPC(npcId, HeadE.SKEPTICAL, "If you do change your mind though, please come back and help us. We will be here ... forever.")
                                                    );
                                                }
                                            })


                                    );

                                    option("No – could you tell me?", new Dialogue()
                                            .addPlayer(HeadE.CALM, "No – could you tell me?")
                                            .addNPC(npcId, HeadE.CALM, " Do you know why ghosts exist?")
                                            .addPlayer(HeadE.CALM, "A ghost is a soul left in limbo, unable to pass over to the next world;")
                                            .addPlayer(HeadE.CALM, "they might have something left to do in this world that torments them, or they might just have died in a state of confusion.")
                                            .addNPC(npcId, HeadE.CALM, " Yes, that is normally the case. But here in Port Phasmatys, we of this town once chose freely to become ghosts!")
                                            .addPlayer(HeadE.CALM, "Why on earth would you do such a thing?")
                                            .addNPC(npcId, HeadE.CALM, " It is a long story. ")
                                            .addNPC(npcId, HeadE.CALM, "Many years ago, this was a thriving port, a trading centre to the eastern lands of RuneScape. ")
                                            .addNPC(npcId, HeadE.CALM, "We became rich on the profits made from the traders that came across the eastern seas. We were very happy... ")
                                            .addNPC(npcId, HeadE.CALM, "until Lord Drakan noticed us. ")
                                            .addNPC(npcId, HeadE.CALM, "He sent unholy creatures to demand that a blood-tithe be paid to the Lord Vampyre, as is required from all in the domain of Morytania.")
                                            .addNPC(npcId, HeadE.CALM, "We had no choice but to agree to his demands. As the years went by, our numbers dwindled and many spoke of abandoning the town for safer lands.")
                                            .addNPC(npcId, HeadE.CALM, "Then, Necrovarus came to us. He came from the eastern lands, but of more than that, little is known.")
                                            .addNPC(npcId, HeadE.CALM, "Some say he was once a mage, some say a priest. Either way, he was in possession of knowledge totally unknown to even the most learned among us.")
                                            .addNPC(npcId, HeadE.CALM, "Necrovarus told us that he had been brought by a vision he'd had of an underground source of power. ")
                                            .addNPC(npcId, HeadE.CALM, "He inspired us to delve beneath the town, promising us the power to repel the vampyres. ")
                                            .addNPC(npcId, HeadE.CALM, "Deep underneath Phasmatys, we found a pool of green slime that Necrovarus called ectoplasm. ")
                                            .addNPC(npcId, HeadE.CALM, "He showed us how to build the Ectofuntus, which would turn the ectoplasm into the power he had promised us. ")
                                            .addNPC(npcId, HeadE.CALM, "Indeed, this Ectopower did repel the vampyres; they would not enter Phasmatys once the Ectofuntus began working. ")
                                            .addNPC(npcId, HeadE.CALM, "But little did we know that we had exchanged one evil for yet another – Ectopower. ")
                                            .addNPC(npcId, HeadE.CALM, "Little by little, we began to lose any desire for food or water, and our desire for the Ectopower grew until it dominated our thoughts entirely. ")
                                            .addNPC(npcId, HeadE.CALM, "Our bodies shrivelled and, one by one, we died. ")
                                            .addNPC(npcId, HeadE.CALM, "The Ectofuntus and the power it emanates keeps us here as ghosts – some, like myself, content to remain in this world, some becoming tortured souls who we do not allow to pass our gates. ")
                                            .addNPC(npcId, HeadE.CALM, "We would be able to pass over into the next world but Necrovarus has used his power to create a psychic barrier, preventing us. We must remain here for all eternity, even unto the very end of the world.")
                                            .addPlayer(HeadE.CALM, "That's a very sad story.")
                                            .addNPC(npcId, HeadE.CALM, " Would you help us obtain our release into the next world?")
                                            .addOptions(new Options() {
                                                @Override
                                                public void create() {
                                                    option("Accept quest", new Dialogue()
                                                            .addPlayer(HeadE.CALM, "Yes, of course I will. Tell me what you want me to do.")
                                                            .addNPC(npcId, HeadE.HAPPY_TALKING, "Oh, thank you! Necrovarus will not listen to those of us who are already dead.")
                                                            .addNPC(npcId, HeadE.CALM, "He might rethink his position if someone with a mortal soul pleaded our cause.")
                                                            .addNPC(npcId, HeadE.CALM, "If he declines, there may yet be another way.")

                                                            .addNext(() -> {
                                                                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 1, true);
                                                            }));

                                                    option("Sorry, I'm scared of ghosts.", new Dialogue()
                                                            .addPlayer(HeadE.SCARED, "Leave me alone - I'm scared of ghosts!!!")
                                                    );
                                                }
                                            })
                                    );
                                }
                            });
                        }
                    }
                }
            });

            if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 1) {
                addNPC(npcId, HeadE.CALM, " I sense that you have not yet spoken to Necrovarus.");
                addPlayer(HeadE.CALM, "No, I was just getting to that.");
                addNPC(npcId, HeadE.CALM, " Well, I suppose we do have all the time in the world.");
            }
            if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 2) {
                addPlayer(HeadE.CALM, "I'm sorry, but Necrovarus will not let you go.");
                addNPC(npcId, HeadE.SHAKING_HEAD, "I feared as much. His spirit is a thing of fire and wrath.");
                addPlayer(HeadE.CALM, "You spoke of another way.");
                addNPC(npcId, HeadE.CALM, "It is only a small chance.");
                addNPC(npcId, HeadE.CALM, "During the building of the Ectofuntus one of Necrovarus's disciples spoke out against him.");
                addNPC(npcId, HeadE.CALM, "It is such a long time ago I cannot remember her name, although I knew her as a friend.");
                addNPC(npcId, HeadE.CALM, "She fled before the Ectofuntus took control over us, but being a disciple of Necrovarus she would have been privy to many of his darkest secrets.");
                addNPC(npcId, HeadE.CALM, "She may know of a way to aid us without Necrovarus.");
                addPlayer(HeadE.CALM, "Do you know where this woman can be found?");
                addNPC(npcId, HeadE.CALM, " I have a vision of a small wooden shack, the land it was built on the unholy soil of Morytania. ");
                addNPC(npcId, HeadE.CALM, "I sense the sea is very close, and that there looms castles to the west and the east.");
                addPlayer(HeadE.CALM, "If it was such a long time ago, won't she be dead already?");
                addNPC(npcId, HeadE.CALM, "She was a friend of mine. Had she died, I would have felt her spirit pass over to the next world, though I may not follow.");
                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 3, true);
            }
            if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 4) {
                addPlayer(HeadE.CALM, "Do you know where I can find the Book of Haricanto?");
                addNPC(npcId, HeadE.CALM, " Nobody knows what has happened to the Book. It was stolen when our port was raided by pirates many years ago, and never seen since.");
                addPlayer(HeadE.CALM, "Do you know where I can find the Robes of Necrovarus?");
                addNPC(npcId, HeadE.CALM, " I imagine they are still worn by his mortal body, which now lies in a coffin inside the Temple.");
                addPlayer(HeadE.CALM, "I need something to translate the Book of Haricanto.");
                addNPC(npcId, HeadE.CALM, " I don't really know. You could try asking some of the traders from the East – they might be able to help you.");
                player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 5, true);
            }
            if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 5) {
                addNPC(npcId, HeadE.CALM, " How is it going?");
                addPlayer(HeadE.CALM, "I have had the Amulet of Ghostspeak enchanted, which I shall use to command Necrovarus to set you free.");
                addNPC(npcId, HeadE.CALM, " Oh, kind " + player.getAppearance().getTitle() +" – you are the answer to all our prayers!");

            }
            if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 99) {
                addNPC(npcId, HeadE.CALM, " You don't need to tell me " + player.getDisplayName() + " – I sensed the removal of Necrovarus's psychic barrier!");
                addPlayer(HeadE.CALM, "Only happy to help out.");
                addNPC(npcId, HeadE.CALM, " Here, take this as a thank you for the service that you have given us.");
                player.sendMessage("Velorina gives you a vial of bright green ectoplasm.");
                addNPC(npcId, HeadE.CALM, " This is an Ectophial. If you ever want to come back to Port Phasmatys, empty this on the floor beneath your feet, and you will be instantly teleported to the temple – the source of its power. ");
                addNPC(npcId, HeadE.CALM, "Remember that once the Ectophial has been used you need to refill it from the Ectofuntus.");
                player.getQuestManager().completeQuest(Quest.GHOSTS_AHOY);
            }
        }
        else {
                addNPC(npcId, HeadE.CALM_TALK, "Woooowoooooo woooooo!");
                addSimple("You cannot understand a word the ghost is saying.");
            }
        }
    }
