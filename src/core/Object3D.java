package core;

import core.util.Cal;
import core.util.math3d.Mat4;
import core.util.math3d.Vec3;


public class Object3D
{
	private static Mat4 TRANSFORM_MAT4 = new Mat4();
	private static int ID = -1;

	private int id;
	private Model model;
	private Mod mod;

	private Vec3 position = new Vec3(0,0,0);
	private Vec3 rotate = new Vec3(0,0,0);
	private Vec3 scale = new Vec3(1,1,1);

	public Object3D(Model model)
	{
		id = ++ID;
		this.model = model;
		model.add(this);
		mod = model.getMod(Model.DEFAULT_MOD_NAME);
	}

	public Model getModel()
	{
		return model;
	}

	public void translate(float x, float y, float z)
	{
		position.set(x, y, z);
	}

	public void rotateX(float angle)
	{
		rotate.setX(angle);
	}

	public void rotateY(float angle)
	{
		rotate.setY(angle);
	}

	public void rotateZ(float angle)
	{
		rotate.setZ(angle);
	}

	public void scale(float s)
	{
		scale.set(s, s, s);
	}

	public void setMod(String name)
	{
		Mod mod = model.getMod(name);
		if (mod == null)
		{
			System.out.println("ERROR : can not find Mod of name: " + name);
		}
		else
		{
			this.mod = mod;
		}
	}

	public Mod getMod()
	{
		return mod;
	}

	public Vec3 getPosition()
	{
		return position;
	}

	public Vec3 getRotate()
	{
		return rotate;
	}

	public Vec3 getScale()
	{
		return scale;
	}

	public Mat4 getTransformMatrix()
	{
		TRANSFORM_MAT4.setIdentity();
		Mat4.translate(position, TRANSFORM_MAT4, TRANSFORM_MAT4);
		Mat4.rotate(Cal.toRadians(rotate.x), Cal.X_AXIS, TRANSFORM_MAT4, TRANSFORM_MAT4);
		Mat4.rotate(Cal.toRadians(rotate.y), Cal.Y_AXIS, TRANSFORM_MAT4, TRANSFORM_MAT4);
		Mat4.rotate(Cal.toRadians(rotate.z), Cal.Z_AXIS, TRANSFORM_MAT4, TRANSFORM_MAT4);
		Mat4.scale(scale, TRANSFORM_MAT4, TRANSFORM_MAT4);
		return TRANSFORM_MAT4;
	}

	public int getId()
	{
		return id;
	}
}
