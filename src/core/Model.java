package core;

import core.gl.Buffer;
import core.gl.VertexArray;
import math3d.Vector2f;
import math3d.Vector3f;
import math3d.Vertex;
import util.BufferUtil;
import util.Util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model
{
    public static final String DEFAULT_MOD = "default";

    private VertexArray vertexArray;
    private Buffer elementBuffer;
    private Map<Integer, Object3D> object3DMap = new HashMap<Integer, Object3D>();
    private Map<String, Mod> modMap = new HashMap<String, Mod>();

    public Model(FloatBuffer vertexBuffer, IntBuffer indiceBuffer, Mod mod)
    {
        vertexArray = new VertexArray();
        vertexArray.begin();
        Buffer arrayBuffer = new Buffer(vertexBuffer, 0, 3, 2, 3);
        vertexArray.end();
        elementBuffer = new Buffer(indiceBuffer);
        add(DEFAULT_MOD, mod);
    }

    public void add(String name, Mod mod)
    {
        modMap.put(name, mod);
    }

    public int id()
    {
        return vertexArray.id();
    }

    public Map<Integer, Object3D> getObject3DMap()
    {
        return object3DMap;
    }

    public void setObject3DMap(Map<Integer, Object3D> object3DMap)
    {
        this.object3DMap = object3DMap;
    }

    public Map<String, Mod> getModMap()
    {
        return modMap;
    }

    public void setModMap(Map<String, Mod> modMap)
    {
        this.modMap = modMap;
    }

    public VertexArray getVertexArray()
    {
        return vertexArray;
    }

    public Buffer getElementBuffer()
    {
        return elementBuffer;
    }
}
