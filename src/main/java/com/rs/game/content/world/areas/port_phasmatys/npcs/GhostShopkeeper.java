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
package com.rs.game.content.world.areas.port_phasmatys.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.content.quests.ghostsahoy.npcs.OldCroneD;
import com.rs.game.engine.dialogue.Dialogue;
import com.rs.game.engine.dialogue.HeadE;
import com.rs.game.model.entity.player.Player;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.NPCClickHandler;
import com.rs.utils.shop.ShopsHandler;

@PluginEventHandler
public class GhostShopkeeper {
	private static int npcId = 1699;

	public static boolean ghostEquipped(Player player) {
		int neckId = player.getEquipment().getNeckId();
		if (neckId == -1)
			return false;
		else {
			return ItemDefinitions.getDefs(neckId).getName().contains("Ghostspeak");
		}
	}

	public static NPCClickHandler ghostShopkeeper = new NPCClickHandler(new Object[] { npcId }, e -> {
		if (e.getOption().equalsIgnoreCase("talk-to")) {
			if (!ghostEquipped(e.getPlayer())) {
				e.getPlayer().startConversation(new Dialogue().addNPC(npcId, HeadE.FRUSTRATED, "Woooo wooo wooooo woooo"));
				e.getPlayer().sendMessage("You cannot understand the ghost.");
				return;
			}

			e.getPlayer().startConversation(new Dialogue()
					.addNPC(npcId, HeadE.SECRETIVE, "Would you like to buy or sell anything?")
					.addOptions(ops -> {
						ops.add("I'd like to see what you have for sale.", () -> ShopsHandler.openShop(e.getPlayer(), "port_phasmatys_general_store"));
						ops.add("No thanks.")
								.addPlayer(HeadE.CONFUSED, "No thanks.");
					}));
		}
		if (e.getOption().equalsIgnoreCase("trade")) {
			ShopsHandler.openShop(e.getPlayer(), "port_phasmatys_general_store");
		}
	});
}

