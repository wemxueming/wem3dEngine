package core;

import core.gl.Buffer;
import math3d.Matrix4f;
import math3d.Vector3f;
import org.lwjgl.BufferUtils;
import util.BufferUtil;
import util.MathUtil;

import java.nio.FloatBuffer;

public class Camera
{
    private static FloatBuffer CAMERA_BUFFER = BufferUtils.createFloatBuffer(32);
    private static Matrix4f PROJECTION_MATRIX = new Matrix4f();
    private static Matrix4f VIEW_MATRIX = new Matrix4f();
    private int width;
    private int height;
    private float fov;
    private float near;
    private float far;
    private Vector3f position;
    private Vector3f rotate;

    public Camera(int width, int height, float fov, float near, float far, Vector3f position, Vector3f rotate)
    {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.near = near;
        this.far = far;
        this.position = position;
        this.rotate = rotate;
    }

    public Camera()
    {
        this(800, 600, 60f, 0.1f, 1000f, new Vector3f(0, 0, -1), new Vector3f(0, 0, 0));
    }

    public FloatBuffer buffer()
    {
        float aspectRatio = (float) width / (float) height;
        float yScale = MathUtil.coTangent(MathUtil.toRadians(fov / 2f));
        float xScale = yScale / aspectRatio;
        float frustumLength = far - near;
        PROJECTION_MATRIX.setIdentity();
        PROJECTION_MATRIX.m00 = xScale;
        PROJECTION_MATRIX.m11 = yScale;
        PROJECTION_MATRIX.m22 = -((far + near) / frustumLength);
        PROJECTION_MATRIX.m23 = -1;
        PROJECTION_MATRIX.m32 = -((2 * near * far) / frustumLength);
        PROJECTION_MATRIX.m33 = 0;

        VIEW_MATRIX.setIdentity();
        Matrix4f.rotate(MathUtil.toRadians(rotate.x), MathUtil.X_AXIS, VIEW_MATRIX, VIEW_MATRIX);
        Matrix4f.rotate(MathUtil.toRadians(rotate.y), MathUtil.Y_AXIS, VIEW_MATRIX, VIEW_MATRIX);
        Matrix4f.rotate(MathUtil.toRadians(rotate.z), MathUtil.Z_AXIS, VIEW_MATRIX, VIEW_MATRIX);
        Matrix4f.translate(position, VIEW_MATRIX, VIEW_MATRIX);

        BufferUtil.update(CAMERA_BUFFER, PROJECTION_MATRIX, VIEW_MATRIX);
        return CAMERA_BUFFER;
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

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public Vector3f getRotate()
    {
        return rotate;
    }

    public void setRotate(Vector3f rotate)
    {
        this.rotate = rotate;
    }
}
