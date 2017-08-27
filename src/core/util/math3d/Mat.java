package core.util.math3d;

import java.io.Serializable;
import java.nio.FloatBuffer;

public abstract class Mat implements Serializable
{
	protected Mat()
	{
	}

	public abstract Mat setIdentity();

	public abstract Mat invert();

	public abstract Mat load(FloatBuffer var1);

	public abstract Mat loadTranspose(FloatBuffer var1);

	public abstract Mat negate();

	public abstract Mat store(FloatBuffer var1);

	public abstract Mat storeTranspose(FloatBuffer var1);

	public abstract Mat transpose();

	public abstract Mat setZero();

	public abstract float determinant();
}