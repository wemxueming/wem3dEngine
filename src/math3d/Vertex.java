package math3d;

/**
 * Created by wem on 2017/5/14.
 */
public class Vertex
{
    protected Vector3f position;
    protected Vector2f texcoord;
    protected Vector3f normal;
    protected Vector3f tangent;

    public Vertex(Vector3f position, Vector2f texcoord, Vector3f normal)
    {
        this.position = position;
        this.texcoord = texcoord;
        this.normal = normal;
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
        return len;
    }

    public float[] getElements()
    {
        int len = length();
        int index = 0;
        float[] elements = new float[len];
        if (position != null)
        {
            elements[index] = position.x;
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
        return elements;
    }

    @Override
    public String toString()
    {
        return "Vertex{" +
                "position=" + position +
                ", texcoord=" + texcoord +
                ", normal=" + normal +
                ", setTangent=" + tangent +
                '}';
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public Vector2f getTexcoord()
    {
        return texcoord;
    }

    public void setTexcoord(Vector2f texcoord)
    {
        this.texcoord = texcoord;
    }

    public Vector3f getNormal()
    {
        return normal;
    }

    public void setNormal(Vector3f normal)
    {
        this.normal = normal;
    }

    public Vector3f getTangent()
    {
        return tangent;
    }

    public void setTangent(Vector3f tangent)
    {
        this.tangent = tangent;
    }
}
