package core;

import core.gl.Buffer;
import core.gl.Shader;
import math3d.Vector2f;
import math3d.Vector3f;

import math3d.Vertex;
import util.BufferUtil;
import util.FileUtil;
import util.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader
{
    private static int PLAN_ID = -1;
    private Scene scene;

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
            Mod mod = new Mod(new Mesh[]{new Mesh(indices.size())});
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
}
