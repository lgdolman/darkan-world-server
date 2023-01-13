package com.rs.game.content.quests.ghostsahoy.npcs;

import com.rs.game.engine.dialogue.Conversation;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.engine.dialogue.Options;
import com.rs.game.engine.quest.Quest;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

@PluginEventHandler
public class OldCroneD extends Conversation {
    private static int npcId = 9989;

    public static NPCClickHandler OldCroneD = new NPCClickHandler(new Object[]{npcId}) {
        @Override
        //Handle Right-Click
        public void handle(NPCClickEvent e) {
            switch (e.getOption()) {
                //Start Conversation
                case "Talk-To" -> e.getPlayer().startConversation(new OldCroneD(e.getPlayer()));
            }
        }
    };

    public OldCroneD(Player player) {
        super(player);
        if (player.getQuestManager().notStarted(Quest.GHOSTS_AHOY) && player.getQuestManager().notStarted(Quest.ANIMAL_MAGNETISM)) {
            String[] preQuest = new String[]{
                    "I lived here when this was all just fields, you know.",
                    "When 100 years old you reach, look like a prune you will.",
                    "I'm over 100 years old, you know.",
            };

                            addPlayer(HeadE.CONFUSED, "What is this place?");
                            addNPC(npcId, HeadE.ANGRY, preQuest[(Utils.random(4))]);

            }
        if(player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 3) {

            //Nettle Tea
            if(!player.getInventory().containsItem(4239) )
            {

            }
            //Nettle Tea
            if(!player.getInventory().containsItem(4239))
            {

            }









            player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 4);
        }
        if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 2) {
            //Speaking to Necrovarus again
            addPlayer(HeadE.CALM_TALK, "Please, listen to me-");
            addNPC(npcId, HeadE.ANGRY, "No – listen to me. Go from this place and do not return, or I will remove your head.;");
        }

    }
}

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