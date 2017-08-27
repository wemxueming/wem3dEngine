package core.util;
import core.util.math3d.Vec2;
import core.util.math3d.Vec3;

public class Vertex
{
	private Vec3 position;
	private Vec2 texcoord;
	private Vec3 normal;
	private Vec3 tangent;
	private Vec3 bitangent;

	public Vertex(Vec3 position)
	{
		this.position = position;
	}

	public int length()
	{
		int len = 0;
		if (position != null)
		{
			len += 3;
		}
		if (texcoord != null)
		{
			len += 2;
		}
		if (normal != null)
		{
			len += 3;
		}
		if (tangent != null)
		{
			len += 3;
		}
		if (bitangent != null)
		{
			len += 3;
		}
		return len;
	}

	public float[] getElements()
	{
		float[] elements = new float[length()];
		int index = -1;
		if (position != null)
		{
			elements[++index] = position.x;
			elements[++index] = position.y;
			elements[++index] = position.z;
		}
		if (texcoord != null)
		{
			elements[++index] = texcoord.x;
			elements[++index] = texcoord.y;
		}
		if (normal != null)
		{
			elements[++index] = normal.x;
			elements[++index] = normal.y;
			elements[++index] = normal.z;
		}
		if (tangent != null)
		{
			elements[++index] = tangent.x;
			elements[++index] = tangent.y;
			elements[++index] = tangent.z;
		}
		if (bitangent != null)
		{
			elements[++index] = bitangent.x;
			elements[++index] = bitangent.y;
			elements[++index] = bitangent.z;
		}
		return elements;
	}

	public Vec3 getPosition()
	{
		return position;
	}

	public Vertex setPosition(Vec3 position)
	{
		if (position == null)
		{
			this.position = position;
		}
		else
		{
			this.position.set(position);
		}
		return this;
	}

	public Vertex setPosition(float x, float y, float z)
	{
		if (position == null)
		{
			position = new Vec3(x, y, z);
		}
		else
		{
			position.set(x, y, z);
		}
		return this;
	}

	public Vec2 getTexcoord()
	{
		return texcoord;
	}

	public Vertex setTexcoord(Vec2 texcoord)
	{
		if (texcoord == null)
		{
			this.texcoord = texcoord;
		}
		else
		{
			this.texcoord.set(texcoord);
		}
		return this;
	}

	public Vertex setTexcoord(float x, float y)
	{
		if (texcoord == null)
		{
			texcoord = new Vec2(x, y);
		}
		else
		{
			texcoord.set(x, y);
		}
		return this;
	}

	public Vec3 getNormal()
	{
		return normal;
	}

	public Vertex setNormal(Vec3 normal)
	{
		if (normal == null)
		{
			this.normal = normal;
		}
		else
		{
			this.normal.set(normal);
		}
		return this;
	}

	public Vertex setNormal(float x, float y, float z)
	{
		if (normal == null)
		{
			this.normal = new Vec3(x, y, z);
		}
		else
		{
			this.normal.set(x, y, z);
		}
		return this;
	}

	public Vec3 getTangent()
	{
		return tangent;
	}

	public Vertex setTangent(Vec3 tangent)
	{
		if (tangent == null)
		{
			this.tangent = tangent;
		}
		else
		{
			this.tangent.set(tangent);
		}
		return this;
	}

	public Vertex setTangent(float x, float y, float z)
	{
		if (tangent == null)
		{
			this.tangent = new Vec3(x, y, z);
		}
		else
		{
			this.tangent.set(x, y, z);
		}
		return this;
	}

	public Vec3 getBitangent()
	{
		return bitangent;
	}

	public Vertex setBitangent(Vec3 bitangent)
	{
		if (bitangent == null)
		{
			this.bitangent = bitangent;
		}
		else
		{
			this.bitangent.set(bitangent);
		}
		return this;
	}

	public Vertex setBitangent(float x, float y, float z)
	{
		if (bitangent == null)
		{
			this.bitangent = new Vec3(x, y, z);
		}
		else
		{
			this.bitangent.set(x, y, z);
		}
		return this;
	}
}
