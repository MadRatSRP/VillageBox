//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.tileentity.totem;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public abstract class TileEntityTotem extends TileEntity implements ITickable {

    private int timer = 60;
    private int interval = 60; //ticks,  1 sec = 20 ticks

    private void setUpdateEffectInterval(int interval) {
        this.interval = interval;
        this.timer = this.interval;
    }

    @Override
    public void update() {
        if (!this.worldObj.isRemote) {
            if (++this.timer > this.interval) {
                this.updateTotonEffect();
                this.timer = 0;
            }
        }
    }

    protected AxisAlignedBB getEffectField(int size, int height) {
        double hs = size / 2;
        double hh = height / 2;
        return new AxisAlignedBB(this.pos.getX() - hs, this.pos.getY() - hh, this.pos.getZ() - hs,
                this.pos.getX() + hs, this.pos.getY() + hh, this.pos.getZ() + hs);
    }

    protected abstract void updateTotonEffect();
}
