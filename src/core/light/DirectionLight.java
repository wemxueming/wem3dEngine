package core.light;

import math3d.Vector3f;

import java.nio.FloatBuffer;

public class DirectionLight extends Light
{
    private Vector3f direction;

    public DirectionLight(Vector3f direction)
    {
        super();
        this.direction = direction;
    }

    public DirectionLight()
    {
        this(new Vector3f(0, -1, 1));
    }

    @Override
    public FloatBuffer buffer()
    {
        LIGHT_BUFFER.clear();
        LIGHT_BUFFER.put(color.x);
        LIGHT_BUFFER.put(color.y);
        LIGHT_BUFFER.put(color.z);
        LIGHT_BUFFER.put(brightness);

        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);

        LIGHT_BUFFER.put(direction.x);
        LIGHT_BUFFER.put(direction.y);
        LIGHT_BUFFER.put(direction.z);
        LIGHT_BUFFER.put(0f);

        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.flip();
        return LIGHT_BUFFER;
    }

    public Vector3f getDirection()
    {
        return direction;
    }

    public void setDirection(Vector3f direction)
    {
        this.direction = direction;
    }
}
