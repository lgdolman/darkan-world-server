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
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.model.entity.player.Equipment;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

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

	public GhostVillagerD(Player player) {
		super(player);
		if (player.getEquipment().GhostEquipped())
		{
			String[] dialogues = new String[] {
					"What do you want, mortal?",
					"We do not talk to the warm-bloods.",
					"Why did we have to listen to that maniacal priest?",
					"This cold wind blows right through you, doesn't it?",
					"Worship the Ectofuntus all you want, but don't bother us, human.",
			};
			addNPC(npcId, HeadE.CALM, dialogues[Utils.random(5)]);
		}
		else {
			addNPC(npcId,HeadE.FRUSTRATED,"Woooo wooo wooooo woooo");
			create();
			player.sendMessage("You cannot understand the ghost.");
		}
	}
}

/*
Xenophobia
Talking to a villager as a "mortal"
Player: Would you sign this petition form, please?
Ghost villager: I'm sorry, but it's hard to believe that a mortal could be interested in helping us.
Talking to a villager wearing the bedsheet
Player: Would you sign this petition form, please?
Ghost villager: Why are you wearing that bedsheet? If you're trying to pretend to be one of us, you're not fooling anybody – you're not even green!
Petitioning Phasmatys
Possible unsuccessful attempts
Player: Would you sign this petition form, please?
Ghost villager: I will have you know that I am a fervent supporter of Necrovarus.
Ghost villager: My answer is no.
Ghost villager: How dare you accost me in the street?
Ghost villager: I don't have time for this nonsense.
Ghost villager: Get lost.
Possible freebie successful attempts
Player: Would you sign this petition form, please?
Ghost villager: Most certainly, I will.
Ghost villager: I'll do anything that annoys Necrovarus.
Ghost villager: Yes, of course.
The ghost signs your petition. You have obtained [1-9] signature[s] so far.
Possible costly attempts
Player: Would you sign this petition form, please?
Ghost villager: I will if you make it worth my while...
Ghost villager: You scratch my back and I'll scratch yours...
Ghost villager: It'll cost you...
Player: How much?
Ghost villager: Oh, it'll cost you [1-3] ecto-tokens.
Player: Okay, if you insist.
The ghost signs your petition. You have obtained [1-9] signature[s] so far.
Player: There's no way I'm giving in to corruption.
Ghost villager: Suit yourself.
If you don't have enough ecto-tokens
Player: I don't have that many on me.
Ghost villager: No tokens, no signature.
Asking the same villager twice in a row
Player: Would you sign this petition form, please?
Ghost villager: You only just asked me the same thing! Leave me alone – I've had my say!
After getting the 10th signature
You have succeeded in obtaining 10 signatures on the petition form!

 */
