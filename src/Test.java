import core.*;
import core.control.FreeLook;
import core.util.math3d.Vec3;

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

		PointLight pointLight0 = new PointLight(new Vec3(0,1,0));
		pointLight0.setColor(new Vec3(0,1,0));
		scene.add(pointLight0);

		PointLight pointLight = new PointLight(new Vec3(0,1,0));
		pointLight.setColor(new Vec3(0,0,1));
		pointLight.setAttenuation(new Vec3(1,0,0));
		scene.add(pointLight);

		scene.run();
	}
}
