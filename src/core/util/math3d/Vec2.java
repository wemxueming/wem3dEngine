//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package core.util.math3d;
import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vec2 extends Vec implements Serializable, ReadableVec2, WritableVec2
{
	private static final long serialVersionUID = 1L;
	public static Vec2 ZERO = new Vec2(0);
	public float x;
	public float y;

	public Vec2()
	{
	}

	public Vec2(ReadableVec2 src)
	{
		this.set(src);
	}

	public Vec2(float x, float y)
	{
		this.set(x, y);
	}

	public Vec2(float f)
	{
		this.set(f, f);
	}

	public static float dot(Vec2 left, Vec2 right)
	{
		return left.x * right.x + left.y * right.y;
	}

	public static float angle(Vec2 a, Vec2 b)
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

	public static Vec2 add(Vec2 left, Vec2 right, Vec2 dest)
	{
		if (dest == null)
		{
			return new Vec2(left.x + right.x, left.y + right.y);
		} else
		{
			dest.set(left.x + right.x, left.y + right.y);
			return dest;
		}
	}

	public static Vec2 sub(Vec2 left, Vec2 right, Vec2 dest)
	{
		if (dest == null)
		{
			return new Vec2(left.x - right.x, left.y - right.y);
		} else
		{
			dest.set(left.x - right.x, left.y - right.y);
			return dest;
		}
	}

	public static Vec2 mul(Vec2 left, Vec2 right, Vec2 dest)
	{
		if (dest == null)
		{
			return new Vec2(left.x * right.x, left.y * right.y);
		} else
		{
			dest.set(left.x * right.x, left.y * right.y);
			return dest;
		}
	}

	public static Vec2 div(Vec2 left, Vec2 right, Vec2 dest)
	{
		if (dest == null)
		{
			return new Vec2(left.x / right.x, left.y / right.y);
		} else
		{
			dest.set(left.x / right.x, left.y / right.y);
			return dest;
		}
	}

	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2 set(ReadableVec2 src)
	{
		this.x = src.getX();
		this.y = src.getY();
		return this;
	}

	public float lengthSquared()
	{
		return this.x * this.x + this.y * this.y;
	}

	public Vec2 translate(float x, float y)
	{
		this.x += x;
		this.y += y;
		return this;
	}

	public Vec negate()
	{
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}

	public Vec2 negate(Vec2 dest)
	{
		if (dest == null)
		{
			dest = new Vec2();
		}

		dest.x = -this.x;
		dest.y = -this.y;
		return dest;
	}

	public Vec2 normalize(Vec2 dest)
	{
		float l = this.length();
		if (dest == null)
		{
			dest = new Vec2(this.x / l, this.y / l);
		} else
		{
			dest.set(this.x / l, this.y / l);
		}

		return dest;
	}

	public Vec store(FloatBuffer buf)
	{
		buf.put(this.x);
		buf.put(this.y);
		return this;
	}

	public Vec load(FloatBuffer buf)
	{
		this.x = buf.get();
		this.y = buf.get();
		return this;
	}

	public Vec scale(float scale)
	{
		this.x *= scale;
		this.y *= scale;
		return this;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder(64);
		sb.append("Vec2[");
		sb.append(this.x);
		sb.append(", ");
		sb.append(this.y);
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
					Vec2 other = (Vec2) obj;
					return this.x == other.x && this.y == other.y;
				}
	}
}
