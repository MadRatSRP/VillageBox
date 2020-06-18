//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.entity.painting;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.BufferHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPainting extends EntityHanging implements IEntityAdditionalSpawnData
{
	public EntityPainting.EnumArt art;

    public EntityPainting(World worldIn)
    {
        super(worldIn);
    }

    public EntityPainting(World worldIn, BlockPos pos, EnumFacing facing)
    {
        super(worldIn, pos);
        List<EntityPainting.EnumArt> list = Lists.<EntityPainting.EnumArt>newArrayList();

        for (EntityPainting.EnumArt entitypainting$enumart : EntityPainting.EnumArt.values())
        {
            this.art = entitypainting$enumart;
            this.updateFacingWithBoundingBox(facing);

            if (this.onValidSurface())
            {
                list.add(entitypainting$enumart);
            }
        }

        if (!list.isEmpty())
        {
            this.art = (EntityPainting.EnumArt)list.get(this.rand.nextInt(list.size()));
        }

        this.updateFacingWithBoundingBox(facing);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setString("Motive", this.art.title);
        super.writeEntityToNBT(tagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        String s = tagCompund.getString("Motive");

        for (EntityPainting.EnumArt entitypainting$enumart : EntityPainting.EnumArt.values())
        {
            if (entitypainting$enumart.title.equals(s))
            {
                this.art = entitypainting$enumart;
            }
        }

        if (this.art == null)
        {
            this.art = EntityPainting.EnumArt.TREE;
        }

        super.readEntityFromNBT(tagCompund);
    }

    public int getWidthPixels()
    {
        return this.art.sizeX;
    }

    public int getHeightPixels()
    {
        return this.art.sizeY;
    }
    
    
    //readSpawnData & writeSpawnData basically are copies of the associated functions in SPacketSpawnPainting class
    
    @Override
	public void writeSpawnData(ByteBuf buffer) {
    	BufferHelper.writeUuid(buffer, this.getUniqueID());
		ByteBufUtils.writeUTF8String(buffer, this.art.title);
        buffer.writeInt(this.getHangingPosition().getX());
        buffer.writeInt(this.getHangingPosition().getY());
        buffer.writeInt(this.getHangingPosition().getZ());
        buffer.writeByte(this.facingDirection.getHorizontalIndex());
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {
		UUID uuid = BufferHelper.readUuid(buffer);
		String title = ByteBufUtils.readUTF8String(buffer);		   
        BlockPos pos = new BlockPos(buffer.readInt(), buffer.readInt(), buffer.readInt());      
        EnumFacing facing = EnumFacing.getHorizontal((buffer.readUnsignedByte()));
        
        this.hangingPosition = pos;
        
        for (EntityPainting.EnumArt entitypainting$enumart : EntityPainting.EnumArt.values())		
        {		
        	if (entitypainting$enumart.title.equals(title))		
        	{		
        		this.art = entitypainting$enumart;
        		break;		
        	}		
        }		

        this.updateFacingWithBoundingBox(facing);
        
        this.setUniqueId(uuid);
	}

    /**
     * Called when this entity is broken. Entity parameter may be null.
     */
    public void onBroken(Entity brokenEntity)
    {
        if (this.worldObj.getGameRules().getBoolean("doEntityDrops"))
        {
            this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);

            if (brokenEntity instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)brokenEntity;

                if (entityplayer.capabilities.isCreativeMode)
                {
                    return;
                }
            }

            this.entityDropItem(new ItemStack(ModItems.painting), 0.0F);
        }
    }

    public void playPlaceSound()
    {
        this.playSound(SoundEvents.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
    }

    /**
     * Sets the location and Yaw/Pitch of an entity in the world
     */
    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch)
    {
    	BlockPos blockpos = this.hangingPosition.add(x - this.posX, y - this.posY, z - this.posZ);
        this.setPosition((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
    {
        BlockPos blockpos = this.hangingPosition.add(x - this.posX, y - this.posY, z - this.posZ);
        this.setPosition((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
    }

    public static enum EnumArt
    {
    	TREE("Tree", 16, 16, 0, 0),
        TAVERN("Tavern", 16, 16, 16, 0),
        CHURCH("Church", 16, 16, 32, 0),
        MARKET("Matket", 16, 16, 48, 0),
        FURNACE("Furnace", 16, 16, 64, 0),
        HOUSE("House", 16, 16, 80, 0),
        FLOWER("Flower", 16, 16, 96, 0),
        SCENE("Scene", 32, 16, 0, 32),
        TTP("ttp", 32, 16, 32, 32),
        SUNSET("Sunset", 32, 16, 64, 32),
        FLOWER2("Flower2", 32, 16, 96, 32),
        SHElL("Shell", 32, 16, 128, 32),
        DARKNESS("Darkness", 16, 32, 0, 64),
        SUMMER("Summer", 16, 32, 16, 64),
        GIRL("Girl", 64, 32, 0, 96),
        MOUNTAIN("Mountain", 64, 32, 0, 96),
        POISON("Poison", 32, 32, 0, 128),
        BEAR("Bear", 32, 32, 32, 128),
        SCENE2("Scene2", 32, 32, 64, 128),
        MOONGATE("Moongate", 32, 32, 96, 128),
        NIGHT("Night", 32, 32, 128, 128),
        PARTY("Party", 32, 32, 160, 128),
        ZEN("Zen", 64, 64, 0, 192),
        CLONE("Clone", 64, 64, 64, 192),
        CASTLE("Castle", 64, 64, 128, 192),
        MARKET2("Market2", 64, 48, 192, 64),
        CHURCH2("Church2", 64, 48, 192, 112),
        HOUSE2("House2", 64, 48, 192, 160),
        TREE2("Tree2", 64, 48, 192, 208);

        public static final int MAX_NAME_LENGTH = "SkullAndRoses".length();
        /** Painting Title. */
        public final String title;
        public final int sizeX;
        public final int sizeY;
        public final int offsetX;
        public final int offsetY;

        private EnumArt(String titleIn, int width, int height, int textureU, int textureV)
        {
            this.title = titleIn;
            this.sizeX = width;
            this.sizeY = height;
            this.offsetX = textureU;
            this.offsetY = textureV;
        }
    }
}
