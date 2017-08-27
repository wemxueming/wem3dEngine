package core.util;

import core.util.math3d.Mat4;
import core.util.math3d.Vec2;
import core.util.math3d.Vec3;
import core.util.math3d.Vec4;
import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

/**
 * Created by wem on 2017/6/20.
 */
public final class Buf
{

	public static FloatBuffer update(FloatBuffer buffer, float...targets)
	{
		buffer.clear();
		buffer.put(targets);
		buffer.flip();
		return buffer;
	}

	public static IntBuffer update(IntBuffer buffer, int...targets)
	{
		buffer.clear();
		buffer.put(targets);
		buffer.flip();
		return buffer;
	}

	public static IntBuffer update(IntBuffer buffer, boolean...targets)
	{
		buffer.clear();
		for (boolean b : targets)
		{
			if (b)
			{
				buffer.put(1);
			}
			else
			{
				buffer.put(0);
			}
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer update(FloatBuffer buffer, Vec2...targets)
	{
		buffer.clear();
		for (Vec2 vec2 : targets)
		{
			buffer.put(vec2.x);
			buffer.put(vec2.y);
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer update(FloatBuffer buffer, Vec3...targets)
	{
		buffer.clear();
		for (Vec3 vec3 : targets)
		{
			buffer.put(vec3.x);
			buffer.put(vec3.y);
			buffer.put(vec3.z);
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer update(FloatBuffer buffer, Vec4...targets)
	{
		buffer.clear();
		for (Vec4 vec4 : targets)
		{
			buffer.put(vec4.x);
			buffer.put(vec4.y);
			buffer.put(vec4.z);
			buffer.put(vec4.w);
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer update(FloatBuffer buffer, Mat4...targets)
	{
		buffer.clear();
		for (Mat4 mat4 : targets)
		{
			mat4.store(buffer);
		}
		buffer.flip();
		return buffer;
	}

	public static ByteBuffer createB(BufferedImage target, boolean alphed)
	{
		ByteBuffer buffer = null;
		int[] pixels = new int[target.getWidth() * target.getHeight()];
		target.getRGB(0, 0, target.getWidth(), target.getHeight(), pixels, 0, target.getWidth());
		if (alphed)
		{
			buffer = BufferUtils.createByteBuffer(target.getWidth() * target.getHeight() * 4);
		}
		else
		{
			buffer = BufferUtils.createByteBuffer(target.getWidth() * target.getHeight() * 3);
		}
		for(int y = 0; y < target.getHeight(); y++)
		{
			for(int x = 0; x < target.getWidth(); x++)
			{
				int pixel = pixels[y * target.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
				buffer.put((byte) (pixel & 0xFF));               // Blue component
				if (alphed)
				{
					buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
				}
			}
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer createF(float[] target)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(target.length);
		buffer.put(target);
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer createF(List<Float> target)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(target.size());
		for (float f : target)
		{
			buffer.put(f);
		}
		buffer.flip();
		return buffer;
	}

	public static IntBuffer createI(int[] target)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(target.length);
		buffer.put(target);
		buffer.flip();
		return buffer;
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

}
