//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.weapon;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public class ItemNatureSword extends ItemSword {

    public ItemNatureSword() {
        super(ModItems.ToolMaterials.STEEL);
        this.setUnlocalizedName(PathHelper.full("natureSword"));
        this.setCreativeTab(ModItems.tabVB);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!target.worldObj.isRemote) {
            if (target instanceof EntityMob) {
                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 160, 2));
                target.addPotionEffect(new PotionEffect(MobEffects.POISON, 160, 2));
            }
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        String info = I18n.format(PathHelper.full("info.item.natureSword"));
        tooltip.add(info);
    }
}
