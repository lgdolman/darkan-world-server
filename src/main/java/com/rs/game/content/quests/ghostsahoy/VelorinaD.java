package com.rs.game.content.quests.ghostsahoy;

import com.rs.cache.loaders.ItemDefinitions;
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

    public boolean GhostEquipped() {
        int neckId = player.getEquipment().getNeckId();
        if (neckId == -1)
            return false;
        return ItemDefinitions.getDefs(neckId).getName().contains("Ghostspeak");
    }

    public VelorinaD(Player player) {
        super(player);
        int neckId = player.getEquipment().getNeckId();
        if (GhostEquipped()) {
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
                    }
                    else{
                        addNPC(npcId, HeadE.SAD_CRYING, "Take pity on me, please – eternity stretches out before me and I am helpless in its grasp.");
                    }
                }
            });
        }
        else {
            addNPC(npcId, HeadE.CALM_TALK, "Woooowoooooo woooooo!");
            addSimple("You cannot understand a word the ghost is saying.");
        }
    }
}

/*
Stage 1
Velorina: Take pity on me, please – eternity stretches out before me and I am helpless in its grasp.
Player: Why, what is the matter?
Velorina: Oh, I'm sorry – I was just wailing out loud. I didn't mean to scare you.
Player: No, that's okay – it takes more than a ghost to scare me. What is wrong?
Velorina: Do you know the history of our town?
Player: Yes, I do. It is a very sad story.
[Continues below]
Player: No – could you tell me?
Velorina: Do you know why ghosts exist?
Player: A ghost is a soul left in limbo, unable to pass over to the next world; they might have something left to do in this world that torments them, or they might just have died in a state of confusion.
Velorina: Yes, that is normally the case. But here in Port Phasmatys, we of this town once chose freely to become ghosts!
Player: Why on earth would you do such a thing?
Velorina: It is a long story. Many years ago, this was a thriving port, a trading centre to the eastern lands of RuneScape. We became rich on the profits made from the traders that came across the eastern seas. We were very happy... until Lord Drakan noticed us. He sent unholy creatures to demand that a blood-tithe be paid to the Lord Vampyre, as is required from all in the domain of Morytania. We had no choice but to agree to his demands. As the years went by, our numbers dwindled and many spoke of abandoning the town for safer lands. Then, Necrovarus came to us. He came from the eastern lands, but of more than that, little is known. Some say he was once a mage, some say a priest. Either way, he was in possession of knowledge totally unknown to even the most learned among us. Necrovarus told us that he had been brought by a vision he'd had of an underground source of power. He inspired us to delve beneath the town, promising us the power to repel the vampyres. Deep underneath Phasmatys, we found a pool of green slime that Necrovarus called ectoplasm. He showed us how to build the Ectofuntus, which would turn the ectoplasm into the power he had promised us. Indeed, this Ectopower did repel the vampyres; they would not enter Phasmatys once the Ectofuntus began working. But little did we know that we had exchanged one evil for yet another – Ectopower. Little by little, we began to lose any desire for food or water, and our desire for the Ectopower grew until it dominated our thoughts entirely. Our bodies shrivelled and, one by one, we died. The Ectofuntus and the power it emanates keeps us here as ghosts – some, like myself, content to remain in this world, some becoming tortured souls who we do not allow to pass our gates. We would be able to pass over into the next world but Necrovarus has used his power to create a psychic barrier, preventing us. We must remain here for all eternity, even unto the very end of the world.
Player: That's a very sad story.
Velorina: Would you help us obtain our release into the next world?
Player: Yes, of course I will. Tell me what you want me to do.
Velorina: Oh, thank you! Necrovarus will not listen to those of us who are already dead. He might rethink his position if someone with a mortal soul pleaded our cause. If he declines, there may yet be another way.
Player: No.
Player: Sorry, I'm scared of ghosts.
Speaking to Velorina again
Velorina: I sense that you have not yet spoken to Necrovarus.
Player: No, I was just getting to that.
Velorina: Well, I suppose we do have all the time in the world.
 */

/*
Stage 2
Plan B
Player: I'm sorry, but Necrovarus will not let you go.
Velorina: I feared as much. His spirit is a thing of fire and wrath.
Player: You spoke of another way.
Velorina: It is only a small chance. During the building of the Ectofuntus one of Necrovarus's disciples spoke out against him. It is such a long time ago I cannot remember her name, although I knew her as a friend. She fled before the Ectofuntus took control over us, but being a disciple of Necrovarus she would have been privy to many of his darkest secrets. She may know of a way to aid us without Necrovarus.
Player: Do you know where this woman can be found?
Velorina: I have a vision of a small wooden shack, the land it was built on the unholy soil of Morytania. I sense the sea is very close, and that there looms castles to the west and the east.
Player: If it was such a long time ago, won't she be dead already?
Velorina: She was a friend of mine. Had she died, I would have felt her spirit pass over to the next world, though I may not follow.
 */

/*
Player: Do you know where I can find the Book of Haricanto?
Velorina: Nobody knows what has happened to the Book. It was stolen when our port was raided by pirates many years ago, and never seen since.
Player: Do you know where I can find the Robes of Necrovarus?
Velorina: I imagine they are still worn by his mortal body, which now lies in a coffin inside the Temple.
Player: I need something to translate the Book of Haricanto.
Velorina: I don't really know. You could try asking some of the traders from the East – they might be able to help you.
 */

/*
Speaking to Velorina afterwards
Velorina: How is it going?
Player: I have had the Amulet of Ghostspeak enchanted, which I shall use to command Necrovarus to set you free.
Velorina: Oh, kind [_/lady] – you are the answer to all our prayers!
 */

/*
Freedom
Velorina: You don't need to tell me [Player] – I sensed the removal of Necrovarus's psychic barrier!
Player: Only happy to help out.
Velorina: Here, take this as a thank you for the service that you have given us.
Velorina gives you a vial of bright green ectoplasm.
Velorina: This is an Ectophial. If you ever want to come back to Port Phasmatys, empty this on the floor beneath your feet, and you will be instantly teleported to the temple – the source of its power. Remember that once the Ectophial has been used you need to refill it from the Ectofuntus.
*/