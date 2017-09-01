import core.*;
import core.control.FreeLook;

public class Test {
    public static void main(String[] args) {
        Window window = new Window();
        Camera camera = new Camera();
        Scene scene = new Scene(window, camera);

        FreeLook freeLook = new FreeLook(camera);
        freeLook.setActive(true);
        scene.add("freeLook", freeLook);

        Model planModel = Loader.loadPlan(1, 1, true);
        scene.add(planModel);

        Object3D o1 = new Object3D();
        o1.setModel(planModel);

        scene.render();
    }
}
