package util;

import math3d.Vector3f;

public class MathUtil
{
    public static final Vector3f X_AXIS = new Vector3f(1, 0, 0);
    public static final Vector3f Y_AXIS = new Vector3f(0, 1, 0);
    public static final Vector3f Z_AXIS = new Vector3f(0, 0, 1);

    public static final int FLOAT_BIT = 4;
    public static final int INT_BIT = 4;
    public static final int MATRIX_BIT = 64;

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

    public static int toCompare(int i1, int i2)
    {

        if (i1 > i2)
        {
            return i1;
        } else
        {
            return i2;

        }
    }

    public static float toSpot(float angle)
    {
        return (float)Math.cos(MathUtil.toRadians(angle));
    }
}