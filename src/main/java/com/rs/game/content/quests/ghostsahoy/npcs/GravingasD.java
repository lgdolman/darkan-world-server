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
import com.rs.game.engine.quest.QuestManager;
import com.rs.game.model.entity.player.Player;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

@PluginEventHandler
public class GravingasD extends Conversation {
	private static int npcId = 6075;


	public static NPCClickHandler GhostVillager = new NPCClickHandler(new Object[]{npcId}) {
		@Override
		//Handle Right-Click
		public void handle(NPCClickEvent e) {
			switch (e.getOption()) {
				//Start Conversation
				case "Talk-To" -> e.getPlayer().startConversation(new GravingasD(e.getPlayer()));
			}
		}
	};

	public GravingasD(Player player) {
		super(player);
		if (player.getEquipment().GhostEquipped())
		{
			if(player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 0) {
				addNPC(npcId, HeadE.FRUSTRATED, "Will you join with me and protest against the evil ban of Necrovarus and his disciples?");
				addPlayer(HeadE.CONFUSED, "I'm sorry, I don't really think I should get involved.");
				addNPC(npcId, HeadE.FRUSTRATED, "Ah, the youth of today - so apathetic to politics.");
				create();
			}
			if(player.isQuestComplete(Quest.GHOSTS_AHOY))
			{
				addPlayer(HeadE.CONFUSED, "Why are you still protesting?");
				addNPC(npcId, HeadE.HAPPY_TALKING, "here's always a need for a healthy interest in politics.");
			}
			if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 1) {
				//Peaceful Protest

				addNPC(npcId, HeadE.HAPPY_TALKING, "Will you join with me and protest against the evil desires of Necrovarus and his disciples?");
				addOptions("Will you help Gravingas?", new Options() {
					@Override
					public void create() {
						option("Yes", new Dialogue()
								.addPlayer(HeadE.CALM, "After hearing Velorina's story I will be happy to help out.")
								.addNPC(npcId, HeadE.HAPPY_TALKING, "Excellent, excellent! Here – take this petition form, and try and get 10 signatures from the townsfolk.")
								.addNext(() -> {
									if(player.getInventory().hasFreeSlots())
									{
										player.getInventory().addItem(4283);
										player.sendMessage("The ghost hands you a petition.");
									}
									else
									{
										addPlayer(HeadE.SHAKING_HEAD,"Sorry, I don't have room for that.");
									}
									player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 2);
								}));
						option("No", new Dialogue()
								.addPlayer(HeadE.SHAKING_HEAD, "I'm sorry, I don't really think I should get involved.")
						);
					}
				});
			}
			if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 2){
				if (!player.getInventory().containsItem(4283)){
					addNPC(npcId, HeadE.ANGRY, "What have you done with the petition?");
					addPlayer(HeadE.NERVOUS, "I've lost it!");
					addNPC(npcId,HeadE.SHAKING_HEAD, "Here, take another.");
					if(player.getInventory().hasFreeSlots())
					{
						player.getInventory().addItem(4283);
						player.sendMessage("The ghost hands you a petition.");
					}
					else
					{
						addPlayer(HeadE.SHAKING_HEAD,"Sorry, I don't have room for that.");
					}
				}
				else if (!(player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") >= 10)){
					addNPC(npcId, HeadE.SHAKING_HEAD, "This isn't enough signatures. You only have " + player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") + "");
				}
				else {
					addNPC(npcId, HeadE.HAPPY_TALKING, "You've got them all! Now go and present them to Necrovarus!!");
					player.getInventory().deleteItem(4283, 1);
					player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 3);
				}
			}
			if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) >=3 && !player.isQuestComplete(Quest.GHOSTS_AHOY))
			{
				addNPC(npcId, HeadE.HAPPY_TALKING, "So have you presented the petition to Necrovarus?");
				addPlayer(HeadE.SAD, "Yes. He burned it.");
				addNPC(npcId, HeadE.SKEPTICAL, "That's exactly what I thought he would do.");
				addPlayer(HeadE.ANGRY, "Well, if you knew that he would do that, why have I been wasting my time running around after ghosts for signatures?");
				addNPC(npcId, HeadE.HAPPY_TALKING, "It never hurts to get involved in politics.");
			}
		}
		else {
			addNPC(npcId,HeadE.FRUSTRATED,"Woooo wooo wooooo woooo");
			create();
			player.sendMessage("You cannot understand the ghost.");
		};
	}
}

