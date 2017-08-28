package core;
import core.util.math3d.Vec3;

import java.nio.FloatBuffer;

public class DirectLight extends Light
{
	protected Vec3 direction;

	public DirectLight(Vec3 direction)
	{
		this.direction = direction;
	}

	public DirectLight()
	{
		this(new Vec3(0,-1,1));
	}

	@Override
	public FloatBuffer getLightBuffer()
	{
		LIGHT_BUFFER.clear();
		LIGHT_BUFFER.put(color.x);
		LIGHT_BUFFER.put(color.y);
		LIGHT_BUFFER.put(color.z);
		LIGHT_BUFFER.put(brightness);

		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);

		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);

		LIGHT_BUFFER.put(direction.x);
		LIGHT_BUFFER.put(direction.y);
		LIGHT_BUFFER.put(direction.z);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.flip();
		return LIGHT_BUFFER;
	}

	public Vec3 getDirection()
	{
		return direction;
	}

	public void setDirection(Vec3 direction)
	{
		this.direction = direction;
	}
}
