//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item;

import ckhbox.villagebox.VillageBoxMod;
import ckhbox.villagebox.common.block.ModBlocks;
import ckhbox.villagebox.common.item.armor.ItemArmor;
import ckhbox.villagebox.common.item.book.ItemDismissalScroll;
import ckhbox.villagebox.common.item.book.ItemResetScroll;
import ckhbox.villagebox.common.item.book.ItemTreasureBook;
import ckhbox.villagebox.common.item.book.ItemVillageBook;
import ckhbox.villagebox.common.item.common.*;
import ckhbox.villagebox.common.item.material.ItemSteelIngot;
import ckhbox.villagebox.common.item.painting.ItemPainting;
import ckhbox.villagebox.common.item.tool.ItemAxe;
import ckhbox.villagebox.common.item.tool.ItemPickaxe;
import ckhbox.villagebox.common.item.tool.ItemShovel;
import ckhbox.villagebox.common.item.tool.ItemWeatherStone;
import ckhbox.villagebox.common.item.weapon.*;
import ckhbox.villagebox.common.util.helper.PathHelper;
import com.sun.istack.internal.Nullable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    public static final CreativeTabs tabVB = new CreativeTabs("tabVillageBox") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.mailbox);
        }

        @SideOnly(Side.CLIENT)
        public int getIconItemDamage() {
            return 0;
        }
    };

    public static ItemTreasureBook treasureHuntBookI;
    public static ItemTreasureBook treasureHuntBookII;
    public static ItemTreasureBook treasureHuntBookIII;
    public static ItemCoin bronzeCoin;
    public static ItemCoin silverCoin;
    public static ItemCoin goldCoin;
    public static ItemMail mail;
    public static ItemResetScroll resetScroll;
    public static ItemDismissalScroll dismissalScroll;
    public static Item invitation;
    public static ItemVillageBook villageBook;

    // staff
    public static ItemStaff staff;
    public static ItemFireStaff fireStaff;
    public static ItemWaterStaff waterStaff;
    public static ItemNatureStaff natureStaff;
    public static ItemLightStaff lightStaff;
    public static ItemDarkStaff darkStaff;
    public static ItemSteelIngot steelIngot;
    public static Item steelHoe;
    public static Item steelAxe;
    public static Item steelPickaxe;
    public static Item steelShovel;
    public static Item steelSword;
    public static ItemFireSword fireSword;
    public static ItemWaterSword waterSword;
    public static ItemNatureSword natureSword;
    public static ItemDarkSword darkSword;
    public static ItemLightSword lightSword;
    public static Item steelHelmet;
    public static Item steelChestplate;
    public static Item steelLeggings;
    public static Item steelBoots;
    public static Item woodenHelmet;
    public static Item woodenChestplate;
    public static Item woodenLeggings;
    public static Item woodenBoots;
    public static Item woodenClub;
    public static ItemWeatherStone sunStone;
    public static ItemWeatherStone rainStone;
    public static Item painting;
    public static Item efficientBow;

    // materials
    public static Item cabbage;
    public static Item chili;
    public static Item corn;
    public static Item soybean;
    public static Item riceplant;
    public static Item mayonnaise;
    public static Item cheese;
    public static Item ballOfWool;
    public static Item rice;
    public static Item riceFlour;
    public static Item wheatFlour;
    public static Item soySauce;
    public static Item cookingOil;
    public static Item boiledEgg;
    public static Item tofu;
    public static Item shrimp;
    public static Item crab;
    public static Item seaweed;
    public static Item salt;

    // fruits
    public static Item grape;
    public static Item lemon;
    public static Item orange;
    public static Item pineapple;
    public static Item strawberry;
    public static Item melon;
    public static Item banana;
    public static Item grapeWine;
    public static Item cookingWine;
    public static Item cocktail;
    public static Item beer;
    public static Item whisky;
    public static Item vodka;

    //food
    public static Item porkRamen;
    public static Item beefNoodleSoup;
    public static Item noodleSoup;
    public static Item carrotCake;
    public static Item hamburger;
    public static Item congee;
    public static Item boiledFish;
    public static Item appleCandy;
    public static Item creamyCorn;
    public static Item chocolateBanana;
    public static Item fries;
    public static Item mapoTofu;
    public static Item cannedOranges;
    public static Item strawberryIcecream;
    public static Item honeyTea;
    public static Item pineappleCanday;
    public static Item cornChips;
    public static Item melonIcecream;
    public static Item melonIcebar;
    public static Item roastedLamb;
    public static Item nigiri;
    public static Item udon;

    // element shards
    public static ItemElementShard fireShard;
    public static ItemElementShard waterShard;
    public static ItemElementShard natureShard;
    public static ItemElementShard darkShard;
    public static ItemElementShard lightShard;

    private final static String REGISTRATION_DEFAULT = "registration_default";
    private final static String REGISTRATION_UNLOCALIZED_NAME = "registration_unlocalized_name";
    private final static String REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB = "registration_unlocalized_name_creative_tab";

    public static void init() {
        // books
        registerItem(ModItems.REGISTRATION_DEFAULT, treasureHuntBookI = new ItemTreasureBook(1),
                null, "treasure_hunt_book_0");
        registerItem(ModItems.REGISTRATION_DEFAULT, treasureHuntBookII = new ItemTreasureBook(2),
                null, "treasure_hunt_book_1");
        registerItem(ModItems.REGISTRATION_DEFAULT, treasureHuntBookIII = new ItemTreasureBook(3),
                null, "treasure_hunt_book_2");
        registerItem(ModItems.REGISTRATION_DEFAULT, villageBook = new ItemVillageBook(),
                null, "village_book");

        registerItem(ModItems.REGISTRATION_DEFAULT, resetScroll = new ItemResetScroll(),
                null, "reset_scroll");
        registerItem(ModItems.REGISTRATION_DEFAULT, dismissalScroll = new ItemDismissalScroll(),
                null, "dismissal_scroll");

        // shards
        registerItem(ModItems.REGISTRATION_DEFAULT, fireShard = new ItemElementShard("fire"),
                null, "fire_shard");
        registerItem(ModItems.REGISTRATION_DEFAULT, waterShard = new ItemElementShard("water"),
                null, "water_shard");
        registerItem(ModItems.REGISTRATION_DEFAULT, natureShard = new ItemElementShard("nature"),
                null, "nature_shard");
        registerItem(ModItems.REGISTRATION_DEFAULT, darkShard = new ItemElementShard("dark"),
                null, "dark_shard");
        registerItem(ModItems.REGISTRATION_DEFAULT, lightShard = new ItemElementShard("light"),
                null, "light_shard");

        // coins
        registerItem(ModItems.REGISTRATION_DEFAULT, bronzeCoin = new ItemCoin("bronze"),
                null, "bronze_coin");
        registerItem(ModItems.REGISTRATION_DEFAULT, silverCoin = new ItemCoin("silver"),
                null, "silver_coin");
        registerItem(ModItems.REGISTRATION_DEFAULT, goldCoin = new ItemCoin("gold"),
                null, "gold_coin");

        // mail
        registerItem(ModItems.REGISTRATION_DEFAULT, mail = new ItemMail(),
                null, "mail");
        registerItem(ModItems.REGISTRATION_DEFAULT, invitation = new ItemInvitation(),
                null,"invitation");

        // painting
        registerItem(ModItems.REGISTRATION_DEFAULT, painting = new ItemPainting(),
                null, "painting");

        // weapon
        registerItem(ModItems.REGISTRATION_DEFAULT, staff = new ItemStaff(),
                null, "staff");
        registerItem(ModItems.REGISTRATION_DEFAULT, fireStaff = new ItemFireStaff(),
                null, "fire_staff");
        registerItem(ModItems.REGISTRATION_DEFAULT, waterStaff = new ItemWaterStaff(),
                null, "water_staff");
        registerItem(ModItems.REGISTRATION_DEFAULT, natureStaff = new ItemNatureStaff(),
                null, "nature_staff");
        registerItem(ModItems.REGISTRATION_DEFAULT, lightStaff = new ItemLightStaff(),
                null, "light_staff");
        registerItem(ModItems.REGISTRATION_DEFAULT, darkStaff = new ItemDarkStaff(),
                null, "dark_staff");

        registerItem(ModItems.REGISTRATION_DEFAULT, efficientBow = new ItemEfficientBow(),
                null, "bow");

        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                steelSword = (new ItemSword(ModItems.ToolMaterials.STEEL)),
                "steelSword", "steel_sword"); ;

        registerItem(ModItems.REGISTRATION_DEFAULT, fireSword = new ItemFireSword(),
                null, "fire_sword");
        registerItem(ModItems.REGISTRATION_DEFAULT, waterSword = new ItemWaterSword(),
                null, "water_sword");
        registerItem(ModItems.REGISTRATION_DEFAULT, natureSword = new ItemNatureSword(),
                null, "nature_sword");
        registerItem(ModItems.REGISTRATION_DEFAULT, darkSword = new ItemDarkSword(),
                null, "dark_sword");
        registerItem(ModItems.REGISTRATION_DEFAULT, lightSword = new ItemLightSword(),
                null, "light_sword");

        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                woodenClub = (new ItemSword(ToolMaterial.WOOD)),
                "woodenClub", "wooden_club");

        // armor
        registerItem(ModItems.REGISTRATION_DEFAULT, steelHelmet = new ItemArmor("steelHelmet",
                ModItems.ArmorMaterials.STEEL, 1, EntityEquipmentSlot.HEAD),
                null, "steel_helmet");
        registerItem(ModItems.REGISTRATION_DEFAULT, steelChestplate = new ItemArmor("steelChestplate",
                ModItems.ArmorMaterials.STEEL, 1, EntityEquipmentSlot.CHEST),
                null, "steel_chestplate");
        registerItem(ModItems.REGISTRATION_DEFAULT, steelLeggings = new ItemArmor("steelLeggings",
                ModItems.ArmorMaterials.STEEL, 2, EntityEquipmentSlot.LEGS),
                null,"steel_leggings");
        registerItem(ModItems.REGISTRATION_DEFAULT, steelBoots = new ItemArmor("steelBoots",
                ModItems.ArmorMaterials.STEEL, 1, EntityEquipmentSlot.FEET),
                null, "steel_boots");

        registerItem(ModItems.REGISTRATION_DEFAULT, woodenHelmet = new ItemArmor("woodenHelmet",
                ModItems.ArmorMaterials.WOOD, 1, EntityEquipmentSlot.HEAD),
                null,"wooden_helmet");
        registerItem(ModItems.REGISTRATION_DEFAULT, woodenChestplate = new ItemArmor("woodenChestplate",
                ModItems.ArmorMaterials.WOOD, 1, EntityEquipmentSlot.CHEST),
                null,"wooden_chestplate");
        registerItem(ModItems.REGISTRATION_DEFAULT, woodenLeggings = new ItemArmor("woodenLeggings",
                ModItems.ArmorMaterials.WOOD, 2, EntityEquipmentSlot.LEGS),
                null,"wooden_leggings");
        registerItem(ModItems.REGISTRATION_DEFAULT, woodenBoots = new ItemArmor("woodenBoots",
                ModItems.ArmorMaterials.WOOD, 1, EntityEquipmentSlot.FEET),
                null,"wooden_boots");

        // tools
        // sun stone
        registerItem(ModItems.REGISTRATION_DEFAULT, sunStone = (new ItemWeatherStone(true)),
                null, "sun_stone");
        // rain stone
        registerItem(ModItems.REGISTRATION_DEFAULT, rainStone = (new ItemWeatherStone(false)),
                null, "rain_stone");
        // steel hoe
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                steelHoe = (new ItemHoe(ModItems.ToolMaterials.STEEL)),
                "steelHoe", "steel_hoe");
        // steel axe
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                steelAxe = (new ItemAxe(ModItems.ToolMaterials.STEEL)),
                "steelAxe", "steel_axe");
        // steel pickaxe
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                steelPickaxe = (new ItemPickaxe(ModItems.ToolMaterials.STEEL)),
                "steelPickaxe", "steel_pickaxe");
        // steel shovel
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                steelShovel = (new ItemShovel(ModItems.ToolMaterials.STEEL)),
                "steelShovel", "steel_shovel");

        registerItem(ModItems.REGISTRATION_DEFAULT, steelIngot = new ItemSteelIngot(),
                null, "steel_ingot");

        // materials
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, cabbage = new Item(),
                "cabbage", "cabbage");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, chili = new Item(),
                "chili", "chili");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, corn = new Item(),
                "corn", "corn");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, soybean = new Item(),
                "soybean", "soybean");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, riceplant = new Item(),
                "riceplant", "riceplant");

        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, mayonnaise = new Item(),
                "mayonnaise", "mayonnaise");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, cheese = new Item(),
                "cheese", "cheese");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, ballOfWool = new Item(),
                "ballOfWool", "ball_of_wool");

        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, rice = new Item(),
                "rice", "rice");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, riceFlour = new Item(),
                "riceFlour", "rice_flour");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, wheatFlour = new Item(),
                "wheatFlour", "wheat_flour");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, soySauce = new Item(),
                "soySauce", "soy_sauce");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, cookingOil = new Item(),
                "cookingOil", "cooking_oil");

        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, seaweed = new Item(),
                "seaweed", "seaweed");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB, salt = new Item(),
                "salt", "salt");

        // wines
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                grapeWine = (new ItemDrink(1, 0.0F, 15)),
                "grapeWine", "grape_wine");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                cookingWine = (new ItemDrink(1, 0.0F, 10)),
                "cookingWine", "cooking_wine");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                cocktail = (new ItemDrink(1, 0.0F, 20)),
                "cocktail", "cocktail");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                beer = (new ItemDrink(2, 0.0F, 10)),
                "beer", "beer");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                whisky = (new ItemDrink(1, 0.0F, 25)),
                "whisky", "whisky");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME,
                vodka = (new ItemDrink(1, 0.0F, 25)),
                "vodka", "vodka");

        // food
        // grape
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                grape = (new ItemFood(4, 0.3F, false)),
                "grape", "grape");
        // lemon
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                lemon = (new ItemFood(2, 0.3F, false)),
                "lemon", "lemon");
        // orange
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                orange = (new ItemFood(4, 0.3F, false)),
                "orange", "orange");
        // pineapple
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                pineapple = (new ItemFood(4, 0.3F, false)),
                "pineapple", "pineapple");
        // strawberry
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                strawberry = (new ItemFood(2, 0.3F, false)),
                "strawberry", "strawberry");
        // melon
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                melon = (new ItemFood(4, 0.3F, false)),
                "melon", "melon");
        // banana
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                banana = (new ItemFood(2, 0.3F, false)),
                "banana", "banana");

        // tofu
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                tofu = (new ItemFood(4, 0.3F, false)),
                "tofu", "tofu");
        // boiled egg
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                boiledEgg = new ItemFood(4, 0.3F, false),
                "boiledEgg", "boiled_egg");

        // shrimp
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                shrimp = new ItemFood(4, 0.3F, false),
                "shrimp", "shrimp");
        // crab
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                crab = new ItemFood(4, 0.3F, false),
                "crab", "crab");

        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                porkRamen = new ItemFood(8, 0.8F, false),
                "porkRamen", "pork_ramen");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                beefNoodleSoup = new ItemFood(8, 0.8F, false),
                "beefNoodleSoup", "beef_noodle_soup");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                noodleSoup = new ItemFood(6, 0.8F, false),
                "noodleSoup", "noodle_soup");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                carrotCake = new ItemFood(6, 0.3F, false),
                "carrotCake", "carrot_cake");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                hamburger = new ItemFood(8, 0.8F, false),
                "hamburger", "hamburger");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                congee = new ItemFood(4, 0.8F, false),
                "congee", "congee");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                boiledFish = new ItemFood(6, 0.8F, false),
                "boiledFish", "boiled_fish");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                appleCandy = new ItemFood(4, 0.3F, false),
                "appleCandy", "apple_candy");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                creamyCorn = new ItemFood(4, 0.3F, false),
                "creamyCorn", "creamy_corn");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                chocolateBanana = new ItemFood(4, 0.3F, false),
                "chocolateBanana", "chocolate_banana");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                fries = new ItemFood(4, 0.3F, false),
                "fries", "fries");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                mapoTofu = new ItemFood(8, 0.8F, false),
                "mapoTofu", "mapo_tofu");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                cannedOranges = new ItemFood(4, 0.3F, false),
                "cannedOranges", "canned_oranges");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                strawberryIcecream = new ItemFood(2, 0.3F, false),
                "strawberryIcecream", "strawberry_icecream");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                honeyTea = new ItemFood(2, 0.3F, false),
                "honeyTea", "honey_tea");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                pineappleCanday = new ItemFood(2, 0.3F, false),
                "pineappleCanday", "pineapple_canday");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                cornChips = new ItemFood(4, 0.3F, false),
                "cornChips", "corn_chips");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                melonIcecream = new ItemFood(4, 0.3F, false),
                "melonIcecream", "melon_icecream");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                melonIcebar = new ItemFood(4, 0.3F, false),
                "melonIcebar", "melon_icebar");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                roastedLamb = new ItemFood(6, 0.8F, false),
                "roastedLamb", "roasted_lamb");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                nigiri = new ItemFood(4, 0.8F, false),
                "nigiri", "nigiri");
        registerItem(ModItems.REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB,
                udon = new ItemFood(6, 0.8F, false),
                "udon", "udon");
    }

    public static void registerItem(String registrationFlag, Item item, @Nullable String path, String name) {
        switch (registrationFlag) {
            case REGISTRATION_DEFAULT :
                GameRegistry.register(item,
                        new ResourceLocation(VillageBoxMod.MOD_ID, name));
            case REGISTRATION_UNLOCALIZED_NAME :
                if (path != null) {
                    GameRegistry.register(item.setUnlocalizedName(PathHelper.full(path)),
                            new ResourceLocation(VillageBoxMod.MOD_ID, name));
                }
            case REGISTRATION_UNLOCALIZED_NAME_CREATIVE_TAB:
                if (path != null) {
                    GameRegistry.register(item.setUnlocalizedName(PathHelper.full(path)).setCreativeTab(ModItems.tabVB),
                            new ResourceLocation(VillageBoxMod.MOD_ID, name));
                }
        }
    }

    public static class ToolMaterials {
        public static ToolMaterial STEEL = EnumHelper.addToolMaterial("VB:STEEL",
                2, 500, 7.0F, 3.0F, 15);
        public static ToolMaterial RUNESTEEL = EnumHelper.addToolMaterial("VB:RUNESTEEL",
                3, 1000, 8.0F, 3.0F, 18);
    }

    public static class ArmorMaterials {
        public static ArmorMaterial WOOD = EnumHelper.addArmorMaterial("VB:WOOD",
                PathHelper.full("wooden"), 7, new int[]{2, 4, 3, 2}, 10,
                SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        public static ArmorMaterial STEEL = EnumHelper.addArmorMaterial("VB:STEEL",
                PathHelper.full("steel"), 15, new int[]{3, 7, 6, 3}, 12,
                SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
        public static ArmorMaterial RUNESTEEL = EnumHelper.addArmorMaterial("VB:RUNESTEEL",
                PathHelper.full("steel"), 30, new int[]{3, 8, 7, 3}, 15,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    }
}
