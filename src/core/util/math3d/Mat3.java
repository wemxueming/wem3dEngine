//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core.util.math3d;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Mat3 extends Mat implements Serializable
{
	private static final long serialVersionUID = 1L;
	public float m00;
	public float m01;
	public float m02;
	public float m10;
	public float m11;
	public float m12;
	public float m20;
	public float m21;
	public float m22;

	public Mat3()
	{
		this.setIdentity();
	}

	public static Mat3 load(Mat3 src, Mat3 dest)
	{
		if (dest == null)
		{
			dest = new Mat3();
		}

		dest.m00 = src.m00;
		dest.m10 = src.m10;
		dest.m20 = src.m20;
		dest.m01 = src.m01;
		dest.m11 = src.m11;
		dest.m21 = src.m21;
		dest.m02 = src.m02;
		dest.m12 = src.m12;
		dest.m22 = src.m22;
		return dest;
	}

	public static Mat3 add(Mat3 left, Mat3 right, Mat3 dest)
	{
		if (dest == null)
		{
			dest = new Mat3();
		}

		dest.m00 = left.m00 + right.m00;
		dest.m01 = left.m01 + right.m01;
		dest.m02 = left.m02 + right.m02;
		dest.m10 = left.m10 + right.m10;
		dest.m11 = left.m11 + right.m11;
		dest.m12 = left.m12 + right.m12;
		dest.m20 = left.m20 + right.m20;
		dest.m21 = left.m21 + right.m21;
		dest.m22 = left.m22 + right.m22;
		return dest;
	}

	public static Mat3 sub(Mat3 left, Mat3 right, Mat3 dest)
	{
		if (dest == null)
		{
			dest = new Mat3();
		}

		dest.m00 = left.m00 - right.m00;
		dest.m01 = left.m01 - right.m01;
		dest.m02 = left.m02 - right.m02;
		dest.m10 = left.m10 - right.m10;
		dest.m11 = left.m11 - right.m11;
		dest.m12 = left.m12 - right.m12;
		dest.m20 = left.m20 - right.m20;
		dest.m21 = left.m21 - right.m21;
		dest.m22 = left.m22 - right.m22;
		return dest;
	}

	public static Mat3 mul(Mat3 left, Mat3 right, Mat3 dest)
	{
		if (dest == null)
		{
			dest = new Mat3();
		}

		float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02;
		float m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02;
		float m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02;
		float m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12;
		float m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12;
		float m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12;
		float m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22;
		float m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22;
		float m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22;
		dest.m00 = m00;
		dest.m01 = m01;
		dest.m02 = m02;
		dest.m10 = m10;
		dest.m11 = m11;
		dest.m12 = m12;
		dest.m20 = m20;
		dest.m21 = m21;
		dest.m22 = m22;
		return dest;
	}

	public static Vec3 transform(Mat3 left, Vec3 right, Vec3 dest)
	{
		if (dest == null)
		{
			dest = new Vec3();
		}

		float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z;
		float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z;
		float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z;
		dest.x = x;
		dest.y = y;
		dest.z = z;
		return dest;
	}

	public static Mat3 transpose(Mat3 src, Mat3 dest)
	{
		if (dest == null)
		{
			dest = new Mat3();
		}

		float m00 = src.m00;
		float m01 = src.m10;
		float m02 = src.m20;
		float m10 = src.m01;
		float m11 = src.m11;
		float m12 = src.m21;
		float m20 = src.m02;
		float m21 = src.m12;
		float m22 = src.m22;
		dest.m00 = m00;
		dest.m01 = m01;
		dest.m02 = m02;
		dest.m10 = m10;
		dest.m11 = m11;
		dest.m12 = m12;
		dest.m20 = m20;
		dest.m21 = m21;
		dest.m22 = m22;
		return dest;
	}

	public static Mat3 invert(Mat3 src, Mat3 dest)
	{
		float determinant = src.determinant();
		if (determinant != 0.0F)
		{
			if (dest == null)
			{
				dest = new Mat3();
			}

			float determinant_inv = 1.0F / determinant;
			float t00 = src.m11 * src.m22 - src.m12 * src.m21;
			float t01 = -src.m10 * src.m22 + src.m12 * src.m20;
			float t02 = src.m10 * src.m21 - src.m11 * src.m20;
			float t10 = -src.m01 * src.m22 + src.m02 * src.m21;
			float t11 = src.m00 * src.m22 - src.m02 * src.m20;
			float t12 = -src.m00 * src.m21 + src.m01 * src.m20;
			float t20 = src.m01 * src.m12 - src.m02 * src.m11;
			float t21 = -src.m00 * src.m12 + src.m02 * src.m10;
			float t22 = src.m00 * src.m11 - src.m01 * src.m10;
			dest.m00 = t00 * determinant_inv;
			dest.m11 = t11 * determinant_inv;
			dest.m22 = t22 * determinant_inv;
			dest.m01 = t10 * determinant_inv;
			dest.m10 = t01 * determinant_inv;
			dest.m20 = t02 * determinant_inv;
			dest.m02 = t20 * determinant_inv;
			dest.m12 = t21 * determinant_inv;
			dest.m21 = t12 * determinant_inv;
			return dest;
		} else
		{
			return null;
		}
	}

	public static Mat3 negate(Mat3 src, Mat3 dest)
	{
		if (dest == null)
		{
			dest = new Mat3();
		}

		dest.m00 = -src.m00;
		dest.m01 = -src.m02;
		dest.m02 = -src.m01;
		dest.m10 = -src.m10;
		dest.m11 = -src.m12;
		dest.m12 = -src.m11;
		dest.m20 = -src.m20;
		dest.m21 = -src.m22;
		dest.m22 = -src.m21;
		return dest;
	}

	public static Mat3 setIdentity(Mat3 m)
	{
		m.m00 = 1.0F;
		m.m01 = 0.0F;
		m.m02 = 0.0F;
		m.m10 = 0.0F;
		m.m11 = 1.0F;
		m.m12 = 0.0F;
		m.m20 = 0.0F;
		m.m21 = 0.0F;
		m.m22 = 1.0F;
		return m;
	}

	public static Mat3 setZero(Mat3 m)
	{
		m.m00 = 0.0F;
		m.m01 = 0.0F;
		m.m02 = 0.0F;
		m.m10 = 0.0F;
		m.m11 = 0.0F;
		m.m12 = 0.0F;
		m.m20 = 0.0F;
		m.m21 = 0.0F;
		m.m22 = 0.0F;
		return m;
	}

	public Mat3 load(Mat3 src)
	{
		return load(src, this);
	}

	public Mat load(FloatBuffer buf)
	{
		this.m00 = buf.get();
		this.m01 = buf.get();
		this.m02 = buf.get();
		this.m10 = buf.get();
		this.m11 = buf.get();
		this.m12 = buf.get();
		this.m20 = buf.get();
		this.m21 = buf.get();
		this.m22 = buf.get();
		return this;
	}

	public Mat loadTranspose(FloatBuffer buf)
	{
		this.m00 = buf.get();
		this.m10 = buf.get();
		this.m20 = buf.get();
		this.m01 = buf.get();
		this.m11 = buf.get();
		this.m21 = buf.get();
		this.m02 = buf.get();
		this.m12 = buf.get();
		this.m22 = buf.get();
		return this;
	}

	public Mat store(FloatBuffer buf)
	{
		buf.put(this.m00);
		buf.put(this.m01);
		buf.put(this.m02);
		buf.put(this.m10);
		buf.put(this.m11);
		buf.put(this.m12);
		buf.put(this.m20);
		buf.put(this.m21);
		buf.put(this.m22);
		return this;
	}

	public Mat storeTranspose(FloatBuffer buf)
	{
		buf.put(this.m00);
		buf.put(this.m10);
		buf.put(this.m20);
		buf.put(this.m01);
		buf.put(this.m11);
		buf.put(this.m21);
		buf.put(this.m02);
		buf.put(this.m12);
		buf.put(this.m22);
		return this;
	}

	public Mat transpose()
	{
		return transpose(this, this);
	}

	public Mat3 transpose(Mat3 dest)
	{
		return transpose(this, dest);
	}

	public float determinant()
	{
		float f = this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
		return f;
	}

	public String toString()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append('\n');
		buf.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append('\n');
		buf.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append('\n');
		return buf.toString();
	}

	public Mat invert()
	{
		return invert(this, this);
	}

	public Mat negate()
	{
		return this.negate(this);
	}

	public Mat3 negate(Mat3 dest)
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
}
