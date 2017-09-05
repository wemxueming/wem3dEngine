import core.*;
import core.control.FreeLook;
import core.light.DirectionLight;
import math3d.Vector3f;

public class Test
{
    public static void main(String[] args)
    {
        Window window = new Window();
        Camera camera = new Camera();
        Scene scene = new Scene(window, camera);

        FreeLook freeLook = new FreeLook(camera);
        freeLook.setActive(true);
        scene.add(freeLook);

        DirectionLight d1 = new DirectionLight(new Vector3f(0,1,0));
        scene.add(d1);

        Model plan = scene.getLoader().loadPlan();
        Object3D o1 = new Object3D();
        o1.build(plan);
        o1.setActive(true);

        scene.render();
    }
}
