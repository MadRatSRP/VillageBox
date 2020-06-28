//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.config.jsonData;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class JsonProfession {
    public int professionId;

    public ArrayList<JsonTradingRecipe> listOfTradingRecipes;
    public ArrayList<JsonQuest> listOfQuests;

    @Nullable
    public int[] upgradeProfessionIDs;

    public String[] upgradeRequirements;
    public String[] holdItems;

    public String texture;
    public String name;

    public JsonProfession() {
        listOfTradingRecipes = new ArrayList<>();
        listOfQuests = new ArrayList<>();
    }

    public JsonProfession(int professionId, ArrayList<JsonTradingRecipe> listOfTradingRecipes,
                          @Nullable ArrayList<JsonQuest> listOfQuests, @Nullable int[] upgradeProfessionIDs,
                          @Nullable String[] upgradeRequirements, String[] holdItems, String texture, String name) {
        this.professionId = professionId;
        this.listOfTradingRecipes = listOfTradingRecipes;
        this.listOfQuests = listOfQuests;
        this.upgradeProfessionIDs = upgradeProfessionIDs;
        this.upgradeRequirements = upgradeRequirements;
        this.holdItems = holdItems;
        this.texture = texture;
        this.name = name;
    }
}
