package core;
import core.util.Cal;
import core.util.Utils;
import core.util.math3d.Vec3;

import java.nio.FloatBuffer;

public class SpotLight extends PointLight
{
	protected Vec3 direction;
	protected float cutoff;
	protected float outcutoff;

	public SpotLight(Vec3 position, Vec3 attenuation, Vec3 direction, float cutoff, float outcutoff)
	{
		super(position, attenuation);
		this.direction = direction;
		this.cutoff = cutoff;
		this.outcutoff = outcutoff;
	}

	public SpotLight(Vec3 position)
	{
		this(position, Utils.getAttenuationMap().get(32f), new Vec3(0,-1,0), (float) Math.cos(Cal.toRadians(45f)), (float) Math.cos(Cal.toRadians(50f)));
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
		LIGHT_BUFFER.put(cutoff);

		LIGHT_BUFFER.put(direction.x);
		LIGHT_BUFFER.put(direction.y);
		LIGHT_BUFFER.put(direction.z);
		LIGHT_BUFFER.put(outcutoff);

		LIGHT_BUFFER.put(attenuation.x);
		LIGHT_BUFFER.put(attenuation.y);
		LIGHT_BUFFER.put(attenuation.z);
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

	public float getCutoff()
	{
		return cutoff;
	}

	public void setCutoff(float cutoff)
	{
		this.cutoff = cutoff;
	}

	public float getOutcutoff()
	{
		return outcutoff;
	}

	public void setOutcutoff(float outcutoff)
	{
		this.outcutoff = outcutoff;
	}
}
