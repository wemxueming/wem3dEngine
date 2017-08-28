package core;


import core.util.Cal;
import core.util.math3d.Mat4;
import core.util.math3d.Vec3;

public class Camera
{
	private static Mat4 PROJECTION_MAT4 = new Mat4();
	private static Mat4 VIEW_MAT4 = new Mat4();
	private int width = 800;
	private int height = 600;
	private float fov = 60f;
	private float near = 0.1f;
	private float far = 1000f;
	private Vec3 position = new Vec3(0, 0, -1);
	private Vec3 rotate = new Vec3(0,0,0);

	public Camera()
	{

	}

	public Mat4 getProjectionMatrix()
	{
		PROJECTION_MAT4.setIdentity();
		float aspectRatio = (float) width / (float)height;
		float yScale = Cal.coTangent(Cal.toRadians(fov / 2f));
		float xScale = yScale / aspectRatio;
		float frustumLength = far - near;
		PROJECTION_MAT4.setIdentity();
		PROJECTION_MAT4.m00 = xScale;
		PROJECTION_MAT4.m11 = yScale;
		PROJECTION_MAT4.m22 = -((far + near) / frustumLength);
		PROJECTION_MAT4.m23 = -1;
		PROJECTION_MAT4.m32 = -((2 * near * far) / frustumLength);
		PROJECTION_MAT4.m33 = 0;
		return PROJECTION_MAT4;
	}

	public Mat4 getViewMatrix()
	{
		VIEW_MAT4.setIdentity();
		Mat4.rotate(Cal.toRadians(rotate.x), Cal.X_AXIS, VIEW_MAT4, VIEW_MAT4);
		Mat4.rotate(Cal.toRadians(rotate.y), Cal.Y_AXIS, VIEW_MAT4, VIEW_MAT4);
		Mat4.rotate(Cal.toRadians(rotate.z), Cal.Z_AXIS, VIEW_MAT4, VIEW_MAT4);
		Mat4.translate(position, VIEW_MAT4, VIEW_MAT4);
		return VIEW_MAT4;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public float getFov()
	{
		return fov;
	}

	public void setFov(float fov)
	{
		this.fov = fov;
	}

	public float getNear()
	{
		return near;
	}

	public void setNear(float near)
	{
		this.near = near;
	}

	public float getFar()
	{
		return far;
	}

	public void setFar(float far)
	{
		this.far = far;
	}

	public Vec3 getPosition()
	{
		return position;
	}

	public void setPosition(Vec3 position)
	{
		this.position = position;
	}

	public Vec3 getRotate()
	{
		return rotate;
	}

	public void setRotate(Vec3 rotate)
	{
		this.rotate = rotate;
	}
}
