package core.light;

import math3d.Vector3f;
import org.lwjgl.BufferUtils;
import util.Util;

import java.nio.FloatBuffer;
import java.util.Map;

public abstract class Light
{
    public static final int BLOCK_SIZE = 64;
    private static int ID = 0;
    public static Map<Float, Vector3f> ATTENUATION = Util.getAttenuationMap();
    protected static FloatBuffer LIGHT_BUFFER = BufferUtils.createFloatBuffer(16);
    private int id;
    protected Vector3f color;
    protected float brightness;

    public Light()
    {
        id = ID ++;
        color = new Vector3f(1);
        brightness = 10f;
    }

    public int id()
    {
        return id;
    }

    public abstract FloatBuffer buffer();

    public Vector3f getColor()
    {
        return color;
    }

    public void setColor(Vector3f color)
    {
        this.color = color;
    }

    public float getBrightness()
    {
        return brightness;
    }

    public void setBrightness(float brightness)
    {
        this.brightness = brightness;
    }
}
