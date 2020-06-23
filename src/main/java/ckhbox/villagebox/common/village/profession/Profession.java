//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.profession;

import ckhbox.villagebox.common.config.VBConfig;
import ckhbox.villagebox.common.config.jsonData.*;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.math.Rand;
import ckhbox.villagebox.common.util.registry.IRegistrable;
import ckhbox.villagebox.common.util.registry.Registry;
import ckhbox.villagebox.common.village.quest.Quest;
import ckhbox.villagebox.common.village.trading.TradingRecipe;
import ckhbox.villagebox.common.village.trading.TradingRecipeList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class Profession implements IRegistrable {

    //---------------------------------
    //registry
    public static Registry<Profession> registry = new Registry<Profession>();
    //trading recipe list
    protected TradingRecipeList tradingRecipeList;
    //texture
    protected ResourceLocation texturem;
    protected ResourceLocation texturef;

    //what professions can upgrade from this profession
    protected int[] upgradeToNextOptionIDs;
    protected Profession[] upgradeToNextOptions;//this will be automatically generated based on option classes
    //what items are needed to upgrade to this profession(usually only gems, maximum: 3 stacks)
    protected ItemStack[] upgradeToCurentNeeds;
    //what items the villager will hold (will randomly select if there are more than 1 items in the list)
    protected ItemStack[] holdItems;
    //quests
    protected List<Quest> quests;
    //unlocalized name
    protected String unlocalized;
    private int regID;

    public Profession(JsonProfession proData) {
        unlocalized = PathHelper.full("profession." + proData.name);
        loadProfessionData(proData);
    }

    public static void init() {

        JsonVBData data = JsonDataManager.GetVBData();

        for (JsonProfession pro : data.listOfProfessions) {
            registry.register(pro.professionId, new Profession(pro));
        }
    }

    @Override
    public int getRegID() {
        return regID;
    }

    @Override
    public void setRegID(int regID) {
        this.regID = regID;
    }

    private void loadProfessionData(JsonProfession proData) {
        //skin texture
        this.createTextures(proData.texture);

        //trading
        tradingRecipeList = new TradingRecipeList();
        if (proData.listOfTradingRecipes != null) {
            for (JsonTradingRecipe recipe : proData.listOfTradingRecipes) {
                this.tradingRecipeList.add(new TradingRecipe(
                        JsonHelper.stringsToItemStacks(recipe.inputs),
                        JsonHelper.stringToItemStack(recipe.output)));
            }
        }

        //upgrading
        upgradeToNextOptionIDs = proData.upgradeProfessionIDs == null ? null : proData.upgradeProfessionIDs.clone();
        upgradeToCurentNeeds = JsonHelper.stringsToItemStacks(proData.upgradeRequirements);

        //items on hands
        this.holdItems = JsonHelper.stringsToItemStacks(proData.holdItems);

        //quests
        this.quests = new ArrayList<Quest>();
        if (proData.listOfQuests != null) {
            for (JsonQuest quest : proData.listOfQuests) {
                this.quests.add(new Quest(
                        JsonHelper.stringsToItemStacks(quest.objectives),
                        JsonHelper.stringsToItemStacks(quest.rewards)));
            }
        }
    }

    public Profession[] getUpgradeToNextOptions() {
        if (this.upgradeToNextOptions == null) {
            if (this.upgradeToNextOptionIDs != null && this.upgradeToNextOptionIDs.length > 0) {
                ArrayList<Profession> list = new ArrayList<Profession>();
                for (int i = 0; i < this.upgradeToNextOptionIDs.length; i++) {
                    Profession p = registry.get(this.upgradeToNextOptionIDs[i]);
                    if (!isProIDBanned(p.getRegID())) {
                        list.add(p);
                    }
                }
                if (list.size() > 0) {
                    this.upgradeToNextOptions = list.toArray(new Profession[list.size()]);
                }
            }
        }
        return this.upgradeToNextOptions;
    }

    private boolean isProIDBanned(int proid) {
        if (VBConfig.proIDBanList == null)
            return false;
        else {
            for (int i = 0; i < VBConfig.proIDBanList.length; i++) {
                if (proid == VBConfig.proIDBanList[i])
                    return true;
            }
            return false;
        }
    }

    public ItemStack[] getUpgradeToCurentNeeds() {
        return VBConfig.freeUpgrading ? null : this.upgradeToCurentNeeds;
    }

    public ResourceLocation getTexture(boolean male) {
        return male ? this.texturem : this.texturef;
    }

    public TradingRecipeList getTradingRecipeList() {
        return this.tradingRecipeList;
    }

    public ItemStack getRandomHoldItem() {
        if (Rand.get().nextFloat() < 0.6F || this.holdItems == null || this.holdItems.length < 1)
            return null;
        else
            return this.holdItems[Rand.get().nextInt(this.holdItems.length)];
    }

    public String getUnloalizedDisplayName() {
        return this.getUnlocalized() + ".name";
    }

    public String getUnloalizedDescription() {
        return this.getUnlocalized() + ".desc";
    }

    protected void createTextures(String name) {
        this.texturem = new ResourceLocation(PathHelper.full("textures/entity/villager/" + name + "_m.png"));
        this.texturef = new ResourceLocation(PathHelper.full("textures/entity/villager/" + name + "_f.png"));
    }

    public List<Quest> getQuests() {
        return this.quests;
    }

    protected String getUnlocalized() {
        return unlocalized;
    }

}
