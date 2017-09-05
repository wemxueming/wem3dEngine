package util;

import math3d.Matrix4f;
import math3d.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public class BufferUtil
{
    public static FloatBuffer createF(List<Vertex> target)
    {
        if (target.size() <= 0)
        {
            return null;
        } else
        {
            FloatBuffer buffer = BufferUtils.createFloatBuffer(target.size() * target.get(0).length());
            for (Vertex v : target)
            {
                buffer.put(v.getElements());
            }
            buffer.flip();
            return buffer;
        }
    }

    public static IntBuffer createI(List<Integer> target)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(target.size());
        for (int i : target)
        {
            buffer.put(i);
        }
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer update(FloatBuffer buffer, Matrix4f... mat4s)
    {
        buffer.clear();
        for (Matrix4f mat4 : mat4s)
        {
            mat4.store(buffer);
        }
        buffer.flip();
        return buffer;
    }

    public static IntBuffer update(IntBuffer buffer, int...values)
    {
        buffer.clear();
        for (int value : values)
        {
            buffer.put(value);
        }
        buffer.flip();
        return buffer;
    }
}
