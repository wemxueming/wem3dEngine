//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core.util.math3d;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Mat2 extends Mat implements Serializable
{
	private static final long serialVersionUID = 1L;
	public float m00;
	public float m01;
	public float m10;
	public float m11;

	public Mat2()
	{
		this.setIdentity();
	}

	public Mat2(Mat2 src)
	{
		this.load(src);
	}

	public static Mat2 load(Mat2 src, Mat2 dest)
	{
		if (dest == null)
		{
			dest = new Mat2();
		}

		dest.m00 = src.m00;
		dest.m01 = src.m01;
		dest.m10 = src.m10;
		dest.m11 = src.m11;
		return dest;
	}

	public static Mat2 add(Mat2 left, Mat2 right, Mat2 dest)
	{
		if (dest == null)
		{
			dest = new Mat2();
		}

		dest.m00 = left.m00 + right.m00;
		dest.m01 = left.m01 + right.m01;
		dest.m10 = left.m10 + right.m10;
		dest.m11 = left.m11 + right.m11;
		return dest;
	}

	public static Mat2 sub(Mat2 left, Mat2 right, Mat2 dest)
	{
		if (dest == null)
		{
			dest = new Mat2();
		}

		dest.m00 = left.m00 - right.m00;
		dest.m01 = left.m01 - right.m01;
		dest.m10 = left.m10 - right.m10;
		dest.m11 = left.m11 - right.m11;
		return dest;
	}

	public static Mat2 mul(Mat2 left, Mat2 right, Mat2 dest)
	{
		if (dest == null)
		{
			dest = new Mat2();
		}

		float m00 = left.m00 * right.m00 + left.m10 * right.m01;
		float m01 = left.m01 * right.m00 + left.m11 * right.m01;
		float m10 = left.m00 * right.m10 + left.m10 * right.m11;
		float m11 = left.m01 * right.m10 + left.m11 * right.m11;
		dest.m00 = m00;
		dest.m01 = m01;
		dest.m10 = m10;
		dest.m11 = m11;
		return dest;
	}

	public static Vec2 transform(Mat2 left, Vec2 right, Vec2 dest)
	{
		if (dest == null)
		{
			dest = new Vec2();
		}

		float x = left.m00 * right.x + left.m10 * right.y;
		float y = left.m01 * right.x + left.m11 * right.y;
		dest.x = x;
		dest.y = y;
		return dest;
	}

	public static Mat2 transpose(Mat2 src, Mat2 dest)
	{
		if (dest == null)
		{
			dest = new Mat2();
		}

		float m01 = src.m10;
		float m10 = src.m01;
		dest.m01 = m01;
		dest.m10 = m10;
		return dest;
	}

	public static Mat2 invert(Mat2 src, Mat2 dest)
	{
		float determinant = src.determinant();
		if (determinant != 0.0F)
		{
			if (dest == null)
			{
				dest = new Mat2();
			}

			float determinant_inv = 1.0F / determinant;
			float t00 = src.m11 * determinant_inv;
			float t01 = -src.m01 * determinant_inv;
			float t11 = src.m00 * determinant_inv;
			float t10 = -src.m10 * determinant_inv;
			dest.m00 = t00;
			dest.m01 = t01;
			dest.m10 = t10;
			dest.m11 = t11;
			return dest;
		} else
		{
			return null;
		}
	}

	public static Mat2 negate(Mat2 src, Mat2 dest)
	{
		if (dest == null)
		{
			dest = new Mat2();
		}

		dest.m00 = -src.m00;
		dest.m01 = -src.m01;
		dest.m10 = -src.m10;
		dest.m11 = -src.m11;
		return dest;
	}

	public static Mat2 setIdentity(Mat2 src)
	{
		src.m00 = 1.0F;
		src.m01 = 0.0F;
		src.m10 = 0.0F;
		src.m11 = 1.0F;
		return src;
	}

	public static Mat2 setZero(Mat2 src)
	{
		src.m00 = 0.0F;
		src.m01 = 0.0F;
		src.m10 = 0.0F;
		src.m11 = 0.0F;
		return src;
	}

	public Mat2 load(Mat2 src)
	{
		return load(src, this);
	}

	public Mat load(FloatBuffer buf)
	{
		this.m00 = buf.get();
		this.m01 = buf.get();
		this.m10 = buf.get();
		this.m11 = buf.get();
		return this;
	}

	public Mat loadTranspose(FloatBuffer buf)
	{
		this.m00 = buf.get();
		this.m10 = buf.get();
		this.m01 = buf.get();
		this.m11 = buf.get();
		return this;
	}

	public Mat store(FloatBuffer buf)
	{
		buf.put(this.m00);
		buf.put(this.m01);
		buf.put(this.m10);
		buf.put(this.m11);
		return this;
	}

	public Mat storeTranspose(FloatBuffer buf)
	{
		buf.put(this.m00);
		buf.put(this.m10);
		buf.put(this.m01);
		buf.put(this.m11);
		return this;
	}

	public Mat transpose()
	{
		return this.transpose(this);
	}

	public Mat2 transpose(Mat2 dest)
	{
		return transpose(this, dest);
	}

	public Mat invert()
	{
		return invert(this, this);
	}

	public String toString()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(this.m00).append(' ').append(this.m10).append(' ').append('\n');
		buf.append(this.m01).append(' ').append(this.m11).append(' ').append('\n');
		return buf.toString();
	}

	public Mat negate()
	{
		return this.negate(this);
	}

	public Mat2 negate(Mat2 dest)
	{
		return negate(this, dest);
	}

	public Mat setIdentity()
	{
		return setIdentity(this);
	}

	public Mat setZero()
	{
		return setZero(this);
	}

	public float determinant()
	{
		return this.m00 * this.m11 - this.m01 * this.m10;
	}
}
