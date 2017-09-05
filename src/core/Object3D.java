package core;

import math3d.Matrix4f;
import math3d.Vector3f;
import org.lwjgl.BufferUtils;
import util.BufferUtil;
import util.MathUtil;

import java.nio.FloatBuffer;

public class Object3D
{
    private static FloatBuffer TRANSFORM_BUFFER = BufferUtils.createFloatBuffer(16);
    private static Matrix4f TRANSFORM_MATRIX = new Matrix4f();
    private static int ID = 0;
    private int id;
    private Vector3f position;
    private Vector3f rotate;
    private Vector3f scale;
    private boolean active;

    private Model model;
    private Mod mod;

    public Object3D()
    {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
    }

    public Object3D(Vector3f position, Vector3f rotate, Vector3f scale)
    {
        id = ID++;
        this.position = position;
        this.rotate = rotate;
        this.scale = scale;
    }

    public FloatBuffer buffer()
    {
        TRANSFORM_MATRIX.setIdentity();
        Matrix4f.translate(position, TRANSFORM_MATRIX, TRANSFORM_MATRIX);
        Matrix4f.rotate(MathUtil.toRadians(rotate.x), MathUtil.X_AXIS, TRANSFORM_MATRIX, TRANSFORM_MATRIX);
        Matrix4f.rotate(MathUtil.toRadians(rotate.y), MathUtil.Y_AXIS, TRANSFORM_MATRIX, TRANSFORM_MATRIX);
        Matrix4f.rotate(MathUtil.toRadians(rotate.z), MathUtil.Z_AXIS, TRANSFORM_MATRIX, TRANSFORM_MATRIX);
        Matrix4f.scale(scale, TRANSFORM_MATRIX, TRANSFORM_MATRIX);
        BufferUtil.update(TRANSFORM_BUFFER, TRANSFORM_MATRIX);
        return TRANSFORM_BUFFER;
    }

    public void build(Model model)
    {
        this.model = model;
        model.getObject3DMap().put(id(), this);
        mod = model.getModMap().get(Model.DEFAULT_MOD);
    }

    public Mod getMod()
    {
        return mod;
    }

    public void setMod(Mod mod)
    {
        this.mod = mod;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
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

    public Vector3f getScale()
    {
        return scale;
    }

    public void setScale(Vector3f scale)
    {
        this.scale = scale;
    }

    public int id()
    {
        return id;
    }
}
