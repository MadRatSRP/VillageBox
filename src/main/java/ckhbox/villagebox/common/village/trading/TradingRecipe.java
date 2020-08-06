//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.trading;

import ckhbox.villagebox.common.util.helper.ItemStackHelper;
import net.minecraft.item.ItemStack;

public class TradingRecipe {
    private ItemStack[] itemsInput = new ItemStack[4];

    private ItemStack itemOutput;

    public TradingRecipe(ItemStack[] inputs, ItemStack output) {
        this.itemsInput = inputs;

        this.itemOutput = output;
    }

    public ItemStack[] getItemsInput() {
        return this.itemsInput;
    }

    public void setItemsInput(ItemStack[] items) {
        if (items != null) {
            // the part of items passed that is larger than size will be ignored
            int total = Math.min(this.itemsInput.length, items.length);

            System.arraycopy(items, 0, this.itemsInput, 0, total);
        }
    }

    public ItemStack getItemOutput() {
        return this.itemOutput;
    }

    public void setItemOutput(ItemStack item) {
        this.itemOutput = item;
    }

    public boolean match(ItemStack[] items) {
        return ItemStackHelper.match(items, this.itemsInput, 4);
    }

    public boolean trade(ItemStack[] items) {
        return ItemStackHelper.consume(items, this.itemsInput, 4);
    }

}
