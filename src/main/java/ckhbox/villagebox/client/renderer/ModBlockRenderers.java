//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.renderer;

import ckhbox.villagebox.common.block.ModBlocks;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModBlockRenderers {
	public static void init(){
		registerModel(ModBlocks.mailbox, "mailbox");
		//registerModel(ModBlocks.alchemyPot, 0, "alchemypot");
		//registerModel(ModBlocks.magicTrashCan, 0, "magic_trash_can");
		registerModel(ModBlocks.buildboxSmall, "buildbox_small");
		registerModel(ModBlocks.buildboxMedium, "buildbox_medium");
		registerModel(ModBlocks.buildboxLarge, "buildbox_large");
		registerModel(ModBlocks.buildboxExLarge, "buildbox_exlarge");
		
		registerModel(ModBlocks.fireTotem, "fire_totem");
		registerModel(ModBlocks.waterTotem, "water_totem");
		registerModel(ModBlocks.natureTotem, "nature_totem");
		registerModel(ModBlocks.totempole0, "totempole_0");
		registerModel(ModBlocks.totempole1, "totempole_1");
		registerModel(ModBlocks.totempole2, "totempole_2");

		
		registerModel(ModBlocks.tableOak, "table_oak");
		registerModel(ModBlocks.tableJungle, "table_jungle");
		registerModel(ModBlocks.tableBirch, "table_birch");
		registerModel(ModBlocks.tableSpruce, "table_spruce");
		registerModel(ModBlocks.tableAcacia, "table_acacia");
		registerModel(ModBlocks.tableDarkOak, "table_big_oak");
		
		registerModel(ModBlocks.chairOak, "chair_oak");
		registerModel(ModBlocks.chairJungle, "chair_jungle");
		registerModel(ModBlocks.chairBirch, "chair_birch");
		registerModel(ModBlocks.chairSpruce, "chair_spruce");
		registerModel(ModBlocks.chairAcacia, "chair_acacia");
		registerModel(ModBlocks.chairDarkOak, "chair_big_oak");
		
		registerModel(ModBlocks.benchOak, "bench_oak");
		registerModel(ModBlocks.benchJungle, "bench_jungle");
		registerModel(ModBlocks.benchBirch, "bench_birch");
		registerModel(ModBlocks.benchSpruce, "bench_spruce");
		registerModel(ModBlocks.benchAcacia, "bench_acacia");
		registerModel(ModBlocks.benchDarkOak, "bench_big_oak");
		
		registerModel(ModBlocks.carpet0, "carpet_0");
		registerModel(ModBlocks.carpet1, "carpet_1");
		registerModel(ModBlocks.carpet2, "carpet_2");
		registerModel(ModBlocks.carpet3, "carpet_3");
		registerModel(ModBlocks.carpet4, "carpet_4");
		registerModel(ModBlocks.carpet5, "carpet_5");
		registerModel(ModBlocks.carpet6, "carpet_6");
		registerModel(ModBlocks.carpet7, "carpet_7");
		registerModel(ModBlocks.carpet8, "carpet_8");
		registerModel(ModBlocks.carpet9, "carpet_9");
		registerModel(ModBlocks.carpet10, "carpet_10");
		registerModel(ModBlocks.carpet11, "carpet_11");
		registerModel(ModBlocks.carpet12, "carpet_12");
		registerModel(ModBlocks.carpet13, "carpet_13");
		registerModel(ModBlocks.carpet14, "carpet_14");
		registerModel(ModBlocks.carpet15, "carpet_15");
		registerModel(ModBlocks.carpet16, "carpet_16");
		registerModel(ModBlocks.carpet17, "carpet_17");
		registerModel(ModBlocks.carpet18, "carpet_18");
		registerModel(ModBlocks.carpet19, "carpet_19");
		registerModel(ModBlocks.carpet20, "carpet_20");
		registerModel(ModBlocks.carpet21, "carpet_21");
		registerModel(ModBlocks.carpet22, "carpet_22");
		registerModel(ModBlocks.carpet23, "carpet_23");
		registerModel(ModBlocks.carpet24, "carpet_24");
		registerModel(ModBlocks.carpet25, "carpet_25");
		registerModel(ModBlocks.carpet26, "carpet_26");
		registerModel(ModBlocks.carpet27, "carpet_27");
		registerModel(ModBlocks.carpet28, "carpet_28");
		registerModel(ModBlocks.carpet29, "carpet_29");
		registerModel(ModBlocks.carpet30, "carpet_30");
		registerModel(ModBlocks.carpet31, "carpet_31");
		registerModel(ModBlocks.carpetWool0, "carpet_wool_0");
		registerModel(ModBlocks.carpetWool1, "carpet_wool_1");
		registerModel(ModBlocks.carpetWool2, "carpet_wool_2");
		registerModel(ModBlocks.carpetWool3, "carpet_wool_3");
		registerModel(ModBlocks.carpetWool4, "carpet_wool_4");
		registerModel(ModBlocks.carpetWool5, "carpet_wool_5");
		registerModel(ModBlocks.carpetWool6, "carpet_wool_6");
		registerModel(ModBlocks.carpetWool7, "carpet_wool_7");
		registerModel(ModBlocks.carpetWool8, "carpet_wool_8");
		registerModel(ModBlocks.carpetWool9, "carpet_wool_9");
		registerModel(ModBlocks.carpetWool10, "carpet_wool_10");
		registerModel(ModBlocks.carpetWool11, "carpet_wool_11");
		registerModel(ModBlocks.carpetWool12, "carpet_wool_12");
		registerModel(ModBlocks.carpetWool13, "carpet_wool_13");
		registerModel(ModBlocks.carpetWool14, "carpet_wool_14");
		registerModel(ModBlocks.carpetWool15, "carpet_wool_15");
		
		//flowers
		registerModel(ModBlocks.flowerRedRose, "flower_redrose");
		registerModel(ModBlocks.flowerPlumBlossom, "flower_plumblossom");
		registerModel(ModBlocks.flowerHydrangeas, "flower_hydrangeas");
		registerModel(ModBlocks.flowerGardenia, "flower_gardenia");
		registerModel(ModBlocks.flowerHeartMushroom, "flower_heartmushroom");
		registerModel(ModBlocks.flowerRanunculus, "flower_ranunculus");
		registerModel(ModBlocks.flowerRosySpiraea, "flower_rosyspiraea");
		registerModel(ModBlocks.bonsai, "bonsai");
	}
	
	private static void registerModel(Block block, String resource){
		registerModel(Item.getItemFromBlock(block), resource);
	}
	
	private static void registerModel(Item item, String resource){
		Minecraft
                .getMinecraft()
                .getRenderItem()
                .getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(PathHelper.full(resource), "inventory"));
	}
}
