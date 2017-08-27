//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core.util.math3d;
import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vec4 extends Vec implements Serializable, ReadableVec4, WritableVec4
{
	private static final long serialVersionUID = 1L;
	public static Vec4 ZERO = new Vec4(0, 0, 0, 0);
	public float x;
	public float y;
	public float z;
	public float w;

	public Vec4()
	{
	}

	public Vec4(ReadableVec4 src)
	{
		this.set(src);
	}

	public Vec4(float x, float y, float z, float w)
	{
		this.set(x, y, z, w);
	}

	public static Vec4 add(Vec4 left, Vec4 right, Vec4 dest)
	{
		if (dest == null)
		{
			return new Vec4(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
		} else
		{
			dest.set(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
			return dest;
		}
	}

	public static Vec4 sub(Vec4 left, Vec4 right, Vec4 dest)
	{
		if (dest == null)
		{
			return new Vec4(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
		} else
		{
			dest.set(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
			return dest;
		}
	}

	public static Vec4 mul(Vec4 left, Vec4 right, Vec4 dest)
	{
		if (dest == null)
		{
			return new Vec4(left.x * right.x, left.y * right.y, left.z * right.z, left.w * right.w);
		} else
		{
			dest.set(left.x * right.x, left.y * right.y, left.z * right.z, left.w * right.w);
			return dest;
		}
	}

	public static Vec4 div(Vec4 left, Vec4 right, Vec4 dest)
	{
		if (dest == null)
		{
			return new Vec4(left.x / right.x, left.y / right.y, left.z / right.z, left.w / right.w);
		} else
		{
			dest.set(left.x / right.x, left.y / right.y, left.z / right.z, left.w / right.w);
			return dest;
		}
	}

	public static float dot(Vec4 left, Vec4 right)
	{
		return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
	}

	public static float angle(Vec4 a, Vec4 b)
	{
		float dls = dot(a, b) / (a.length() * b.length());
		if (dls < -1.0F)
		{
			dls = -1.0F;
		} else
			if (dls > 1.0F)
			{
				dls = 1.0F;
			}

		return (float) Math.acos((double) dls);
	}

	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public void set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vec4 set(ReadableVec4 src)
	{
		this.x = src.getX();
		this.y = src.getY();
		this.z = src.getZ();
		this.w = src.getW();
		return this;
	}

	public float lengthSquared()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}

	public Vec4 translate(float x, float y, float z, float w)
	{
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}

	public Vec negate()
	{
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		this.w = -this.w;
		return this;
	}

	public Vec4 negate(Vec4 dest)
	{
		if (dest == null)
		{
			dest = new Vec4();
		}

		dest.x = -this.x;
		dest.y = -this.y;
		dest.z = -this.z;
		dest.w = -this.w;
		return dest;
	}

	public Vec4 normalize(Vec4 dest)
	{
		float l = this.length();
		if (dest == null)
		{
			dest = new Vec4(this.x / l, this.y / l, this.z / l, this.w / l);
		} else
		{
			dest.set(this.x / l, this.y / l, this.z / l, this.w / l);
		}

		return dest;
	}

	public Vec load(FloatBuffer buf)
	{
		this.x = buf.get();
		this.y = buf.get();
		this.z = buf.get();
		this.w = buf.get();
		return this;
	}

	public Vec scale(float scale)
	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		this.w *= scale;
		return this;
	}

	public Vec store(FloatBuffer buf)
	{
		buf.put(this.x);
		buf.put(this.y);
		buf.put(this.z);
		buf.put(this.w);
		return this;
	}

	public String toString()
	{
		return "Vec4: " + this.x + " " + this.y + " " + this.z + " " + this.w;
	}

	public final float getX()
	{
		return this.x;
	}

	public final void setX(float x)
	{
		this.x = x;
	}

	public final float getY()
	{
		return this.y;
	}

	public final void setY(float y)
	{
		this.y = y;
	}

	public float getZ()
	{
		return this.z;
	}

	public void setZ(float z)
	{
		this.z = z;
	}

	public float getW()
	{
		return this.w;
	}

	public void setW(float w)
	{
		this.w = w;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		} else
			if (obj == null)
			{
				return false;
			} else
				if (this.getClass() != obj.getClass())
				{
					return false;
				} else
				{
					Vec4 other = (Vec4) obj;
					return this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w;
				}
	}
}
