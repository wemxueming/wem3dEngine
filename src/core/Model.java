package core;
import core.util.OGL;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Model
{
	private int vao;
	private int vbo;
	private int ebo;

	public Model(FloatBuffer vBuffer, IntBuffer iBuffer)
	{
		vao = OGL.createVertexArray();
		GL30.glBindVertexArray(vao);
		vbo = OGL.createBuffer(vBuffer, 0, 3,2,3);
		GL30.glBindVertexArray(0);
		ebo = OGL.createBuffer(iBuffer);
	}

	public int getVao()
	{
		return vao;
	}

	public int getVbo()
	{
		return vbo;
	}

	public int getEbo()
	{
		return ebo;
	}
}
