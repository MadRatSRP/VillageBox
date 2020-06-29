//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.weapon;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemLightSword
    extends ItemSword {

    public ItemLightSword() {
        super(ModItems.ToolMaterials.STEEL);

        this.setUnlocalizedName(PathHelper.full("lightSword"));

        this.setCreativeTab(ModItems.tabVB);
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack, EntityLivingBase target,
                             @Nonnull EntityLivingBase attacker) {
        if (!target.worldObj.isRemote) {
            Class<?> classInstance = target.getClass();

            if (classInstance == EntityZombie.class || classInstance == EntitySkeleton.class) {
                // one hit kill
                target.setHealth(0);
            }
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                               @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);

        String info = I18n.format(PathHelper.full("info.item.lightSword"));

        tooltip.add(info);
    }
}
