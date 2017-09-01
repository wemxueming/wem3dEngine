package core;

import core.gl.Buffer;
import core.gl.VertexArray;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class Model
{
    public static String DEFAULT_MOD_NAME = "default";
    private VertexArray vertexArray;
    private Buffer arrayBuffer;
    private Buffer elementBuffer;
    private Buffer transformBuffer;
    private Map<Integer, Object3D> object3DMap = new HashMap<Integer, Object3D>();
    private Map<Integer, Mod> modMap = new HashMap<Integer, Mod>();
    private Map<String, Integer> modNameMap = new HashMap<String, Integer>();
    private int modId;

    public Model(FloatBuffer vertexBuffer, IntBuffer indiceBuffer, Mod mod)
    {
        vertexArray = new VertexArray();
        vertexArray.begin();
        arrayBuffer = new Buffer(vertexBuffer, 0, 3, 2, 3);
        vertexArray.end();
        elementBuffer = new Buffer(indiceBuffer);
        addMod(DEFAULT_MOD_NAME, mod);
    }

    public void update()
    {
        vertexArray.begin();
        elementBuffer.begin();
        for (Object3D object3D : object3DMap.values())
        {
            transformBuffer.begin();
            transformBuffer.setBuffer(0, object3D.getTransformBuffer());
            transformBuffer.end();
            for (Mesh mesh : modMap.get(object3D.getModId()).getMeshes())
            {
                vertexArray.drawTriangle(mesh.getCount());
            }
        }
        elementBuffer.end();
        vertexArray.end();
    }

    public void addMod(String name, Mod mod)
    {
        if (modNameMap.get(name) == null)
        {
            modNameMap.put(name, modId);
            modMap.put(modId, mod);
            modId++;
        } else
        {
            modMap.put(modNameMap.get(name), mod);
        }
    }

    public Integer getModId(String name)
    {
        return modNameMap.get(name);
    }

    public void setTransformBuffer(Buffer transformBuffer)
    {
        this.transformBuffer = transformBuffer;
    }

    public Map<Integer, Object3D> getObject3DMap()
    {
        return object3DMap;
    }

    public void setObject3DMap(Map<Integer, Object3D> object3DMap)
    {
        this.object3DMap = object3DMap;
    }

    public Map<Integer, Mod> getModMap()
    {
        return modMap;
    }

    public void setModMap(Map<Integer, Mod> modMap)
    {
        this.modMap = modMap;
    }

    public int getId()
    {
        return vertexArray.getId();
    }
}
