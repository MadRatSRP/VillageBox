package ckhbox.villagebento.common.entity.throwable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFlameBall extends EntityThrowable
{
    public EntityFlameBall(World worldIn)
    {
        super(worldIn);
    }

    public EntityFlameBall(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityFlameBall(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition movingObject)
    {
    	if (!this.worldObj.isRemote)
        {
            if (movingObject.entityHit != null)
            {
                boolean flag = movingObject.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);

                if (flag)
                {
                    this.applyEnchantments(this.getThrower(), movingObject.entityHit);

                    if (!movingObject.entityHit.isImmuneToFire())
                    {
                        movingObject.entityHit.setFire(5);
                    }
                }
            }
            else
            {
                BlockPos blockpos = movingObject.getBlockPos().offset(movingObject.sideHit);

                if (this.worldObj.isAirBlock(blockpos))
                {
                    this.worldObj.setBlockState(blockpos, Blocks.fire.getDefaultState());
                }
            }
            
            this.playSound("random.explode", 0.7F, 1.0F);

            this.setDead();
        }
    	
        for (int k = 0; k < 3; ++k)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {Item.getIdFromItem(Items.egg)});
        }
    }

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.worldObj.isRemote){
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			if(this.rand.nextBoolean()){
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}
    
    
}