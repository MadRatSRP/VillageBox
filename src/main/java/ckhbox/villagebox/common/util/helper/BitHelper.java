//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.util.helper;

public class BitHelper {
    public static boolean readBit(int data, int offset) {
        return ((data >> offset) & 1) != 0;
    }

    public static int writeBit(int data, int offset, boolean flag) {
        if (readBit(data, offset) != flag) {
            data ^= (1 << offset);
        }
        return data;
    }
}
