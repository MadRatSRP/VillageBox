//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.common;

import ckhbox.villagebox.common.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDrink
    extends ItemFood {

    private final int drunkTicks;

    public ItemDrink(int amount, float saturation, int drunkTime) {
        super(amount, saturation, false);

        this.setCreativeTab(ModItems.tabVB);

        this.drunkTicks = drunkTime * 20;

        this.setAlwaysEdible();
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    protected void onFoodEaten(@Nonnull ItemStack stack,
                               @Nonnull World worldIn, @Nonnull EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);

        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, this.drunkTicks));
    }
}
