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
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

import static java.lang.Math.round;

@PluginEventHandler
public class GhostVillagerD extends Conversation {
	private static int npcId = 1697;

	public static NPCClickHandler GhostVillagerD = new NPCClickHandler(new Object[]{npcId}) {
		@Override
		//Handle Right-Click
		public void handle(NPCClickEvent e) {
			switch (e.getOption()) {
				//Start Conversation
				case "Talk-To" -> e.getPlayer().startConversation(new GhostVillagerD(e.getPlayer()));
			}
		}
	};

	public boolean BedsheetEquipped() {
		int helmId = player.getEquipment().getHatId();
		if (helmId == -1)
			return false;
		return ItemDefinitions.getDefs(helmId).getName().contains("Bedsheet");
	}

	public GhostVillagerD(Player player) {
		super(player);
		if (player.getEquipment().GhostEquipped()) {
			if(BedsheetEquipped()) {
				if (player.getEquipment().getHatId() == 4284) { //Can't wear ATM
					if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 1) {
						//Talking to a villager wearing the plain bedsheet during quest
						addPlayer(HeadE.CALM_TALK, "Would you sign this petition form, please?");
						addNPC(npcId, HeadE.SKEPTICAL_HEAD_SHAKE, "Why are you wearing that bedsheet? If you're trying to pretend to be one of us, you're not fooling anybody – you're not even green!");
					} else {
						//Talking to a villager wearing the plain bedsheet outside of quest. Client doesn't render. 
						addPlayer(HeadE.HAPPY_TALKING, "Woooo wooo wooooo woooo");
						addNPC(npcId, HeadE.SKEPTICAL_HEAD_SHAKE, "Why are you wearing that bedsheet? If you're trying to pretend to be one of us, you're not fooling anybody – you're not even green!");
					}
				} else if (player.getEquipment().getHatId() == 4285) {
					if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 1) {
						//Talking to a villager wearing the green bedsheet
						addPlayer(HeadE.CALM_TALK, "Would you sign this petition form, please?");
						//Generate the strings for later
						String[] fail = new String[]{
								"I will have you know that I am a fervent supporter of Necrovarus.",
								"My answer is no.",
								"How dare you accost me in the street?",
								"I don't have time for this nonsense.",
								"Get lost."
						};
						String[] successful = new String[]{
								"Most certainly, I will. ",
								"I'll do anything that annoys Necrovarus.",
								"Yes, of course."
						};
						String[] charge = new String[]{
								"I will if you make it worth my while...",
								"You scratch my back and I'll scratch yours...",
								"It'll cost you...",
						};
						//Roll for success
						switch (Utils.random(1,4)) {
							case 1: {
								//Pick a phrase from the successful options
								addNPC(npcId, HeadE.CALM, successful[Utils.random(3)]);
								//Increase signatures
								player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).incI("signatures");
								//Tell the player the current number of signatures
								player.sendMessage("The ghost signs your petition. You have obtained " + player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") + " of 10 signatures so far.");
								//Check for completion
								if (player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") == 10) {
									//Advance the quest
									player.sendMessage("The petition is complete, you should return it to Gravingas.");
									player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 2);
								}
								break;
							}
							case 2: {
								//Pick a phrase from the unsuccessful options, do not pass go, do not collect a signature
								addNPC(npcId, HeadE.CALM, fail[(Utils.random(5))]);
								break;
							}
							case 3: {
								//Pick a phrase from the charitable options
								addNPC(npcId, HeadE.EVIL_LAUGH, charge[(Utils.random(3))]);
								//Roll for price between 1-3
								int cost = Utils.random(1, 3);
								addPlayer(HeadE.SKEPTICAL, "How much?");
								//Give the player the price
								addNPC(npcId, HeadE.HAPPY_TALKING, "Oh, it'll cost you " + cost + " ecto-tokens.");
								//Let the player choose to pay
								addOptions("How much?", new Options() {
									@Override
									public void create() {
										option("Okay", new Dialogue()
												.addPlayer(HeadE.CONFUSED, "Okay, if you insist.")
												//4278 Ecto-token
												.addNext(() -> {
													//Check the player has the right number of tokens
													if (player.getInventory().containsItem(4278, cost)) {
														//Delete the tokens from the player's inventory
														player.getInventory().deleteItem(4278, cost);
														//Increase the signatures counter
														player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).incI("signatures");
														//Tell the player the number of signatures
														player.sendMessage("The ghost signs your petition costing " + cost + " tokens. You have obtained " + player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") + " of 10 signatures so far.");
														//Check for completion
														if (player.getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("signatures") == 10) {
															//Advance the quest
															player.sendMessage("The petition is complete, you should return it to Gravingas.");
															player.getQuestManager().setStage(Quest.GHOSTS_AHOY, 2);
														}
													} else {
														//Handle insufficient tokens
														addPlayer(HeadE.SHAKING_HEAD, "Sorry, I don't have that many on me.");
														addNPC(npcId, HeadE.SHAKING_HEAD, "No tokens, no signature.");
													}
												}));
										option("No", new Dialogue()
												//Player refuses to pay
												.addPlayer(HeadE.CONFUSED, "There's no way I'm giving in to corruption.")
												.addNPC(npcId, HeadE.SHAKING_HEAD, "Suit yourself.")
										);
									}
								});
								break;
							}
						}
					} else {
						//Handle the post quest response if wearing a bedsheet
						addPlayer(HeadE.HAPPY_TALKING, "Woooo wooo wooooo woooo");
						addNPC(npcId, HeadE.SKEPTICAL_HEAD_SHAKE, "Sorry I don't speak ghost...");
					}
				}
			}
			else {
				if (player.getQuestManager().getStage(Quest.GHOSTS_AHOY) == 1) {
					//Final quest catch - Talking to a villager as a "mortal"
					addPlayer(HeadE.CALM_TALK, "Would you sign this petition form, please?");
					addNPC(npcId, HeadE.SHAKING_HEAD, "I'm sorry, but it's hard to believe that a mortal could be interested in helping us.");
				} else {
					//Handle normal dialogue
					String[] dialogues = new String[]{
							"What do you want, mortal?",
							"We do not talk to the warm-bloods.",
							"Why did we have to listen to that maniacal priest?",
							"This cold wind blows right through you, doesn't it?",
							"Worship the Ectofuntus all you want, but don't bother us, human.",
					};
					addNPC(npcId, HeadE.CALM, dialogues[Utils.random(5)]);
				}
			}
		}

		else {
			//Handle no ghostspeak
			addNPC(npcId,HeadE.FRUSTRATED,"Woooo wooo wooooo woooo");
			create();
			player.sendMessage("You cannot understand the ghost.");
		}
	}
}

/*
Xenophobia



Asking the same villager twice in a row
Player: Would you sign this petition form, please?
Ghost villager: You only just asked me the same thing! Leave me alone – I've had my say!
After getting the 10th signature
You have succeeded in obtaining 10 signatures on the petition form!

 */
