//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.event;

import ckhbox.villagebox.common.config.VBConfig;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.math.Rand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventCoinsFound {
    private void dropCoins(int num, World world, double x, double y, double z) {

        while (num > 0) {
            int count = Math.min(num, 64);
            num -= count;
            EntityItem entityitem = new EntityItem(world, x, y + 0.5F, z, new ItemStack(ModItems.bronzeCoin, count));
            entityitem.setDefaultPickupDelay();
            world.spawnEntityInWorld(entityitem);
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if (!event.getEntityLiving().worldObj.isRemote &&
            event.getEntityLiving() instanceof EntityMob &&
            event.getSource().getSourceOfDamage() instanceof EntityPlayer) {
            if (VBConfig.killMobsDropCoins) {
                int l = ExtendedPlayerProperties.get((EntityPlayer) event.getSource()
                    .getSourceOfDamage()).treasureHuntLevel;
                int base = l * 2 + 1;
                int add = l + 3;
                dropCoins(Rand.get().nextInt(add) + base, event.getEntityLiving().worldObj,
                    event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ);
            }
        }
    }

    @SubscribeEvent
    public void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        if (!event.getWorld().isRemote && event.getHarvester() != null) {
            if (Rand.get().nextInt(5) == 0 && VBConfig.destroyBlocksDropCoins) {
                //int l = ExtendedPlayerProperties.get(event.getHarvester()).treasureHuntLevel;
                // int base = l + 1;
                // int add = l * 2;

                dropCoins(Rand.get().nextInt(3), event.getWorld(), event.getPos().getX() + 0.5D,
                    event.getPos().getY() + 0.5D, event.getPos().getZ() + 0.5D);
            }
        }
    }
}
