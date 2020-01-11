package io.github.fatsquirrels.deuzum.net;

import org.json.JSONObject;

public class ServerUserFunctionalityUtils {

	public static boolean containsNeededVals(JSONObject data, String...vals) {
		boolean result = true;
		for(int i = 0; i < vals.length; i++)
			if(!data.has(vals[i])) {
				result = false;
				break;
			}
		return result;
	}
	
	
}
