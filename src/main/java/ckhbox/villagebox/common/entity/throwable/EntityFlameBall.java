//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.entity.throwable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityFlameBall extends EntityThrowable {
    /*public EntityFlameBall(World worldIn) {
        super(worldIn);
    }*/

    public EntityFlameBall(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    /*public EntityFlameBall(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }*/

    protected void onImpact(@Nonnull RayTraceResult result) {
        if (!this.worldObj.isRemote) {
            if (result.entityHit != null) {
                @Nullable Boolean flag = null;
                @Nullable final EntityLivingBase thrower = this.getThrower();

                if (thrower != null) {
                    flag = result.entityHit.attackEntityFrom(
                        DamageSource.causeThrownDamage(this,
                            thrower), 5.0F);
                }

                if (flag != null && flag) {
                    this.applyEnchantments(thrower, result.entityHit);

                    if (!result.entityHit.isImmuneToFire()) {
                        result.entityHit.setFire(5);
                    }
                }
            } else {
                BlockPos blockpos = result.getBlockPos().offset(result.sideHit);

                if (this.worldObj.isAirBlock(blockpos)) {
                    this.worldObj.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                }
            }

            this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 0.7F, 1.0F);

            this.setDead();
        }

        for (int k = 0; k < 3; ++k) {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ,
                ((double) this.rand.nextFloat() - 0.5D) * 0.08D,
                ((double) this.rand.nextFloat() - 0.5D) * 0.08D,
                ((double) this.rand.nextFloat() - 0.5D) * 0.08D,
                Item.getIdFromItem(Items.EGG));
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.worldObj.isRemote) {
            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D,
                this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);

            if (this.rand.nextBoolean()) {
                this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.5D,
                    this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }


}