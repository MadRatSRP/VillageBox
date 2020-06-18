//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.config.jsonData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonVBData {
	
	public ArrayList<JsonProfession> professions;
	
	public JsonVBData()
	{
		professions = new ArrayList<JsonProfession>();
	}
}
