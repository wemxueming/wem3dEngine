//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core.util.math3d;
import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vec3 extends Vec implements Serializable, ReadableVec3, WritableVec3
{
	private static final long serialVersionUID = 1L;
	public static Vec3 ZERO = new Vec3(0);
	public float x;
	public float y;
	public float z;

	public Vec3()
	{
	}

	public Vec3(ReadableVec3 src)
	{
		this.set(src);
	}

	public Vec3(float x, float y, float z)
	{
		this.set(x, y, z);
	}

	public Vec3(float f)
	{
		this.set(f, f, f);
	}

	public static Vec3 add(Vec3 left, Vec3 right, Vec3 dest)
	{
		if (dest == null)
		{
			return new Vec3(left.x + right.x, left.y + right.y, left.z + right.z);
		} else
		{
			dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
			return dest;
		}
	}

	public static Vec3 sub(Vec3 left, Vec3 right, Vec3 dest)
	{
		if (dest == null)
		{
			return new Vec3(left.x - right.x, left.y - right.y, left.z - right.z);
		} else
		{
			dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
			return dest;
		}
	}

	//add
	public static Vec3 mul(Vec3 left, float value, Vec3 dest)
	{
		if (dest == null)
		{
			return new Vec3(left.x * value, left.y * value, left.z * value);
		} else
		{
			dest.set(left.x * value, left.y * value, left.z * value);
			return dest;
		}
	}

	public static Vec3 div(Vec3 left, float value, Vec3 dest)
	{
		if (dest == null)
		{
			return new Vec3(left.x / value, left.y / value, left.z / value);
		} else
		{
			dest.set(left.x / value, left.y / value, left.z / value);
			return dest;
		}
	}

	public static Vec3 cross(Vec3 left, Vec3 right, Vec3 dest)
	{
		if (dest == null)
		{
			dest = new Vec3();
		}

		dest.set(left.y * right.z - left.z * right.y, right.x * left.z - right.z * left.x, left.x * right.y - left.y * right.x);
		return dest;
	}

	public static float dot(Vec3 left, Vec3 right)
	{
		return left.x * right.x + left.y * right.y + left.z * right.z;
	}

	public static float angle(Vec3 a, Vec3 b)
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

	public Vec3 set(ReadableVec3 src)
	{
		this.x = src.getX();
		this.y = src.getY();
		this.z = src.getZ();
		return this;
	}

	public float lengthSquared()
	{
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec3 translate(float x, float y, float z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vec negate()
	{
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}

	public Vec3 negate(Vec3 dest)
	{
		if (dest == null)
		{
			dest = new Vec3();
		}

		dest.x = -this.x;
		dest.y = -this.y;
		dest.z = -this.z;
		return dest;
	}

	public Vec3 normalize(Vec3 dest)
	{
		float l = this.length();
		if (dest == null)
		{
			dest = new Vec3(this.x / l, this.y / l, this.z / l);
		} else
		{
			dest.set(this.x / l, this.y / l, this.z / l);
		}

		return dest;
	}

	public Vec load(FloatBuffer buf)
	{
		this.x = buf.get();
		this.y = buf.get();
		this.z = buf.get();
		return this;
	}

	public Vec scale(float scale)
	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}

	public Vec store(FloatBuffer buf)
	{
		buf.put(this.x);
		buf.put(this.y);
		buf.put(this.z);
		return this;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder(64);
		sb.append("Vec3[");
		sb.append(this.x);
		sb.append(", ");
		sb.append(this.y);
		sb.append(", ");
		sb.append(this.z);
		sb.append(']');
		return sb.toString();
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
					Vec3 other = (Vec3) obj;
					return this.x == other.x && this.y == other.y && this.z == other.z;
				}
	}
}
