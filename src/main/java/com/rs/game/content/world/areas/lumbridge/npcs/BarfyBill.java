package com.rs.game.content.world.areas.lumbridge.npcs;

import com.rs.game.engine.dialogue.Conversation;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.engine.dialogue.Options;
import com.rs.game.model.entity.player.Player;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

@PluginEventHandler
public class BarfyBill extends Conversation {

    //Identify NPC by ID
    private static int npcId = 3331;
    public static NPCClickHandler BarfyBill = new NPCClickHandler(new Object[]{npcId}) {
        @Override
        //Handle Right-Click
        public void handle(NPCClickEvent e) {
            switch (e.getOption()) {
                //Start Conversation
                case "Talk-To" -> e.getPlayer().startConversation(new BarfyBill(e.getPlayer()));
            }
        }
    };


    public BarfyBill(Player player) {
        super(player);
        //Identify NPC by ID
        addPlayer(HeadE.CALM_TALK, "Hello There");
        addNPC(npcId,HeadE.CHEERFUL,"Oh! Hello there.");
        addOptions(new Options() {
            @Override
            public void create() {
                //Give player options
                addOptions(new Options() {
                    @Override
                    public void create() {
                        //Simple Reply
                        option("Who are you?", new Dialogue()
                                .addPlayer(HeadE.CALM_TALK, "Who are you?")
                                .addNPC(npcId, HeadE.CALM_TALK, "My name is Ex Sea Captain Barfy Bill.")
                                .addPlayer(HeadE.CONFUSED, "Ex sea captain?")
                                .addNPC(npcId, HeadE.AMAZED_MILD, " Yeah, I bought a lovely ship and was planning to make a fortune running her as a merchant vessel.")
                                .addPlayer(HeadE.CONFUSED," Yeah, I bought a lovely ship and was planning to make a fortune running her as a merchant vessel.")
                                .addNPC(npcId,HeadE.SHAKING_HEAD,"Chronic sea sickness. My first, and only, voyage was spent dry heaving over the rails.")
                                .addNPC(npcId,HeadE.ROLL_EYES, "If I had known about the sea sickness I could have saved myself a lot of money.")
                                .addPlayer(HeadE.CALM_TALK, "What are you up to now then?")
                                .addNPC(npcId,HeadE.NERVOUS,"Well my ship had a little fire related problem. Fortunately it was well insured.")
                                .addNPC(npcId,HeadE.HAPPY_TALKING," Anyway, I don't have to work anymore so I've taken to canoeing on the river.")
                        );

                        //Conversation
                        //TODO skill based responses
                        option("Can you teach me about Canoeing", new Dialogue()
                                .addNPC(npcId, HeadE.CALM_TALK, "I'm feeling a little ill right now.")
                                .addNPC(npcId, HeadE.HAPPY_TALKING, "Come back soon and I'll show you how to make a canoe.")

                        );

                        option("See you later!", new Dialogue()
                                .addPlayer(HeadE.CALM_TALK, "See you later.")
                                .addNPC(npcId, HeadE.CALM_TALK, "Bye.")
                        );


                    }
                });
            }
        });
    }
}
