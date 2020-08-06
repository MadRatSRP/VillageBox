//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.quest;

import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Quest {
    private final ItemStack[] required;

    private final ItemStack[] rewards;

    public Quest(ItemStack[] required, ItemStack[] rewards) {
        this.required = required;

        this.rewards = rewards;
    }

    public ItemStack[] getRequirements() {
        return this.required;
    }

    public ItemStack[] getRewards() {
        return this.rewards;
    }

    @SideOnly(Side.CLIENT)
    public String getText(EntityPlayer player) {
        return I18n.format(PathHelper.full("quest.text"), player.getName());
    }

    public boolean canComplete(EntityPlayer player) {
        if (required == null)
            return true;
        for (ItemStack itemStack : required) {
            if (this.getItemNum(itemStack, player) < itemStack.stackSize) {
                return false;
            }
        }
        return true;
    }

    public boolean complete(EntityPlayer player) {
        if (!canComplete(player))
            return false;

        for (ItemStack itemStack : required) {
            this.consumeItems(itemStack, player);
        }

        if (rewards != null) {
            for (ItemStack itemStack : rewards) {
                ItemStack reward = itemStack.copy();

                if (!player.inventory.addItemStackToInventory(reward)) {
                    player.dropItem(reward, false);
                }
            }
        }

        // exp
        int exp = 15;

        while (exp > 0) {
            int i = EntityXPOrb.getXPSplit(exp);

            exp -= i;

            player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj,
                    player.posX, player.posY + 0.5D, player.posZ, i));
        }

        return true;
    }

    public int getItemNum(ItemStack itemstack, EntityPlayer player) {
        ItemStack[] stacks = player.inventory.mainInventory;

        int count = 0;

        for (ItemStack stack : stacks) {
            if (stack != null && stack.isItemEqual(itemstack)) {
                count += stack.stackSize;
            }
        }

        return count;
    }

    private void consumeItems(ItemStack itemstack, EntityPlayer player) {
        ItemStack[] stacks = player.inventory.mainInventory;

        for (int i = 0; i < stacks.length; ++i) {
            if (stacks[i] != null && stacks[i].isItemEqual(itemstack)) {
                if (stacks[i].stackSize >= itemstack.stackSize) {
                    stacks[i].stackSize -= itemstack.stackSize;

                    if (stacks[i].stackSize == 0)
                        stacks[i] = null;
                    break;
                } else {
                    itemstack.stackSize -= stacks[i].stackSize;

                    stacks[i] = null;
                }
            }
        }
    }
}
