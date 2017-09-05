package core;

import math3d.Vector2f;
import math3d.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Material
{
    private static FloatBuffer MATERIAL_BUFFER = BufferUtils.createFloatBuffer(15);
    private Vector3f albedo;
    private float metallic;
    private float roughness;
    private float ao;
    private Vector2f wrap;
    private Texture albedoMap;
    private Texture metallicMap;
    private Texture roughnessMap;
    private Texture aoMap;
    private Texture normalMap;
    private Texture heightMap;
    private float heightScale;

    public Material(Vector3f albedo, float metallic, float roughness, float ao) {
        this.albedo = albedo;
        this.metallic = metallic;
        this.roughness = roughness;
        this.ao = ao;
        wrap = new Vector2f(1,1);
    }

    public FloatBuffer buffer()
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

        MATERIAL_BUFFER.put(isAlbedoMap());
        MATERIAL_BUFFER.put(isMetallicMap());
        MATERIAL_BUFFER.put(isRoughnessMap());
        MATERIAL_BUFFER.put(isAoMap());

        MATERIAL_BUFFER.put(isNormalMap());
        MATERIAL_BUFFER.put(isHeightMap());
        MATERIAL_BUFFER.put(heightScale);

        MATERIAL_BUFFER.flip();
        return MATERIAL_BUFFER;
    }

    public float isAlbedoMap()
    {
        return albedoMap!=null ? 1f : 0f;
    }

    public float isMetallicMap()
    {
        return metallicMap!=null ? 1f : 0f;
    }

    public float isRoughnessMap()
    {
        return roughnessMap!=null ? 1f : 0f;
    }

    public float isAoMap()
    {
        return aoMap!=null ? 1f : 0f;
    }

    public float isNormalMap()
    {
        return normalMap!=null ? 1f : 0f;
    }

    public float isHeightMap()
    {
        return heightMap!=null ? 1f : 0f;
    }

    public Vector3f getAlbedo() {
        return albedo;
    }

    public void setAlbedo(Vector3f albedo) {
        this.albedo = albedo;
    }

    public float getMetallic() {
        return metallic;
    }

    public void setMetallic(float metallic) {
        this.metallic = metallic;
    }

    public float getRoughness() {
        return roughness;
    }

    public void setRoughness(float roughness) {
        this.roughness = roughness;
    }

    public float getAo() {
        return ao;
    }

    public void setAo(float ao) {
        this.ao = ao;
    }

    public Vector2f getWrap() {
        return wrap;
    }

    public void setWrap(Vector2f wrap) {
        this.wrap = wrap;
    }

    public Texture getAlbedoMap() {
        return albedoMap;
    }

    public void setAlbedoMap(Texture albedoMap) {
        this.albedoMap = albedoMap;
    }

    public Texture getMetallicMap() {
        return metallicMap;
    }

    public void setMetallicMap(Texture metallicMap) {
        this.metallicMap = metallicMap;
    }

    public Texture getRoughnessMap() {
        return roughnessMap;
    }

    public void setRoughnessMap(Texture roughnessMap) {
        this.roughnessMap = roughnessMap;
    }

    public Texture getAoMap() {
        return aoMap;
    }

    public void setAoMap(Texture aoMap) {
        this.aoMap = aoMap;
    }

    public Texture getNormalMap() {
        return normalMap;
    }

    public void setNormalMap(Texture normalMap) {
        this.normalMap = normalMap;
    }

    public Texture getHeightMap() {
        return heightMap;
    }

    public void setHeightMap(Texture heightMap) {
        this.heightMap = heightMap;
    }

    public float getHeightScale() {
        return heightScale;
    }

    public void setHeightScale(float heightScale) {
        this.heightScale = heightScale;
    }
}
