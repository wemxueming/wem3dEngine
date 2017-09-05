package core.light;

import math3d.Vector3f;

import java.nio.FloatBuffer;

public class PointLight extends Light
{
    protected Vector3f position;
    protected Vector3f attenuation;

    public PointLight(Vector3f position, Vector3f attenuation)
    {
        super();
        this.position = position;
        this.attenuation = attenuation;
    }

    public PointLight()
    {
        this(new Vector3f(0,10,0), ATTENUATION.get(20f));
    }

    @Override
    public FloatBuffer buffer()
    {
        LIGHT_BUFFER.clear();
        LIGHT_BUFFER.put(color.x);
        LIGHT_BUFFER.put(color.y);
        LIGHT_BUFFER.put(color.z);
        LIGHT_BUFFER.put(brightness);

        LIGHT_BUFFER.put(position.x);
        LIGHT_BUFFER.put(position.y);
        LIGHT_BUFFER.put(position.z);
        LIGHT_BUFFER.put(0f);

        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.put(0f);

        LIGHT_BUFFER.put(attenuation.x);
        LIGHT_BUFFER.put(attenuation.y);
        LIGHT_BUFFER.put(attenuation.z);
        LIGHT_BUFFER.put(0f);
        LIGHT_BUFFER.flip();
        return LIGHT_BUFFER;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public Vector3f getAttenuation()
    {
        return attenuation;
    }

    public void setAttenuation(Vector3f attenuation)
    {
        this.attenuation = attenuation;
    }
}
