package core;

import math3d.Vector2f;
import math3d.Vector3f;
import math3d.Vertex;
import util.BufferUtil;
import util.FileUtil;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader
{
    private static int PLAN_ID = -1;
    private Scene scene;

    private Map<String, Texture> textureMap = new HashMap<String, Texture>();

    public Loader(Scene scene)
    {
        this.scene = scene;
    }

    public Model loadPlan()
    {
        if (PLAN_ID == -1)
        {
            List<Vertex> vertexs = new ArrayList<Vertex>();
            vertexs.add(new Vertex(new Vector3f(-0.5f, 0, -0.5f), new Vector2f(0, 0), new Vector3f(0, 1, 0)));
            vertexs.add(new Vertex(new Vector3f(0.5f, 0, -0.5f), new Vector2f(1, 0), new Vector3f(0, 1, 0)));
            vertexs.add(new Vertex(new Vector3f(-0.5f, 0, 0.5f), new Vector2f(0, 1), new Vector3f(0, 1, 0)));
            vertexs.add(new Vertex(new Vector3f(0.5f, 0, 0.5f), new Vector2f(1, 1), new Vector3f(0, 1, 0)));
            int[] arrays = new int[]{
                    0,2,1,
                    1,2,3
            };
            List<Integer> indices = Util.asList(arrays);
            Mod mod = new Mod
            (
                new Mesh[]
                {
                    new Mesh(indices.size(), getDefaultMaterial())
                }
            );
            Model model = new Model(BufferUtil.createF(vertexs), BufferUtil.createI(indices), mod);
            scene.add(model);
            PLAN_ID = model.id();
            return model;
        }
        else
        {
            return scene.getModelMap().get(PLAN_ID);
        }
    }

    public Texture loadTexture(String src)
    {
        if (textureMap.get(src) == null)
        {
            Texture t = new Texture(FileUtil.readImage(src));
            textureMap.put(src, t);
            return t;
        }
        else
        {
            return textureMap.get(src);
        }
    }

    public Material getDefaultMaterial()
    {
        return new Material(new Vector3f(1,0,0), 0.5f,0.5f,1);
    }
}
