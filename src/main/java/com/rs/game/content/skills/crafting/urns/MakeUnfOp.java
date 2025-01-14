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
package com.rs.game.content.skills.crafting.urns;

import com.rs.game.engine.dialogue.impl.MakeXActionD;
import com.rs.game.engine.dialogue.impl.MakeXItem;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.Constants;
import com.rs.lib.game.Item;

public class MakeUnfOp extends MakeXActionD {

	public MakeUnfOp(Player player, Urn... urns) {
		for (Urn urn : urns)
			addOption(new MakeXItem(player, new Item(1761, 2), new Item(urn.unfId()), urn.getUnfXp(), 899, urn.getLevel(), Constants.CRAFTING, 4));
	}

}
