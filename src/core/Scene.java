package core;

import core.gl.Buffer;
import core.gl.Contexts;
import core.gl.Shader;
import org.lwjgl.opengl.GL11;
import util.Util;

import java.util.HashMap;
import java.util.Map;

public class Scene
{
    private boolean running;
    private Timer timer;
    private Window window;
    private Camera camera;
    private Control control;
    private Shader modelShader;
    private Buffer cameraBuffer;
    private Buffer transformBuffer;
    private Map<Integer, Model> modelMap = new HashMap<Integer, Model>();

    public Scene(Window window, Camera camera)
    {
        this.window = window;
        this.camera = camera;
        init();
    }

    protected void init()
    {
        timer = new Timer();
        control = new Control();

        modelShader = Loader.load("src/shader/model.vert", "src/shader/model.frag");

        cameraBuffer = new Buffer(64 * 2, 0);
        transformBuffer = new Buffer(64, 1);

        camera.setCameraBuffer(cameraBuffer);
    }

    public void start()
    {
        Util.out("The scene start running success!");
        timer.start();
        running = window.isCreated();
    }

    public void render()
    {
        start();
        while (running && !window.isClosed())
        {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            control.update(timer.getDelta());
            camera.update();
            modelShader.begin();
            for (Model model : modelMap.values())
            {
                model.update();
            }
            modelShader.end();
            window.update();
            timer.update();
        }
        clean();
    }

    public void clean()
    {
        Contexts.getContexts().destroy();
        window.destroy();
        Util.out("The scene stop running success!");
    }

    public void add(String name, Controller controller)
    {
        control.getControllers().put(name, controller);
    }

    public void add(Model model)
    {
        modelMap.put(model.getId(), model);
        model.setTransformBuffer(transformBuffer);
    }

}
