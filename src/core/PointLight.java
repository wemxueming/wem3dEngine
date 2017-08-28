package core;
import core.util.Utils;
import core.util.math3d.Vec3;

import java.nio.FloatBuffer;

public class PointLight extends Light
{
	protected Vec3 position;
	protected Vec3 attenuation;

	public PointLight(Vec3 position, Vec3 attenuation)
	{
		this.position = position;
		this.attenuation = attenuation;
	}

	public PointLight(Vec3 position)
	{
		this(position, Utils.getAttenuationMap().get(32f));
	}

	@Override
	public FloatBuffer getLightBuffer()
	{
		LIGHT_BUFFER.clear();
		LIGHT_BUFFER.put(color.x);
		LIGHT_BUFFER.put(color.y);
		LIGHT_BUFFER.put(color.z);
		LIGHT_BUFFER.put(brightness);

		LIGHT_BUFFER.put(position.x);
		LIGHT_BUFFER.put(position.y);
		LIGHT_BUFFER.put(position.z);
		LIGHT_BUFFER.put(0f);

		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.put(0f);

		LIGHT_BUFFER.put(attenuation.x);
		LIGHT_BUFFER.put(attenuation.y);
		LIGHT_BUFFER.put(attenuation.z);
		LIGHT_BUFFER.put(0f);
		LIGHT_BUFFER.flip();
		return LIGHT_BUFFER;
	}

	public Vec3 getPosition()
	{
		return position;
	}

	public void setPosition(Vec3 position)
	{
		this.position = position;
	}

	public Vec3 getAttenuation()
	{
		return attenuation;
	}

	public void setAttenuation(Vec3 attenuation)
	{
		this.attenuation = attenuation;
	}
}
