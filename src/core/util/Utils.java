package core.util;
import core.util.math3d.Vec3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils
{
	public static long getTime()
	{
		return System.nanoTime() / 1000000;
	}

	public static List<Integer> asList(int[] arrays)
	{
		List<Integer> list = new ArrayList<Integer>();
		for (int i : arrays)
		{
			list.add(i);
		}
		return list;
	}

	public static Map<String, Integer> getKeyboardMap()
	{
		Map<String, Integer> keyMap = new HashMap<String, Integer>();
		keyMap.put("w", 17);
		keyMap.put("s", 31);
		keyMap.put("a", 30);
		keyMap.put("d", 32);
		keyMap.put("esc", 1);
		keyMap.put("up", 200);
		keyMap.put("down", 208);
		keyMap.put("+", 13);
		keyMap.put("-", 12);
		keyMap.put("space", 57);
		return keyMap;
	}

	public static Map<Float, Vec3> getAttenuationMap()
	{
		Map<Float, Vec3> map = new HashMap<Float, Vec3>();
		map.put(7f, new Vec3(1.0f,	0.7f,	1.8f));
		map.put(13f, new Vec3(1.0f,	0.35f,	0.44f));
		map.put(20f, new Vec3(1.0f,	0.22f,	0.20f));
		map.put(32f, new Vec3(1.0f,	0.14f,	0.07f));
		map.put(50f, new Vec3(1.0f,	0.09f,	0.032f));
		map.put(65f, new Vec3(1.0f,	0.07f,	0.017f));
		map.put(100f, new Vec3(1.0f,	0.045f,	0.0075f));
		map.put(160f, new Vec3(1.0f,	0.027f,	0.0028f));

		map.put(200f, new Vec3(1.0f,	0.022f,	0.0019f));
		map.put(325f, new Vec3(1.0f,	0.014f,	0.0007f));
		map.put(600f, new Vec3(1.0f,	0.007f,	0.0002f));
		map.put(3250f, new Vec3(1.0f,	0.0014f,0.000007f));
		return map;
	}
}
