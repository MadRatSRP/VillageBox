//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.weapon;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemWaterSword
    extends ItemSword {

    public ItemWaterSword() {
        super(ModItems.ToolMaterials.STEEL);

        this.setUnlocalizedName(PathHelper.full("waterSword"));

        this.setCreativeTab(ModItems.tabVB);
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn,
                         @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;

            if (player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack) {
                if (!player.isPotionActive(MobEffects.WATER_BREATHING)) {
                    player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 40));
                }
                if (!player.isPotionActive(MobEffects.SPEED)) {
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, 1));
                }
            }
        }
    }


    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                               @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);

        String info = I18n.format(PathHelper.full("info.item.waterSword"));

        tooltip.add(info);
    }
}
