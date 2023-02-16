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
package com.rs.game.content.world.areas.canifis;

import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.ObjectClickHandler;

@PluginEventHandler
public class Canifis {

	public static ObjectClickHandler handleNettles = new ObjectClickHandler(new Object[]{"Nettles"}, e -> {
		switch (e.getOption()) {
			case "Pick" -> {
				if (e.getPlayer().getEquipment().wearingGloves())
					if (e.getPlayer().getInventory().hasFreeSlots()) {
						e.getPlayer().getInventory().addItem(4241, 1);
						e.getPlayer().sendMessage("You pick some nettles");
					} else {
						e.getPlayer().sendMessage("You have no free space");
					}
				else {
					e.getPlayer().sendMessage("You shouldn't try picking these with your bare hands..");
				}
			}
		}

	});
}