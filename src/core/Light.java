package core;
import core.util.math3d.Vec3;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public abstract class Light
{
	private static int ID = 0;
	private int id;
	public static final int PER_LIGHT_SIZE = 64;
	protected static FloatBuffer LIGHT_BUFFER = BufferUtils.createFloatBuffer(16);

	protected float brightness = 10;
	protected Vec3 color = new Vec3(1,1,1);


	public Light()
	{
		id = ID ++;
	}

	public abstract FloatBuffer getLightBuffer();

	public int getId()
	{
		return id;
	}

	public float getBrightness()
	{
		return brightness;
	}

	public void setBrightness(float brightness)
	{
		this.brightness = brightness;
	}

	public Vec3 getColor()
	{
		return color;
	}

	public void setColor(Vec3 color)
	{
		this.color = color;
	}
}
