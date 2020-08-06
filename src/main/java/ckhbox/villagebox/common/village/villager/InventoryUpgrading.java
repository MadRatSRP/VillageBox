//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.villager;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.util.helper.ItemStackHelper;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class InventoryUpgrading implements IInventory {
    private final ItemStack[] inventoryItems = new ItemStack[3];

    private final EntityVillager villager;

    private final EntityPlayer player;

    private int currentUpgradeOptionIndex;

    private Profession currentUpgradeOption;

    private boolean canUpgrade = false;

    public InventoryUpgrading(EntityPlayer player, EntityVillager villager) {
        this.villager = villager;

        this.player = player;

        this.resetUpgradeOptionAndSlots();
    }

    @Nonnull
    @Override
    public String getName() {
        return "villagebox.upgrading";
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
        if (this.inventoryItems[index] != null) {
            ItemStack take;

            if (this.inventoryItems[index].stackSize <= count) {
                take = this.inventoryItems[index];

                this.inventoryItems[index] = null;

                //System.out.println("decrStackSize(not empty)");
            } else {
                take = this.inventoryItems[index].splitStack(count);

                if (this.inventoryItems[index].stackSize == 0) {
                    this.inventoryItems[index] = null;
                }
            }
            this.resetUpgradeOptionAndSlots();

            return take;
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

        this.resetUpgradeOptionAndSlots();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        this.resetUpgradeOptionAndSlots();
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

    public boolean canUpgrade() {
        return this.canUpgrade;
    }

    public void upgrade() {
        if (this.canUpgrade()) {
            if (ItemStackHelper.consume(this.inventoryItems, this.currentUpgradeOption.getUpgradeToCurentNeeds(), 3)) {
                this.villager.upgrade(this.currentUpgradeOption.getRegID());

                this.player.closeScreen();
            }
        }
    }

    public void resetUpgradeOptionAndSlots() {
        Profession[] options = this.villager.getProfession().getUpgradeToNextOptions();

        this.currentUpgradeOption = options == null ? null : options[this.currentUpgradeOptionIndex];

        this.canUpgrade = false;

        if (this.currentUpgradeOption != null) {
            if (ItemStackHelper.match(this.inventoryItems, this.currentUpgradeOption.getUpgradeToCurentNeeds(), 3)) {
                this.canUpgrade = true;
            }
        }
    }

    public void setCurrentUpgradeOptionIndex(int currentUpgradeOptionIndex) {
        this.currentUpgradeOptionIndex = currentUpgradeOptionIndex;

        this.resetUpgradeOptionAndSlots();
    }
}
