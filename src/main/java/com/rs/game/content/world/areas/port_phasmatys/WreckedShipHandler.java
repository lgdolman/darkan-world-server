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
//  Copyright (C) 2022 Liam Dolman
//  This file is part of project: Darkan
//
package com.rs.game.content.world.areas.port_phasmatys;

import com.rs.game.World;
import com.rs.game.content.quests.ghostsahoy.MastController;
import com.rs.game.content.world.AgilityShortcuts;
import com.rs.game.engine.quest.Quest;
import com.rs.game.model.entity.Hit;
import com.rs.game.model.entity.npc.OwnedNPC;
import com.rs.game.model.object.GameObject;
import com.rs.lib.Constants;
import com.rs.lib.game.Animation;
import com.rs.lib.game.Item;
import com.rs.lib.game.WorldTile;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.ObjectClickHandler;
import com.rs.utils.Ticks;

@PluginEventHandler
public class WreckedShipHandler {
    public static ObjectClickHandler handleShipwreckLadders = new ObjectClickHandler(new Object[]{5266, 5265}, e -> {
        switch (e.getObjectId()) {
            case 5266 -> {
                if (e.getObject().getTile().isAt(3609, 3543, 1)) {
                    e.getPlayer().useStairs(828, WorldTile.of(3609, 3543, 0), 1, 2);
                }
                if (e.getObject().getTile().isAt(3613, 3543, 1)) {
                    e.getPlayer().useStairs(828, WorldTile.of(3613, 3543, 0), 1, 2);
                }
                if (e.getObject().getTile().isAt(3615, 3541, 2)) {
                    e.getPlayer().useStairs(828, WorldTile.of(3615, 3541, 1), 1, 2);
                }
            }
            case 5265 -> {
                if (e.getObject().getTile().isAt(3609, 3543, 0)) {
                    e.getPlayer().useStairs(828, WorldTile.of(3608, 3543, 1), 1, 2);
                }
                if (e.getObject().getTile().isAt(3613, 3543, 0)) {
                    e.getPlayer().useStairs(828, WorldTile.of(3613, 3543, 1), 1, 2);
                }
                if (e.getObject().getTile().isAt(3615, 3541, 1)) {
                    e.getPlayer().useStairs(828, WorldTile.of(3616, 3541, 2), 1, 2);
            new MastController(e.getPlayer()).start();
                }
            }
        }
    });


    public static ObjectClickHandler handleShipwreckChests = new ObjectClickHandler(new Object[]{5270, 5271, 5272, 5273}, e -> {
        e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F1", 0);
        int Fragment1 = e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F1");
        e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F2", 0);
        int Fragment2 = e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2");
        e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F3", 0);
        int Fragment3 = e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F3");
        switch (e.getObjectId()) {
            case 5270 -> {
                if (e.getObject().getTile().isAt(3619, 3545, 1)) {
                    if (!e.getPlayer().getInventory().containsItem(4273) || Fragment1 != 0) {
                        e.getPlayer().sendMessage("This chest is locked");
                        break;
                    } else {
                        if (e.getPlayer().getInventory().hasFreeSlots()) {
                            e.getPlayer().getInventory().addItem(4274);
                            e.getPlayer().sendMessage("You find a fragment of a map.");
                            e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F1", 1);
                            if (e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F1") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") >= 3) {
                                e.getPlayer().getInventory().deleteItem(4273, 1);

                            }
                        } else {
                            e.getPlayer().sendMessage("You have no free space to carry anymore loot.");
                        }
                    }
                }
                if (e.getObject().getTile().isAt(3606, 3564, 0)) {
                    if (!e.getPlayer().getInventory().containsItem(4273) || Fragment2 != 0) {
                        e.getPlayer().sendMessage("This chest is locked");
                        break;
                    } else {
                        if (e.getPlayer().getInventory().hasFreeSlots()) {
                            e.getPlayer().getInventory().addItem(4274);
                            e.getPlayer().sendMessage("You find a fragment of a map.");
                            e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F2", 1);
                            if (e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F1") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") <= 3) {
                                e.getPlayer().getInventory().deleteItem(4273, 1);
                                e.getPlayer().sendMessage("The key sticks in the lock.");
                            }
                        } else {
                            e.getPlayer().sendMessage("You have no free space to carry anymore loot.");
                        }
                    }
                }
                //Ghosts Ahoy
                if (e.getObject().getTile().isAt(3606, 3564, 0)) {
                    if (!e.getPlayer().getInventory().containsItem(4273) || Fragment2 != 0) {
                        e.getPlayer().sendMessage("This chest is locked");
                        break;
                    } else {
                        if (e.getPlayer().getInventory().hasFreeSlots()) {
                            e.getPlayer().getInventory().addItem(4274);
                            e.getPlayer().sendMessage("You find a fragment of a map.");
                            e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).setI("F2", 1);
                            if (e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F1") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") + e.getPlayer().getQuestManager().getAttribs(Quest.GHOSTS_AHOY).getI("F2") <= 3) {
                                e.getPlayer().getInventory().deleteItem(4273, 1);
                                e.getPlayer().sendMessage("The key sticks in the lock.");
                            }
                        } else {
                            e.getPlayer().sendMessage("You have no free space to carry anymore loot.");
                        }
                    }
                }
                //Ghosts Ahoy
                if (e.getPlayer().getInventory().containsItem(4273) && Fragment3 >= 0)
                    switch (Utils.random(1, 6)) {
                        case 1, 2, 3 -> {
                            e.getPlayer().sendMessage("You search the chest but find nothing.");
                            GameObject openedChest = new GameObject(5271, e.getObject().getType(), e.getObject().getRotation(), e.getObject().getX(), e.getObject().getY(), e.getObject().getPlane());
                            e.getPlayer().faceObject(openedChest);
                            e.getPlayer().setNextAnimation(new Animation(536));
                            e.getPlayer().lock(2);
                            World.spawnObjectTemporary(openedChest, Ticks.fromSeconds(30));
                        }
                        case 4 -> {
                            int coins = Utils.random(1, 3);
                            e.getPlayer().sendMessage("You find " + coins + " coins.");
                            e.getPlayer().getInventory().addCoins(coins);
                            GameObject openedChest = new GameObject(5271, e.getObject().getType(), e.getObject().getRotation(), e.getObject().getX(), e.getObject().getY(), e.getObject().getPlane());
                            e.getPlayer().faceObject(openedChest);
                            e.getPlayer().setNextAnimation(new Animation(536));
                            e.getPlayer().lock(2);
                            World.spawnObjectTemporary(openedChest, Ticks.fromSeconds(30));
                        }
                        case 5 -> {
                            e.getPlayer().sendMessage("A Rat jumps out of the chest.");
                            OwnedNPC lobster = new OwnedNPC(e.getPlayer(), 10482, WorldTile.of(3611, 3543, 0), false);
                            lobster.setTarget(e.getPlayer());
                            lobster.sendDrop(e.getPlayer(), new Item(4274));
                            GameObject openedChest = new GameObject(5271, e.getObject().getType(), e.getObject().getRotation(), e.getObject().getX(), e.getObject().getY(), e.getObject().getPlane());
                            e.getPlayer().faceObject(openedChest);
                            e.getPlayer().setNextAnimation(new Animation(536));
                            e.getPlayer().lock(2);
                            World.spawnObjectTemporary(openedChest, Ticks.fromSeconds(60));
                        }
                    }
                else {
                    e.getPlayer().sendMessage("It's locked.");
                }
            }
            case 5271, 5273 -> {
                e.getPlayer().sendMessage("It's empty");
            }
            case 5272 -> {
                if (e.getPlayer().getInventory().containsItem(4273) && Fragment3 >= 0) {
                    e.getPlayer().sendMessage("A Lobster jumps out of the chest. Some paper appears to have fallen from it's mouth.");
                    OwnedNPC lobster = new OwnedNPC(e.getPlayer(), 1693, WorldTile.of(3611, 3543, 0), false);
                    lobster.setIgnoreDocile(true);
                    lobster.setForceAgressive(true);
                    lobster.setForceAggroDistance(64);
                    lobster.setIntelligentRouteFinder(true);
                    lobster.setTarget(e.getPlayer());
                    lobster.sendDrop(e.getPlayer(), new Item(4274)); //TODO drop on death
                    GameObject openedChest = new GameObject(5271, e.getObject().getType(), e.getObject().getRotation(), e.getObject().getX(), e.getObject().getY(), e.getObject().getPlane());
                    e.getPlayer().faceObject(openedChest);
                    e.getPlayer().setNextAnimation(new Animation(536));
                    e.getPlayer().lock(2);
                    World.spawnObjectTemporary(openedChest, Ticks.fromSeconds(180));
                } else {
                    e.getPlayer().sendMessage("It's locked.");
                }
            }
        }
    });
    public static ObjectClickHandler handleShipwreckPlank = new ObjectClickHandler(new Object[]{5285, 5286}, e -> {
        switch (e.getObjectId()) {
            case 5285 -> {
                if (e.getOption().equalsIgnoreCase("Cross")) {
                    e.getPlayer().useStairs(-1, WorldTile.of(3605, 3548, 0), 1, 3);
                }
            }
            case 5286 -> {
                if (e.getOption().equalsIgnoreCase("Cross")) {
                    e.getPlayer().useStairs(-1, WorldTile.of(3605, 3545, 1), 1, 3);
                }
            }
        }
    });

    public static ObjectClickHandler handleMast= new ObjectClickHandler(new Object[]{5274}, e -> {

        if (e.getOption().equalsIgnoreCase("Search")) {
            new MastController(e.getPlayer()).start();
            if(MastController.windSpeed){
                e.getPlayer().sendMessage("The wind is too high!");
            }
            else {
                e.getPlayer().sendMessage("The wind is low");
            }
        }
    });

    public static ObjectClickHandler handleShipwreckRocks = new ObjectClickHandler(new Object[]{5269}, e -> {
        switch (e.getObjectId()) {
            case 5269 -> {
                //X Movements
                if (e.getObject().getTile().isAt(3604, 3550, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(e.getPlayer().getX() > e.getObject().getX() ? -4 : 4, 0, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3602, 3550, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(e.getPlayer().getX() > e.getObject().getX() ? -4 : 4, 0, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3599, 3552, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(e.getPlayer().getX() > e.getObject().getX() ? -4 : 4, 0, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3597, 3552, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(e.getPlayer().getX() > e.getObject().getX() ? -4 : 4, 0, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3599, 3564, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(e.getPlayer().getX() > e.getObject().getX() ? -4 : 4, 0, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3601, 3564, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(e.getPlayer().getX() > e.getObject().getX() ? -4 : 4, 0, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                //Y Movements
                if (e.getObject().getTile().isAt(3595, 3554, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, e.getPlayer().getY() > e.getObject().getY() ? -4 : 4, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3595, 3556, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, e.getPlayer().getY() > e.getObject().getY() ? -4 : 4, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
                if (e.getObject().getTile().isAt(3597, 3559, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, e.getPlayer().getY() > e.getObject().getY() ? -4 : 4, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }

                if (e.getObject().getTile().isAt(3597, 3561, 0)) {
                    if (Utils.random(20) <= 2) {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, 0, 0), 760, 1);
                        e.getPlayer().sendMessage("You fail to make the jump.");
                        e.getPlayer().drainRunEnergy(5);
                        e.getPlayer().applyHit(new Hit(Utils.random(2,6), Hit.HitLook.TRUE_DAMAGE));
                    } else {
                        AgilityShortcuts.forceMovement(e.getPlayer(), e.getPlayer().transform(0, e.getPlayer().getY() > e.getObject().getY() ? -4 : 4, 0), 769, 1);
                        e.getPlayer().getSkills().addXp(Constants.AGILITY, 3);
                        e.getPlayer().drainRunEnergy(5);
                    }
                }
            }
        }
    });
}


