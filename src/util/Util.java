package util;

import math3d.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util
{
    public static long getTime()
    {
        return System.nanoTime() / 1000000;
    }

    public static void out(String str)
    {
        System.out.println(str);
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
        return keyMap;
    }

    public static Map<Float, Vector3f> getAttenuationMap()
    {
        Map<Float, Vector3f> map = new HashMap<Float, Vector3f>();
        map.put(7f, new Vector3f(1.0f, 0.7f, 1.8f));
        map.put(13f, new Vector3f(1.0f, 0.35f, 0.44f));
        map.put(20f, new Vector3f(1.0f, 0.22f, 0.20f));
        map.put(32f, new Vector3f(1.0f, 0.14f, 0.07f));
        map.put(50f, new Vector3f(1.0f, 0.09f, 0.032f));
        map.put(65f, new Vector3f(1.0f, 0.07f, 0.017f));
        map.put(100f, new Vector3f(1.0f, 0.045f, 0.0075f));
        map.put(160f, new Vector3f(1.0f, 0.027f, 0.0028f));

        map.put(200f, new Vector3f(1.0f, 0.022f, 0.0019f));
        map.put(325f, new Vector3f(1.0f, 0.014f, 0.0007f));
        map.put(600f, new Vector3f(1.0f, 0.007f, 0.0002f));
        map.put(3250f, new Vector3f(1.0f, 0.0014f, 0.000007f));
        return map;
    }

    public static int getMipmapLevel(int width, int height)
    {
        if (width >= 4096 || height >= 4096)
        {
            return 13;
        }
        if ((width < 4096 && width >= 2048) || (height < 4096 && height >= 2048))
        {
            return 12;
        }
        if ((width < 2048 && width >= 1024) || (height < 2048 && height >= 1024))
        {
            return 11;
        }
        if ((width < 1024 && width >= 512) || (height < 1024 && height >= 512))
        {
            return 10;
        }
        if ((width < 512 && width >= 256) || (height < 512 && height >= 256))
        {
            return 9;
        }
        if ((width < 256 && width >= 128) || (height < 256 && height >= 128))
        {
            return 8;
        }
        if ((width < 128 && width >= 64) || (height < 128 && height >= 64))
        {
            return 7;
        }
        if ((width < 64 && width >= 32) || (height < 64 && height >= 32))
        {
            return 6;
        }
        if ((width < 32 && width >= 16) || (height < 32 && height >= 16))
        {
            return 5;
        }
        if ((width < 16 && width >= 8) || (height < 16 && height >= 8))
        {
            return 4;
        }
        if ((width < 8 && width >= 4) || (height < 8 && height >= 4))
        {
            return 3;
        }
        if ((width < 4 && width >= 2) || (height < 4 && height >= 2))
        {
            return 2;
        }
        return 1;
    }
}
