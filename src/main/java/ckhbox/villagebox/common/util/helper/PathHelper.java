//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.util.helper;

import ckhbox.villagebox.VillageBoxMod;

public class PathHelper {
    public static String full(String path) {
        return VillageBoxMod.MODID + ":" + path;
    }
}
