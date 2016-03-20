package ckhbox.villagebox.common.entity.villager;

import ckhbox.villagebox.VillageBoxMod;
import ckhbox.villagebox.common.entity.ai.VillagerAIFollowing;
import ckhbox.villagebox.common.entity.ai.VillagerAILookAtInteractPlayer;
import ckhbox.villagebox.common.entity.ai.VillagerAIWander;
import ckhbox.villagebox.common.gui.GuiIDs;
import ckhbox.villagebox.common.util.helper.BitHelper;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.math.IntBoundary;
import ckhbox.villagebox.common.util.math.IntVec3;
import ckhbox.villagebox.common.util.math.Rand;
import ckhbox.villagebox.common.util.tool.HouseDetector;
import ckhbox.villagebox.common.util.tool.NameGenerator;
import ckhbox.villagebox.common.village.home.DataVillage;
import ckhbox.villagebox.common.village.profession.Profession;
import ckhbox.villagebox.common.village.trading.ITrading;
import ckhbox.villagebox.common.village.trading.TradingRecipeList;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVillager extends EntityCreature implements ITrading{

	private Profession profession;
	//the player this villager is currently interacting with
	private EntityPlayer interacting;
	private EntityPlayer following;
	
	private IntBoundary home;
	
	//the canter of wandering when no home has been set to this villager
	private Vec3 wanderCenter;
	
	public EntityVillager(World worldIn){
		this(worldIn, Rand.get().nextBoolean());
	}
	
	public EntityVillager(World worldIn, boolean male){
		this(worldIn, male?NameGenerator.getRandomMaleName():NameGenerator.getRandomFemaleName(), male);
	}
	
	public EntityVillager(World worldIn, String name, boolean male) {
		super(worldIn);		
		
		this.setSize(0.6F, 1.8F);
		
		if(!this.worldObj.isRemote){
			this.setProfession(Rand.get().nextInt(3));
		}
		
		this.setGender(male);
		
		if(!this.hasCustomName()){
			this.setCustomNameTag(name);
		}
		
		this.initAI();
	}
	
	
	
	@Override
	public EntityJumpHelper getJumpHelper() {
		// TODO Auto-generated method stub
		return super.getJumpHelper();
	}



	@Override
	protected float getJumpUpwardsMotion() {
		// TODO Auto-generated method stub
		return super.getJumpUpwardsMotion();
	}

	protected void initAI(){
		((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new VillagerAILookAtInteractPlayer(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new VillagerAIFollowing(this,0.6F));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.3D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new VillagerAIWander(this, 0.4D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }
	
	@Override
	protected void entityInit() {
		super.entityInit();
		//profession id
		this.getDataWatcher().addObject(16, 0);
		//data flags(interacting.following,etc.)
		this.getDataWatcher().addObject(17, 0);
	}

	@Override
	protected boolean interact(EntityPlayer player) {
		
		if(!player.worldObj.isRemote){
			if(	(this.isInteracting() && this.interacting.isEntityAlive() && this.interacting != player) ||
				(this.isFollowing() && this.following.isEntityAlive() && this.following != player)){
				player.addChatMessage(new ChatComponentTranslation(PathHelper.full("message.villager.isbusy")));
			}
			else{
				player.openGui(VillageBoxMod.instance, GuiIDs.VillagerMain, player.worldObj, player.dimension, this.getEntityId(), 0);
			}
		}
		
		return true;
	}

	@Override
	public TradingRecipeList getTradingRecipeList() {
		return this.profession.getTradingRecipeList();
	}
	
	@Override
	public void onTrade() {
	}
	
	public Vec3 getWanderCenter(){
		if(this.wanderCenter == null){
			this.wanderCenter = new Vec3(this.posX, this.posY, this.posZ);
		}
		return this.wanderCenter;
	}
	
	//data flags
	
	/**
	 * POS: 0=Interacting, 1=Following, 2=Has Home
	 */
	protected void setDataFlag(int pos, boolean flag){
		int data = this.getDataWatcher().getWatchableObjectInt(17);
		data = BitHelper.writeBit(data, pos, flag);
		this.getDataWatcher().updateObject(17, data);
	}
	
	/**
	 * POS: 0=Interacting, 1=Following, 2=Has Home, 3 gender
	 */
	protected boolean getDataFlag(int pos){
		int data = this.getDataWatcher().getWatchableObjectInt(17);
		return BitHelper.readBit(data, pos);
	}
	
	//gender
	public void setGender(boolean male){
		this.setDataFlag(3, male);
	}
	
	public boolean isMale(){
		return this.getDataFlag(3);
	}
	
	//interacting and following	
	public void setInteracting(EntityPlayer player){
		if(!this.worldObj.isRemote){
			this.interacting = player;
			this.setDataFlag(0, (this.interacting != null));
		}
	}
	
	public boolean isInteracting(){
		return this.getDataFlag(0);
	}

	public EntityPlayer getInteracting(){
		return this.interacting;
	}
	
	public void setFollowing(EntityPlayer player){
		if(!this.worldObj.isRemote){
			this.following = player;
			this.setDataFlag(1, (this.following != null));
			this.wanderCenter = null;
		}
	}
	
	public boolean isFollowing(){
		return this.getDataFlag(1);
	}	
	
	public EntityPlayer getFollowing(){
		return this.following;
	}
	
	//set home
	public void setCurrentPosAsHome(EntityPlayer player){
		//server side only
		if(this.worldObj.isRemote)
			return;
		
		//scan home boundary
		IntBoundary bound = HouseDetector.getClosedField(this.worldObj, new IntVec3(this.posX,this.posY,this.posZ));
		if(bound == null){
			player.addChatMessage(new ChatComponentTranslation(PathHelper.full("message.villager.home.openspace")));
		}
		else{
			//remove outlines
			bound = bound.extend(-1,0,-1);
			bound.maxy -= 1;
			System.out.println(bound.toString());
			//add home bounday
			String oldOwner = DataVillage.get(this.worldObj).addHome(this.getName(),bound);
			if(oldOwner != null){
				player.addChatMessage(new ChatComponentTranslation(PathHelper.full("message.villager.home.existed"),oldOwner));
			}
			else{
				//remove old home
				if(this.home != null){
					DataVillage.get(this.worldObj).removeHome(home);
				}
				this.home = bound;
				//stop following
				this.setFollowing(null);
				player.addChatMessage(new ChatComponentTranslation(PathHelper.full("message.villager.home.movein"),this.getName()));
				//update data flag
				this.setDataFlag(2, true);
			}
		}
	}
	
	public void setHome(IntBoundary home){
		this.home = home;
		this.setDataFlag(2, true);
	}
	
	public void moveOutHome(EntityPlayer player){
		if(this.home != null){
			DataVillage.get(this.worldObj).removeHome(home);
			this.home = null;
			this.setDataFlag(2, false);
			player.addChatMessage(new ChatComponentTranslation(PathHelper.full("message.villager.home.moveout"),this.getName()));
		}
	}
	
	public IntBoundary getHome(){
		return this.home;
	}
	
	public boolean hasHome(){
		return this.getDataFlag(2);
	}
	
	//profession
	public Profession getProfession(){
		if(this.worldObj.isRemote && (this.profession == null || this.getDataWatcher().getWatchableObjectInt(16) != this.profession.getRegID())){
			this.profession = Profession.registry.get(this.getDataWatcher().getWatchableObjectInt(16));
			this.refreshProfession();
		}
		return this.profession;
	}
	
	public void setProfession(int proid){
		this.getDataWatcher().updateObject(16, proid);
		this.refreshProfession();
	}
	
	public String getName(){
		return this.getCustomNameTag();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		//update profession
		if(this.worldObj.isRemote && (this.profession == null || this.getDataWatcher().getWatchableObjectInt(16) != this.profession.getRegID())){
			this.setProfession(this.getDataWatcher().getWatchableObjectInt(16));
		}
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if(!this.worldObj.isRemote){
			System.out.println("dead....");
			DataVillage.get(this.worldObj).addDeadVillager(this);
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation(PathHelper.full("message.villager.died"),this.getName()));
		}
	}

	public void refreshProfession(){
		int proid = this.getDataWatcher().getWatchableObjectInt(16);
		this.profession = Profession.registry.get(proid);
	}
	
	@Override
	public boolean allowLeashing() {
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    public boolean getAlwaysRenderNameTagForRender()
    {
        return true;
    }
    
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("proid", this.getDataWatcher().getWatchableObjectInt(16));
		tagCompound.setBoolean("gender", this.isMale());
		//home
		if(this.home != null){
			tagCompound.setIntArray("homebd", new int[]{
					this.home.minx,
					this.home.miny,
					this.home.minz,
					this.home.maxx,
					this.home.maxy,
					this.home.maxz
					});
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		super.readEntityFromNBT(tagCompund);
		int proid = tagCompund.getInteger("proid");
		this.setProfession(proid);
		this.setGender(tagCompund.getBoolean("gender"));
		//home
		int[] arr = tagCompund.getIntArray("homebd");
		if(arr == null || arr.length == 0){
			this.home = null;
			this.setDataFlag(2,false);
		}
		else{
			this.home = new IntBoundary(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5]);
			this.setDataFlag(2,true);
		}
	}

	
	//----------------------------------
	//upgrading preview
	@SideOnly(Side.CLIENT)
	public Profession previewProfession;
}
