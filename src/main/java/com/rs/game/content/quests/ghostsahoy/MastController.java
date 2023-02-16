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
package com.rs.game.content.quests.ghostsahoy;


import com.rs.game.model.entity.player.Controller;
import com.rs.game.model.entity.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasks;
import com.rs.lib.game.WorldTile;
import com.rs.lib.util.Utils;


public class MastController extends Controller {

    private static WorldTask watcher;
    private static WorldTask windWatcher;


    private transient Player player;

    public MastController(Player player) {
        this.player = player;
    }

    public void start() {
        if (!player.getTempAttribs().getB("MastEvent")) {
            setWindSpeed();
            sendInterfaces();
            player.getTempAttribs().setB("MastEvent", true);
        }
    }

    public static boolean windSpeed;

    public void setWindSpeed() {
        WorldTasks.schedule(windWatcher = new WorldTask() {
            @Override
            public void run() {
                if (Utils.random(100) <= 30) {
                        windSpeed = !windSpeed;
                    }
                }
        }, 0, 3);
    }


    public void sendInterfaces() {
        WorldTile tile = WorldTile.of(3619, 3543, 2);
        WorldTasks.schedule(watcher = new WorldTask() {
            @Override
            public void run() {
                if (player.getTile().withinDistance(tile, 5))
                {
                    if (!windSpeed)
                    {
                        player.getInterfaceManager().removeOverlay(true);
                    }
                    else
                    {
                        player.getInterfaceManager().sendOverlay(10, true);
                    }
                    return;
                }
                if (!player.getTile().withinDistance(tile, 5)) {
                    player.getInterfaceManager().removeOverlay(true);
                    player.getTempAttribs().setB("Mast", false);
                    windWatcher.stop();
                    watcher.stop();
                }
            }
        }, 0, 1);
    }
}
