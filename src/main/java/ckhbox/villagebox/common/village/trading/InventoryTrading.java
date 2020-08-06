//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.trading;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class InventoryTrading implements IInventory {
    // 4 inputs and 1 output
    private final ItemStack[] inventoryItems = new ItemStack[5];

    private final ITrading trader;

    private final EntityPlayer player;

    private int currentRecipeIndex;

    private TradingRecipe currentRecipe;

    public InventoryTrading(EntityPlayer player, ITrading trader) {
        this.trader = trader;

        this.player = player;
    }

    @Nonnull
    @Override
    public String getName() {
        return "villagebox.trading";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return this.inventoryItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return index >= this.getSizeInventory() ? null : this.inventoryItems[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        //System.out.println("decrStackSize:" + index + "," + count);

        if (this.inventoryItems[index] != null) {
            if (index == 4) {
                ItemStack take = this.inventoryItems[index];

                this.inventoryItems[index] = null;

                return take;
            } else if (this.inventoryItems[index].stackSize <= count) {
                ItemStack take = this.inventoryItems[index];

                this.inventoryItems[index] = null;

                if (this.inventoryResetNeededOnSlotChange(index)) {
                    this.resetRecipeAndSlots();
                }

                //System.out.println("decrStackSize(not empty)");

                return take;
            } else {
                ItemStack take = this.inventoryItems[index].splitStack(count);

                if (this.inventoryItems[index].stackSize == 0) {
                    this.inventoryItems[index] = null;
                }

                if (this.inventoryResetNeededOnSlotChange(index)) {
                    this.resetRecipeAndSlots();
                }

                return take;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (this.inventoryItems[index] != null) {
            ItemStack removed = this.inventoryItems[index];

            this.inventoryItems[index] = null;

            return removed;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventoryItems[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (this.inventoryResetNeededOnSlotChange(index)) {
            this.resetRecipeAndSlots();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        this.resetRecipeAndSlots();
    }

    @Override
    public boolean isUseableByPlayer(@Nonnull EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {

    }

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        Arrays.fill(this.inventoryItems, null);
    }

    private boolean inventoryResetNeededOnSlotChange(int index) {
        return index >= 0 && index < 4;
    }

    public void resetRecipeAndSlots() {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            if (this.inventoryItems[i] != null)
                count++;
        }

        this.currentRecipe = this.trader.getTradingRecipeList().get(this.currentRecipeIndex);

        ItemStack output = null;

        if (this.currentRecipe.match(this.inventoryItems)) {
            output = this.currentRecipe.getItemOutput().copy();
        }

        this.setInventorySlotContents(4, output);
    }

    public boolean tradeCurrentRecipe() {
        if (this.currentRecipe != null) {
            return this.currentRecipe.trade(this.inventoryItems);
        }

        return false;
    }

    public void setCurrentRecipeIndex(int currentRecipeIndexIn) {
        this.currentRecipeIndex = currentRecipeIndexIn;

        this.resetRecipeAndSlots();
    }
}
