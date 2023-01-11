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
package com.rs.game.content.world.areas.portPhasmatys.npcAnnounce;

import com.rs.game.model.entity.ForceTalk;
import com.rs.game.model.entity.npc.NPC;
import com.rs.lib.game.WorldTile;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.NPCInstanceHandler;

@PluginEventHandler
public class GravingasWorld extends NPC {
	private static int npcId = 6075;

	public GravingasWorld(int id, WorldTile tile) {
		super(id, tile);
	}

	@Override
	public void processNPC() {
		super.processNPC(); //TODO add support for ghostspeak?
		if (Utils.random(30) == 0)
		{
			String[] dialogues = new String[] {
			"Down with Necrovarus!!",
			"Rise up my fellow ghosts, and we shall be victorious!!",
			"Power to the Ghosts!!",
			"Rise together, Ghosts without a cause!!",
			"United we conquer - divided we fall!!",
			"We shall overcome!!",
			"Let Necrovarus know we want out!!",
			"Don't stay silent - victory in numbers!!",
			};
			setNextForceTalk(new ForceTalk(dialogues[Utils.random(8)]));
		}
	}

	public static NPCInstanceHandler toFunc = new NPCInstanceHandler(npcId) {
		@Override
		public NPC getNPC(int npcId, WorldTile tile) {
			return new GravingasWorld(npcId, tile);
		}
	};
}
