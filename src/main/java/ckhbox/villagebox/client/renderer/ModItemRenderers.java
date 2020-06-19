//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.renderer;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModItemRenderers {
	
	public static void init(){
		
		registerModel(ModItems.treasureHuntBookI, "treasure_hunt_book_0");
		registerModel(ModItems.treasureHuntBookII, "treasure_hunt_book_1");
		registerModel(ModItems.treasureHuntBookIII, "treasure_hunt_book_2");
		registerModel(ModItems.villageBook, "village_book");
		registerModel(ModItems.bronzeCoin, "bronze_coin");
		registerModel(ModItems.silverCoin, "silver_coin");
		registerModel(ModItems.goldCoin, "gold_coin");
		registerModel(ModItems.mail, "mail");
		registerModel(ModItems.invitation, "invitation");
		registerModel(ModItems.staff, "staff");
		registerModel(ModItems.fireStaff, "fire_staff");
		registerModel(ModItems.waterStaff, "water_staff");
		registerModel(ModItems.natureStaff, "nature_staff");
		registerModel(ModItems.lightStaff, "light_staff");
		registerModel(ModItems.darkStaff, "dark_staff");
		registerModel(ModItems.resetScroll, "reset_scroll");
		registerModel(ModItems.dismissalScroll, "dismissal_scroll");
		
		registerModel(ModItems.fireShard, "fire_shard");
		registerModel(ModItems.waterShard, "water_shard");
		registerModel(ModItems.natureShard, "nature_shard");
		registerModel(ModItems.lightShard, "light_shard");
		registerModel(ModItems.darkShard, "dark_shard");
		
		registerModel(ModItems.steelHoe, "steel_hoe");
		registerModel(ModItems.steelAxe, "steel_axe");
		registerModel(ModItems.steelPickaxe, "steel_pickaxe");
		registerModel(ModItems.steelShovel, "steel_shovel");
		
		registerModel(ModItems.steelSword, "steel_sword");
		registerModel(ModItems.fireSword, "fire_sword");
		registerModel(ModItems.waterSword, "water_sword");
		registerModel(ModItems.natureSword, "nature_sword");
		registerModel(ModItems.darkSword, "dark_sword");
		registerModel(ModItems.lightSword, "light_sword");
		
		registerModel(ModItems.steelHelmet, "steel_helmet");
		registerModel(ModItems.steelChestplate, "steel_chestplate");
		registerModel(ModItems.steelLeggings, "steel_leggings");
		registerModel(ModItems.steelBoots, "steel_boots");
		
		registerModel(ModItems.woodenHelmet, "wooden_helmet");
		registerModel(ModItems.woodenChestplate, "wooden_chestplate");
		registerModel(ModItems.woodenLeggings, "wooden_leggings");
		registerModel(ModItems.woodenBoots, "wooden_boots");
		
		registerModel(ModItems.woodenClub, "wooden_club");
		
		registerModel(ModItems.steelIngot, "steel_ingot");
		
		registerModel(ModItems.efficientBow, "bow");
		
		registerModel(ModItems.painting, "painting");
		
		registerModel(ModItems.sunStone, "sun_stone");
		registerModel(ModItems.rainStone, "rain_stone");
		
		registerModel(ModItems.cabbage, "cabbage");
		registerModel(ModItems.riceplant, "riceplant");
		registerModel(ModItems.chili, "chili");
		registerModel(ModItems.corn, "corn");
		registerModel(ModItems.soybean, "soybean");
		
		registerModel(ModItems.cheese, "cheese");
		registerModel(ModItems.mayonnaise, "mayonnaise");
		registerModel(ModItems.ballOfWool, "ball_of_wool");
		
		registerModel(ModItems.rice, "rice");
		registerModel(ModItems.riceFlour, "rice_flour");
		registerModel(ModItems.wheatFlour, "wheat_flour");
		registerModel(ModItems.cookingOil, "cooking_oil");
		registerModel(ModItems.soySauce, "soy_sauce");
		registerModel(ModItems.tofu, "tofu");
		registerModel(ModItems.boiledEgg, "boiled_egg");
		
		registerModel(ModItems.grapeWine, "grape_wine");
		registerModel(ModItems.cookingWine, "cooking_wine");
		registerModel(ModItems.cocktail, "cocktail");
		registerModel(ModItems.beer, "beer");
		registerModel(ModItems.whisky, "whisky");
		registerModel(ModItems.vodka, "vodka");
		
		registerModel(ModItems.banana, "banana");
		registerModel(ModItems.orange, "orange");
		registerModel(ModItems.melon, "melon");
		registerModel(ModItems.lemon, "lemon");
		registerModel(ModItems.strawberry, "strawberry");
		registerModel(ModItems.pineapple, "pineapple");
		registerModel(ModItems.grape, "grape");
		
		registerModel(ModItems.salt, "salt");
		registerModel(ModItems.seaweed, "seaweed");
		registerModel(ModItems.crab, "crab");
		registerModel(ModItems.shrimp, "shrimp");
		
		registerModel(ModItems.porkRamen, "pork_ramen");
		registerModel(ModItems.beefNoodleSoup, "beef_noodle_soup");
		registerModel(ModItems.noodleSoup, "noodle_soup");
		registerModel(ModItems.carrotCake, "carrot_cake");
		registerModel(ModItems.hamburger, "hamburger");
		registerModel(ModItems.congee, "congee");
		registerModel(ModItems.boiledFish, "boiled_fish");
		registerModel(ModItems.appleCandy, "apple_candy");
		registerModel(ModItems.creamyCorn, "creamy_corn");
		registerModel(ModItems.chocolateBanana, "chocolate_banana");
		registerModel(ModItems.fries, "fries");
		registerModel(ModItems.mapoTofu, "mapo_tofu");
		registerModel(ModItems.cannedOranges, "canned_oranges");
		registerModel(ModItems.strawberryIcecream, "strawberry_icecream");
		registerModel(ModItems.honeyTea, "honey_tea");
		registerModel(ModItems.pineappleCanday, "pineapple_canday");
		registerModel(ModItems.cornChips, "corn_chips");
		registerModel(ModItems.melonIcecream, "melon_icecream");
		registerModel(ModItems.melonIcebar, "melon_icebar");
		registerModel(ModItems.roastedLamb, "roasted_lamb");
		registerModel(ModItems.nigiri, "nigiri");
		registerModel(ModItems.udon, "udon");

	}

	private static void registerModel(Item item, String resource){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(item, 0, new ModelResourceLocation(PathHelper.full(resource), "inventory"));
	}
}
