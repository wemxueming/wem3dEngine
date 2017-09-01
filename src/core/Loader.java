package core;

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

public final class Loader {
    private static Map<String, Integer> SHADER_SOURCE = new HashMap<String, Integer>();

    public static Shader load(String vertSrc, String fragSrc) {
        Shader shader = new Shader();
        int vert = 0;
        if (SHADER_SOURCE.get(vertSrc) == null) {
            vert = shader.createVertexShader(loadShaderSource(vertSrc));
            SHADER_SOURCE.put(vertSrc, vert);
        } else {
            vert = SHADER_SOURCE.get(vertSrc);
        }
        int frag = 0;
        if (SHADER_SOURCE.get(fragSrc) == null) {
            frag = shader.createFragmentShader(loadShaderSource(fragSrc));
            SHADER_SOURCE.put(fragSrc, frag);
        } else {
            frag = SHADER_SOURCE.get(fragSrc);
        }
        shader.addShader(vert);
        shader.addShader(frag);
        shader.build();
        return shader;
    }

    public static Model loadPlan(float width, float height, boolean faced) {
        List<Vertex> vertexs = new ArrayList<Vertex>();
        if (faced) {
            vertexs.add(new Vertex(new Vector3f(-width / 2, -height / 2, 0), new Vector2f(0, 0), new Vector3f(0, 0, 1)));
            vertexs.add(new Vertex(new Vector3f(width / 2, -height / 2, 0), new Vector2f(1, 0), new Vector3f(0, 0, 1)));
            vertexs.add(new Vertex(new Vector3f(-width / 2, height / 2, 0), new Vector2f(0, 1), new Vector3f(0, 0, 1)));
            vertexs.add(new Vertex(new Vector3f(width / 2, height / 2, 0), new Vector2f(1, 1), new Vector3f(0, 0, 1)));
        } else {
            vertexs.add(new Vertex(new Vector3f(-width / 2, 0, -height / 2), new Vector2f(0, 0), new Vector3f(0, 1, 0)));
            vertexs.add(new Vertex(new Vector3f(width / 2, 0, -height / 2), new Vector2f(1, 0), new Vector3f(0, 1, 0)));
            vertexs.add(new Vertex(new Vector3f(-width / 2, 0, height / 2), new Vector2f(0, 1), new Vector3f(0, 1, 0)));
            vertexs.add(new Vertex(new Vector3f(width / 2, 0, height / 2), new Vector2f(1, 1), new Vector3f(0, 1, 0)));
        }
        int[] arrays = new int[]{
                0, 2, 1,
                1, 2, 3
        };

        List<Integer> indices = Util.asList(arrays);
        Mod mod = new Mod(new Mesh[]{new Mesh(indices.size())});
        return new Model(BufferUtil.createF(vertexs), BufferUtil.createI(indices), mod);
    }

    public static CharSequence loadShaderSource(String src) {
        BufferedReader reader = null;
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(src));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#include ")) {
                    String subFileSrc = line.split("\\s+")[1].trim();
                    CharSequence subFile = FileUtil.readFile(subFileSrc);
                    stringBuilder.append(subFile).append("\n");
                } else {
                    stringBuilder.append(line).append("\n");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
