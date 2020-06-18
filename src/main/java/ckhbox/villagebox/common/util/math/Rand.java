//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.util.math;

import java.util.Random;

public class Rand {
	private static Random random;
	public static Random get(){
		if(random == null)
			random = new Random();
		return random;
	}
}
