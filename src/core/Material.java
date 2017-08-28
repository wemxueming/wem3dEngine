package core;
import core.util.Utils;
import core.util.math3d.Vec2;
import core.util.math3d.Vec3;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Material
{
	public static final int MATERIAL_SIZE = 60;
	private static FloatBuffer MATERIAL_BUFFER = BufferUtils.createFloatBuffer(MATERIAL_SIZE / 4);
	private Vec3 albedo = new Vec3(0.5f);
	private float metallic = 0.5f;
	private float roughness = 0.5f;
	private float ao = 1f;
	private Vec2 wrap = new Vec2(1,1);
	private float heightScale = 0.05f;
	private int albedoTexture = -1;
	private int metallicTexture = -1;
	private int roughnessTexture = -1;
	private int aoTexture = -1;
	private int normalTexture = -1;
	private int heightTexture = -1;


	public FloatBuffer getMaterialBuffer()
	{
		MATERIAL_BUFFER.clear();
		MATERIAL_BUFFER.put(albedo.x);
		MATERIAL_BUFFER.put(albedo.y);
		MATERIAL_BUFFER.put(albedo.z);
		MATERIAL_BUFFER.put(metallic);

		MATERIAL_BUFFER.put(roughness);
		MATERIAL_BUFFER.put(ao);
		MATERIAL_BUFFER.put(wrap.x);
		MATERIAL_BUFFER.put(wrap.y);

		MATERIAL_BUFFER.put(heightScale);
		MATERIAL_BUFFER.put(Utils.getBool(albedoTexture != -1));
		MATERIAL_BUFFER.put(Utils.getBool(metallicTexture != -1));
		MATERIAL_BUFFER.put(Utils.getBool(roughnessTexture != -1));

		MATERIAL_BUFFER.put(Utils.getBool(aoTexture != -1));
		MATERIAL_BUFFER.put(Utils.getBool(normalTexture != -1));
		MATERIAL_BUFFER.put(Utils.getBool(heightTexture != -1));
		MATERIAL_BUFFER.flip();
		return MATERIAL_BUFFER;
	}

	public int getAlbedoTexture()
	{
		return albedoTexture;
	}

	public int getMetallicTexture()
	{
		return metallicTexture;
	}

	public int getRoughnessTexture()
	{
		return roughnessTexture;
	}

	public int getAoTexture()
	{
		return aoTexture;
	}

	public int getNormalTexture()
	{
		return normalTexture;
	}

	public int getHeightTexture()
	{
		return heightTexture;
	}

	public Vec3 getAlbedo()
	{
		return albedo;
	}

	public void setAlbedo(Vec3 albedo)
	{
		this.albedo = albedo;
	}

	public float getMetallic()
	{
		return metallic;
	}

	public void setMetallic(float metallic)
	{
		this.metallic = metallic;
	}

	public float getRoughness()
	{
		return roughness;
	}

	public void setRoughness(float roughness)
	{
		this.roughness = roughness;
	}

	public float getAo()
	{
		return ao;
	}

	public void setAo(float ao)
	{
		this.ao = ao;
	}

	public Vec2 getWrap()
	{
		return wrap;
	}

	public void setWrap(Vec2 wrap)
	{
		this.wrap = wrap;
	}

	public float getHeightScale()
	{
		return heightScale;
	}

	public void setHeightScale(float heightScale)
	{
		this.heightScale = heightScale;
	}
}
