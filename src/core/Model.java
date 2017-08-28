package core;
import core.util.OGL;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class Model
{
	public static final String DEFAULT_MOD_NAME = "default";
	private int vao;
	private int vbo;
	private int ebo;

	private Map<Integer, Object3D> instances = new HashMap<Integer, Object3D>();
	private Map<String, Mod> mods = new HashMap<String, Mod>();

	public Model(FloatBuffer vBuffer, IntBuffer iBuffer, Mod defaultMod)
	{
		vao = OGL.createVertexArray();
		GL30.glBindVertexArray(vao);
		vbo = OGL.createBuffer(vBuffer, 0, 3,2,3);
		GL30.glBindVertexArray(0);
		ebo = OGL.createBuffer(iBuffer);
		add(DEFAULT_MOD_NAME, defaultMod);
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

	public Mod getMod(String name)
	{
		return mods.get(name);
	}

	public void add(String name, Mod mod)
	{
		mods.put(name, mod);
	}

	public void add(Object3D object3D)
	{
		instances.put(object3D.getId(), object3D);
	}

	public Map<Integer, Object3D> getInstances()
	{
		return instances;
	}

	public void setInstances(Map<Integer, Object3D> instances)
	{
		this.instances = instances;
	}

	public Map<String, Mod> getMods()
	{
		return mods;
	}

	public void setMods(Map<String, Mod> mods)
	{
		this.mods = mods;
	}
}
