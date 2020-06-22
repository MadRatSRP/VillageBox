//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page.data;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.village.profession.Profession;
import ckhbox.villagebox.common.village.trading.TradingRecipe;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VillageBookData{
	public List<ItemStack> listOfItemStacks = new ArrayList<>();

	public List<Profession> listOfProfessions = new ArrayList<>();

	public HashMap<String,ItemStack> mapNamesToItemStacks = new HashMap<>();
	
	public VillageBookData(){
		this.generate();
	}
	
	public void generate(){
		// items
		this.listOfItemStacks.clear();

		for (Item item : Item.REGISTRY){
            if (item == null) {
                continue;
            }

            for (CreativeTabs tab : item.getCreativeTabs()) {
                if (tab == ModItems.tabVB) {
                	item.getSubItems(item, ModItems.tabVB, this.listOfItemStacks);
                }
            }
        }

		this.mapNamesToItemStacks.clear();

		for (ItemStack itemstack : this.listOfItemStacks) {
			this.mapNamesToItemStacks.put(itemstack.getUnlocalizedName(), itemstack);
		}
		
		// professions
		this.listOfProfessions = Profession.registry.getAll();
	}
	
	public List<Profession> findRelatedProByItem(ItemStack itemstack){
		List<Profession> list = new ArrayList<>();

		for (Profession profession : this.listOfProfessions) {
			for (TradingRecipe recipe : profession.getTradingRecipeList()) {
				if (recipe.getItemOutput().isItemEqual(itemstack)) {
					list.add(profession);

					break;
				}
			}
		}
		return list;
	}
}
