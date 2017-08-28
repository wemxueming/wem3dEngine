import core.Loader;
import core.Model;
import core.Object3D;
import core.Scene;
import core.control.FreeLook;

public class Test
{
	public static void main(String[] args)
	{
		Scene scene = new Scene();
		FreeLook freeLook = new FreeLook(scene.getCamera());
		freeLook.setActive(true);
		scene.add("freeLook", freeLook);

		Model plan = Loader.createPlan(1, 1, true);
		Object3D plan1 = new Object3D(plan);

		scene.add(plan1);

		scene.run();
	}
}
