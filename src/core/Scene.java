package core;

import core.control.Control;
import core.gl.Contexts;
import core.light.Light;
import util.Util;

import java.util.HashMap;
import java.util.Map;

public class Scene
{
    private boolean running;
    private Timer timer;
    private Window window;
    private Camera camera;
    private Renderer renderer;
    private Controller controller;
    private Loader loader;

    private Map<Integer, Model> modelMap = new HashMap<Integer, Model>();
    private Map<Integer, Light> lightMap = new HashMap<Integer, Light>();

    public Scene(Window window, Camera camera)
    {
        this.window = window;
        this.camera = camera;
        init();
    }

    protected void init()
    {
        timer = new Timer();
        controller = new Controller(timer);
        loader = new Loader(this);
        renderer = new Renderer(this);
    }

    public void render()
    {
        Util.out("The scene start running success!");
        timer.start();
        running = window.isCreated();
        while (running && !window.isClosed())
        {
            timer.update();
            controller.update();
            renderer.update();
            window.update();
        }
        Contexts.getContexts().destroy();
        window.destroy();
        Util.out("The scene stop running success!");
    }

    public void add(Control control)
    {
        this.controller.getControls().put(control.id(), control);
    }

    public void add(Model model)
    {
        modelMap.put(model.id(), model);
    }

    public void add(Light light)
    {
        lightMap.put(light.id(), light);
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public Timer getTimer()
    {
        return timer;
    }

    public void setTimer(Timer timer)
    {
        this.timer = timer;
    }

    public Window getWindow()
    {
        return window;
    }

    public void setWindow(Window window)
    {
        this.window = window;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public Map<Integer, Model> getModelMap()
    {
        return modelMap;
    }

    public void setModelMap(Map<Integer, Model> modelMap)
    {
        this.modelMap = modelMap;
    }

    public Map<Integer, Light> getLightMap()
    {
        return lightMap;
    }

    public void setLightMap(Map<Integer, Light> lightMap)
    {
        this.lightMap = lightMap;
    }

    public Renderer getRenderer()
    {
        return renderer;
    }

    public void setRenderer(Renderer renderer)
    {
        this.renderer = renderer;
    }

    public Loader getLoader()
    {
        return loader;
    }

    public void setLoader(Loader loader)
    {
        this.loader = loader;
    }
}
