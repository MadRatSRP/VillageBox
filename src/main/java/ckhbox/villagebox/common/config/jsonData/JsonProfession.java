//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.config.jsonData;

import java.util.ArrayList;

public class JsonProfession {

    public int id;
    public String name;

    public ArrayList<JsonTradingRecipe> tradingRecipes;
    public ArrayList<JsonQuest> quests;

    public int[] upgradeProfessionIDs;
    public String[] upgradeRequirements;
    public String[] holdItems;
    public String texture;

    public JsonProfession() {
        tradingRecipes = new ArrayList<JsonTradingRecipe>();
        quests = new ArrayList<JsonQuest>();
    }

}
