//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.config;

import ckhbox.villagebox.Constants;
import ckhbox.villagebox.VillageBoxMod;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class VBConfig {
    //properties
    public static int[] proIDBanList;                    //banned profession IDs
    public static boolean destroyBlocksDropCoins;        //ture: destroy blocks can drop coins
    public static boolean killMobsDropCoins;            //true: kill mobs can drop coins
    public static boolean displayExtraInfo;                //true: (not working)will display extra information in game like the profession id
    public static boolean freeUpgrading;                //set to ture when you want nothing be cunsumed on upgrading villagers
    public static int reviveTicks;                        //how many ticks to revive a villager
    public static boolean oneVillagerPerRoom;            //don't allow more than one villagers live in the same room
    public static int questFrequency;                    //how fast the new quest appears.
    //e.g. 0 or a number less than 0 means no new quest,
    //24000 means one new quest per day in average,
    //72000 means one new quest every three days in average
    public static int questLifetime;                    //the life time of a quest. e.g. 48000 means the quest will exist 2 minecraft days
    public static boolean disableBookAtStarting;            ////disable the village box at starting

    public static void load(File file) {
        Configuration conf = new Configuration(file, Constants.MOD_VERSION);
        Property pproperty = null;

        conf.load();

        //profession ban list
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "BannedProIDList", new int[0]);
        pproperty.setComment("Banned profession IDs. One id per line, empty means no banned professions");
        proIDBanList = pproperty.getIntList();

        //coin earning options
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "DestroyBlocksDropCoins", true);
        pproperty.setComment("Does destroying blocks drop coins?");
        destroyBlocksDropCoins = pproperty.getBoolean();

        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "KillMobsDropCoins", true);
        pproperty.setComment("Does killing mobs drop coins?");
        killMobsDropCoins = pproperty.getBoolean();

        //display extra information
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "displayExtraInfo", false);
        pproperty.setComment("removed");
        displayExtraInfo = pproperty.getBoolean();

        //villager revive ticks
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "ReviveTicks", 24000);
        pproperty.setComment("How many ticks until a dead villager revives again");
        reviveTicks = pproperty.getInt();

        //free upgrading
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "FreeUpgrading", false);
        pproperty.setComment("Set to true when you want nothing be consumed on upgrading villagers");
        freeUpgrading = pproperty.getBoolean();

        //one villager one room
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "OneVillagerPerRoom", false);
        pproperty.setComment("Set to true to disallow more than one villagers live in the same room");
        oneVillagerPerRoom = pproperty.getBoolean();

        //quest frequency
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "QuestFrequency", 24000);
        pproperty.setComment("How fast the new quest appears. e.g. 0 or a number less than 0 means no new quest, 24000 means one new quest per day in average, 72000 means one new quest every three days in average");
        questFrequency = pproperty.getInt();

        //quest lifetime
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "QuestLifetime", 48000);
        pproperty.setComment("The life time of a quest. e.g. 48000 means the quest will exist 2 minecraft days");
        questLifetime = pproperty.getInt();

        //disable the village box at starting
        pproperty = conf.get(Configuration.CATEGORY_GENERAL, "DisableBookAtStarting", false);
        pproperty.setComment("Disable the village box at starting");
        disableBookAtStarting = pproperty.getBoolean();

        conf.save();
    }
}
