package core.util;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OGL
{
	private static Map<Integer, Integer> PROGRAMS = new HashMap<Integer, Integer>();
	private static Map<Integer, List<Integer>> SHADERS = new HashMap<Integer, List<Integer>>();
	private static Map<Integer, Integer> VAOS = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> VBOS = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> TEXTURES = new HashMap<Integer, Integer>();

	private static void vertexFormat(int[] formats, int stride, int start, boolean divisored, int divisor)
	{
		int offset = 0;
		int length = formats.length + start;
		for (int i = start; i < length; i++)
		{
			int size = formats[i - start];
			GL20.glEnableVertexAttribArray(i);
			GL20.glVertexAttribPointer(i, size, GL11.GL_FLOAT, false, stride, offset);
			offset += size * Cal.FLOAT_BIT;
			if (divisored)
			{
				GL33.glVertexAttribDivisor(i, divisor);
			}
		}
	}

	private static int formatMipmap(int width, int height)
	{
		if (width >= 4096 || height >= 4096)
		{
			return 13;
		}
		if ((width < 4096 && width >= 2048) || (height < 4096 && height >= 2048))
		{
			return 12;
		}
		if ((width < 2048 && width >= 1024) || (height < 2048 && height >= 1024))
		{
			return 11;
		}
		if ((width < 1024 && width >= 512) || (height < 1024 && height >= 512))
		{
			return 10;
		}
		if ((width < 512 && width >= 256) || (height < 512 && height >= 256))
		{
			return 9;
		}
		if ((width < 256 && width >= 128) || (height < 256 && height >= 128))
		{
			return 8;
		}
		if ((width < 128 && width >= 64) || (height < 128 && height >= 64))
		{
			return 7;
		}
		if ((width < 64 && width >= 32) || (height < 64 && height >= 32))
		{
			return 6;
		}
		if ((width < 32 && width >= 16) || (height < 32 && height >= 16))
		{
			return 5;
		}
		if ((width < 16 && width >= 8) || (height < 16 && height >= 8))
		{
			return 4;
		}
		if ((width < 8 && width >= 4) || (height < 8 && height >= 4))
		{
			return 3;
		}
		if ((width < 4 && width >= 2) || (height < 4 && height >= 2))
		{
			return 2;
		}
		return 1;
	}

	public static int createProgram(CharSequence vert, CharSequence frag)
	{
		int program = GL20.glCreateProgram();

		int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexShader, vert);
		GL20.glCompileShader(vertexShader);
		GL20.glAttachShader(program, vertexShader);

		int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentShader, frag);
		GL20.glCompileShader(fragmentShader);
		GL20.glAttachShader(program, fragmentShader);

		GL20.glLinkProgram(program);
		GL20.glValidateProgram(program);

		List<Integer> shaderList = new ArrayList<Integer>();
		shaderList.add(vertexShader);
		shaderList.add(fragmentShader);
		PROGRAMS.put(program, program);
		SHADERS.put(program, shaderList);
		return program;
	}

	public static int createVertexArray()
	{
		int vao = GL30.glGenVertexArrays();
		VAOS.put(vao, vao);
		return vao;
	}

	public static int createBuffer(FloatBuffer buffer, int start, int... formats)
	{
		int vbo = GL15.glGenBuffers();
		int stride = Cal.toTotal(formats) * Cal.FLOAT_BIT;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		vertexFormat(formats, stride, start, false, 1);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		VBOS.put(vbo, vbo);
		return vbo;
	}

	public static int createBuffer(IntBuffer buffer)
	{
		int vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		VBOS.put(vbo, vbo);
		return vbo;
	}

	public static int createBuffer(int size, int start, int... formats)
	{
		int vbo = GL15.glGenBuffers();
		int stride = Cal.toTotal(formats) * Cal.FLOAT_BIT;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(size * stride), GL15.GL_STATIC_DRAW);
		vertexFormat(formats, stride, start, true, 1);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		VBOS.put(vbo, vbo);
		return vbo;
	}

	public static int createBuffer(int size, int binding)
	{
		int vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, vbo);
		GL15.glBufferData(GL31.GL_UNIFORM_BUFFER, size, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
		GL30.glBindBufferBase(GL31.GL_UNIFORM_BUFFER, binding, vbo);
		VBOS.put(vbo, vbo);
		return vbo;
	}

	public static int createTexture(boolean alphed, BufferedImage image)
	{
		ByteBuffer buffer = Buf.createB(image, alphed);
		int colorType = GL11.GL_RGB;
		if (alphed)
		{
			colorType = GL11.GL_RGBA;
		}
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, image.getWidth(), image.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		TEXTURES.put(texture, texture);
		return texture;
	}

	public static int createTexture(boolean alphed, BufferedImage front, BufferedImage back, BufferedImage up, BufferedImage down, BufferedImage left, BufferedImage right)
	{
		int colorType = GL11.GL_RGB;
		if (alphed)
		{
			colorType = GL11.GL_RGBA;
		}
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture);
		ByteBuffer buffer = Buf.createB(front, alphed);
		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, GL11.GL_RGB, front.getWidth(), front.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		buffer = Buf.createB(back, alphed);
		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, GL11.GL_RGB, front.getWidth(), front.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		buffer = Buf.createB(up, alphed);
		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, GL11.GL_RGB, front.getWidth(), front.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		buffer = Buf.createB(down, alphed);
		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, GL11.GL_RGB, front.getWidth(), front.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		buffer = Buf.createB(left, alphed);
		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0, GL11.GL_RGB, front.getWidth(), front.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		buffer = Buf.createB(right, alphed);
		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, GL11.GL_RGB, front.getWidth(), front.getHeight(), 0, colorType, GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL12.GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, 0);
		TEXTURES.put(texture, texture);
		return texture;
	}

	public static int createTexture(boolean alphed, BufferedImage... images)
	{
		int colorType = GL11.GL_RGB;
		if (alphed)
		{
			colorType = GL11.GL_RGBA;
		}
		int texture = GL11.glGenTextures();
		int width = 0;
		int height = 0;
		int depth = images.length;
		for (BufferedImage image : images)
		{
			width = Cal.toCompare(width, image.getWidth());
			height = Cal.toCompare(height, image.getHeight());
		}
		GL11.glBindTexture(GL30.GL_TEXTURE_2D_ARRAY, texture);
		GL42.glTexStorage3D(GL30.GL_TEXTURE_2D_ARRAY, formatMipmap(width, height), GL11.GL_RGBA8, width, height, depth);
		for (int i = 0; i < depth; i++)
		{
			GL12.glTexSubImage3D(GL30.GL_TEXTURE_2D_ARRAY, 0, 0, 0, i, images[i].getWidth(), images[i].getHeight(), 1, colorType,
					GL11.GL_UNSIGNED_BYTE, Buf.createB(images[i], alphed));
		}
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL30.glGenerateMipmap(GL30.GL_TEXTURE_2D_ARRAY);
		GL11.glBindTexture(GL30.GL_TEXTURE_2D_ARRAY, 0);
		TEXTURES.put(texture, texture);
		return texture;
	}

	public static int createTexture(boolean alphed, List<BufferedImage> imageList)
	{
		int colorType = GL11.GL_RGB;
		if (alphed)
		{
			colorType = GL11.GL_RGBA;
		}
		int id = GL11.glGenTextures();
		int width = 0;
		int height = 0;
		int depth = imageList.size();
		for (BufferedImage image : imageList)
		{
			width = Cal.toCompare(width, image.getWidth());
			height = Cal.toCompare(height, image.getHeight());
		}
		GL11.glBindTexture(GL30.GL_TEXTURE_2D_ARRAY, id);
		GL42.glTexStorage3D(GL30.GL_TEXTURE_2D_ARRAY, formatMipmap(width, height), GL11.GL_RGBA8, width, height, depth);
		for (int i = 0; i < depth; i++)
		{
			GL12.glTexSubImage3D(GL30.GL_TEXTURE_2D_ARRAY, 0, 0, 0, i, imageList.get(i).getWidth(), imageList.get(i).getHeight(),
					1, colorType, GL11.GL_UNSIGNED_BYTE, Buf.createB(imageList.get(i), alphed));
		}
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL30.GL_TEXTURE_2D_ARRAY, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL30.glGenerateMipmap(GL30.GL_TEXTURE_2D_ARRAY);
		GL11.glBindTexture(GL30.GL_TEXTURE_2D_ARRAY, 0);
		TEXTURES.put(id, id);
		return id;
	}

	public static void enableTexture(int target, int texture, int location)
	{
		if (texture > -1)
		{
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + location);
			GL11.glBindTexture(target, texture);
		}
	}

	public static void disableTexture(int target)
	{
		GL11.glBindTexture(target, 0);
	}

	public static void deleteTexture(int texture)
	{
		TEXTURES.remove(texture);
		GL11.glDeleteTextures(texture);
	}

	public static void deleteBuffer(int vbo)
	{
		VBOS.remove(vbo);
		GL15.glDeleteBuffers(vbo);
	}

	public static void deleteVertexArray(int vao)
	{
		VAOS.remove(vao);
		GL30.glDeleteVertexArrays(vao);
	}

	public static void deleteProgram(int program)
	{
		PROGRAMS.remove(program);
		for (int shader : SHADERS.get(program))
		{
			GL20.glDetachShader(program, shader);
			GL20.glDeleteShader(shader);
		}
		GL20.glDeleteProgram(program);
	}

	public static void destroy()
	{
		for (int texture : TEXTURES.values())
		{
			GL11.glDeleteTextures(texture);
		}
		for (int vbo : VBOS.values())
		{
			GL15.glDeleteBuffers(vbo);
		}
		for (int vao : VAOS.values())
		{
			GL30.glDeleteVertexArrays(vao);
		}
		for (int program : PROGRAMS.values())
		{
			for (int shader : SHADERS.get(program))
			{
				GL20.glDetachShader(program, shader);
				GL20.glDeleteShader(shader);
			}
			GL20.glDeleteProgram(program);
		}
	}
}
