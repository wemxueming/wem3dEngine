package core.util;


import core.util.math3d.Vec3;

import java.util.List;

public final class Cal
{
	public static final Vec3 X_AXIS = new Vec3(1, 0, 0);
	public static final Vec3 Y_AXIS = new Vec3(0, 1, 0);
	public static final Vec3 Z_AXIS = new Vec3(0, 0, 1);

	public static final int FLOAT_BIT = 4;
	public static final int INT_BIT = 4;
	public static final int MAT4_BIT = 64;

	public static float toRadians(float degrees)
	{
		return (float) Math.toRadians(degrees);
	}

	public static float toDegrees(float radians)
	{
		return (float) Math.toDegrees(radians);
	}

	public static float coTangent(float angle)
	{
		return (float) (1f / Math.tan(angle));
	}

	public static int toTotal(int[] arrays)
	{
		int result = 0;
		for (int i : arrays)
		{
			result += i;
		}
		return result;
	}

	public static int toTotal(List<Integer> arrays)
	{
		int result = 0;
		for (int i : arrays)
		{
			result += i;
		}
		return result;
	}

	public static int toCompare(int i1, int i2)
	{
		if (i1 > i2)
		{
			return i1;
		}
		else
		{
			return i2;
		}
	}
}
