package core;

import core.gl.Buffer;
import core.gl.Shader;
import core.gl.VertexArray;
import core.light.Light;
import org.lwjgl.opengl.GL11;
import util.FileUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Renderer
{
    private Scene scene;
    private Shader modelShader;
    private Buffer cameraBuffer;
    private Buffer transformBuffer;
    private Buffer lightsBuffer;

    public Renderer(Scene scene)
    {
        this.scene = scene;
        modelShader = new Shader();
        modelShader.createVertexShader(loadShaderSource("src/shader/model.vert"));
        modelShader.createFragmentShader(loadShaderSource("src/shader/model.frag"));
        modelShader.build();

        cameraBuffer = new Buffer(64 * 2, 0);
        transformBuffer = new Buffer(64, 1);
        lightsBuffer = new Buffer(16 + 64 * 10, 2);
    }

    public void update()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        Camera camera = scene.getCamera();
        Map<Integer, Model> modelMap = scene.getModelMap();
        Map<Integer, Light> lightMap = scene.getLightMap();

        cameraBuffer.begin();
        cameraBuffer.put(0, camera.buffer());
        cameraBuffer.end();

        lightsBuffer.begin();
        lightsBuffer.put(0, lightMap.size());
        int location = 16;
        for (Light light : lightMap.values())
        {
            lightsBuffer.put(location, light.buffer());
            location += Light.BLOCK_SIZE;
        }
        lightsBuffer.end();

        modelShader.begin();
        for (Model model : modelMap.values())
        {
            VertexArray vertexArray = model.getVertexArray();
            Buffer elementBuffer = model.getElementBuffer();
            Map<Integer, Object3D> object3DMap = model.getObject3DMap();

            vertexArray.begin();
            elementBuffer.begin();
            for (Object3D object3D : object3DMap.values())
            {
                if (object3D.isActive())
                {
                    transformBuffer.begin();
                    transformBuffer.put(0, object3D.buffer());
                    transformBuffer.end();

                    Mod mod = object3D.getMod();
                    Mesh[] meshes = mod.getMeshes();
                    for (Mesh mesh : meshes)
                    {
                        vertexArray.drawTriangle(mesh.getCount());
                    }
                }
            }
            elementBuffer.end();
            vertexArray.end();
        }
        modelShader.end();
    }


    protected CharSequence loadShaderSource(String src)
    {
        BufferedReader reader = null;
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            reader = new BufferedReader(new FileReader(src));
            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith("#include "))
                {
                    String subFileSrc = line.split("\\s+")[1].trim();
                    CharSequence subFile = FileUtil.readFile(subFileSrc);
                    stringBuilder.append(subFile).append("\n");
                } else
                {
                    stringBuilder.append(line).append("\n");
                }
            }
            reader.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
