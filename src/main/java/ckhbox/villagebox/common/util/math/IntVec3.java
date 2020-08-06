//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.util.math;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class IntVec3 {
    public int x, y, z;

    public IntVec3(double x, double y, double z) {
        set(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z));
    }

    public IntVec3(Vec3d vec) {
        this(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public IntVec3(int[] array) {
        set(array[0], array[1], array[2]);
    }

    public IntVec3(int x, int y, int z) {
        set(x, y, z);
    }

    public void set(int x, int y, int z) {
        this.x = x;

        this.y = y;

        this.z = z;
    }

    public int[] toArray() {
        return new int[]{x, y, z};
    }
}
