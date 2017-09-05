package core;

import core.gl.Contexts;
import org.lwjgl.opengl.*;
import util.BufferUtil;
import util.MathUtil;
import util.Util;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.List;


public class Texture
{
	private int id;
	private int target;

	public Texture(BufferedImage image)
	{
		id = Contexts.getContexts().createTexture();
		target = GL11.GL_TEXTURE_2D;
		ByteBuffer buffer = BufferUtil.createB(image);
		GL11.glBindTexture(target, id);
			GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexImage2D(target, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			GL30.glGenerateMipmap(target);
		GL11.glBindTexture(target, 0);
	}

	public Texture(BufferedImage cubeFront, BufferedImage cubeBack, BufferedImage cubeUp, BufferedImage cubeDown, BufferedImage cubeLeft, BufferedImage cubeRight)
	{
		id = Contexts.getContexts().createTexture();
		target = GL13.GL_TEXTURE_CUBE_MAP;
		GL11.glBindTexture(target, id);
			ByteBuffer buffer = BufferUtil.createB(cubeFront);
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, GL11.GL_RGB, cubeFront.getWidth(), cubeFront.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			buffer = BufferUtil.createB(cubeBack);
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, GL11.GL_RGB, cubeFront.getWidth(), cubeFront.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			buffer = BufferUtil.createB(cubeUp);
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, GL11.GL_RGB, cubeFront.getWidth(), cubeFront.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			buffer = BufferUtil.createB(cubeDown);
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, GL11.GL_RGB, cubeFront.getWidth(), cubeFront.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			buffer = BufferUtil.createB(cubeLeft);
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0, GL11.GL_RGB, cubeFront.getWidth(), cubeFront.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			buffer = BufferUtil.createB(cubeRight);
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, GL11.GL_RGB, cubeFront.getWidth(), cubeFront.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
			GL11.glTexParameteri(target, GL12.GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glBindTexture(target, 0);
	}

	public Texture(BufferedImage...images)
	{
		id = Contexts.getContexts().createTexture();
		target = GL30.GL_TEXTURE_2D_ARRAY;
		int width = 0;
		int height = 0;
		int depth = images.length;
		for (BufferedImage image : images)
		{
			width = MathUtil.toCompare(width, image.getWidth());
			height = MathUtil.toCompare(height, image.getHeight());
		}
		GL11.glBindTexture(target, id);
			GL42.glTexStorage3D(target, Util.getMipmapLevel(width, height), GL11.GL_RGBA8, width, height, depth);
			for (int i = 0; i < depth; i ++)
			{
				GL12.glTexSubImage3D(target, 0, 0, 0, i, images[i].getWidth(), images[i].getHeight(), 1, GL11.GL_RGBA,
						GL11.GL_UNSIGNED_BYTE, BufferUtil.createB(images[i]));
			}
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT );
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT );
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL30.glGenerateMipmap(target);
		GL11.glBindTexture(target, 0);
	}

	public Texture(List<BufferedImage> imageList)
	{
		id = Contexts.getContexts().createTexture();
		target = GL30.GL_TEXTURE_2D_ARRAY;
		int width = 0;
		int height = 0;
		int depth = imageList.size();
		for (BufferedImage image : imageList)
		{
			width = MathUtil.toCompare(width, image.getWidth());
			height = MathUtil.toCompare(height, image.getHeight());
		}
		GL11.glBindTexture(target, id);
			GL42.glTexStorage3D(target, Util.getMipmapLevel(width, height), GL11.GL_RGBA8, width, height, depth);
			for (int i = 0; i < depth; i ++)
			{
				GL12.glTexSubImage3D(target, 0, 0, 0, i, imageList.get(i).getWidth(), imageList.get(i).getHeight(), 1, GL11.GL_RGBA,
						GL11.GL_UNSIGNED_BYTE, BufferUtil.createB(imageList.get(i)));
			}
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL30.glGenerateMipmap(target);
		GL11.glBindTexture(target, 0);
	}

	public void begin(int location)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + location);
		GL11.glBindTexture(target, id);
	}

	public void end()
	{
		GL11.glBindTexture(target, 0);
	}
}
