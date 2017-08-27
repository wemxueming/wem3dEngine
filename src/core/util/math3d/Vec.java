//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core.util.math3d;

import java.io.Serializable;
import java.nio.FloatBuffer;

public abstract class Vec implements Serializable, ReadableVec
{
	protected Vec()
	{
	}

	public final float length()
	{
		return (float) Math.sqrt((double) this.lengthSquared());
	}

	public abstract float lengthSquared();

	public abstract Vec load(FloatBuffer var1);

	public abstract Vec negate();

	public final Vec normalise()
	{
		float len = this.length();
		if (len != 0.0F)
		{
			float l = 1.0F / len;
			return this.scale(l);
		} else
		{
			throw new IllegalStateException("Zero length vector");
		}
	}

	public abstract Vec store(FloatBuffer var1);

	public abstract Vec scale(float var1);
}
