package core.control;

import core.Camera;
import core.Controller;
import math3d.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import util.MathUtil;

public class FreeLook implements Controller
{
    private static final float MOVE_FORMAT = 0.003f;
    private static final float LOOK_FORMAT = 0.004f;
    private static final float MAX_LOOK_UP = 85;
    private static final float MAX_LOOK_DOWN = -85;
    private static Vector3f ROTATE_LIMIT = new Vector3f();
    private static float DX;
    private static float DY;
    private static boolean UP;
    private static boolean DOWN;
    private static boolean LEFT;
    private static boolean RIGHT;

    private Camera camera;
    private boolean active;
    private float moveSpeed = 2f;
    private float lookSpeed = 1f;
    private int keyUp = KEY_MAP.get("w");
    private int keyDown = KEY_MAP.get("s");
    private int keyLeft = KEY_MAP.get("a");
    private int keyRight = KEY_MAP.get("d");
    private int keyGrab = KEY_MAP.get("esc");
    private int keySpeedUp = KEY_MAP.get("up");
    private int keySpeedDown = KEY_MAP.get("down");

    public FreeLook(Camera camera)
    {
        this.camera = camera;
    }

    @Override
    public void control(int delta)
    {
        if (Mouse.isGrabbed())
        {
            move(camera.getPosition(), camera.getRotate(), delta);
            look(camera.getRotate(), delta);
        }
    }

    @Override
    public void nextControl(int delta)
    {
        if (Keyboard.isKeyDown(keyGrab))
        {
            if (Mouse.isGrabbed())
            {
                Mouse.setGrabbed(false);
            } else
            {
                Mouse.setGrabbed(true);
            }
        }
        if (Mouse.isGrabbed())
        {
            if (Keyboard.isKeyDown(keySpeedUp))
            {
                moveSpeed += 1f;
            }
            if (Keyboard.isKeyDown(keySpeedDown))
            {
                if (moveSpeed > 0)
                {
                    moveSpeed -= 1f;
                }
            }
        }
    }

    @Override
    public boolean isActive()
    {
        return active;
    }

    @Override
    public void setActive(boolean b)
    {
        active = b;
    }

    public void setKeyUp(String keyName)
    {
        keyUp = KEY_MAP.get(keyName.toLowerCase());
    }

    public void setKeyDown(String keyName)
    {
        keyDown = KEY_MAP.get(keyName.toLowerCase());
    }

    public void setKeyLeft(String keyName)
    {
        keyLeft = KEY_MAP.get(keyName.toLowerCase());
    }

    public void setKeyRight(String keyName)
    {
        keyRight = KEY_MAP.get(keyName.toLowerCase());
    }

    public float getMoveSpeed()
    {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }

    public float getLookSpeed()
    {
        return lookSpeed;
    }

    public void setLookSpeed(float lookSpeed)
    {
        this.lookSpeed = lookSpeed;
    }

    protected void move(Vector3f position, Vector3f rotate, int delta)
    {
        UP = Keyboard.isKeyDown(keyUp);
        DOWN = Keyboard.isKeyDown(keyDown);
        LEFT = Keyboard.isKeyDown(keyLeft);
        RIGHT = Keyboard.isKeyDown(keyRight);

        if (UP && !LEFT && !RIGHT && !DOWN)
        {
            up(position, rotate, moveSpeed, delta);
        }
        if (DOWN && !UP && !LEFT && !RIGHT)
        {
            down(position, rotate, moveSpeed, delta);
        }
        if (LEFT && !RIGHT && !UP && !DOWN)
        {
            left(position, rotate, moveSpeed, delta);
        }
        if (RIGHT && !LEFT && !UP && !DOWN)
        {
            right(position, rotate, moveSpeed, delta);
        }
        if (UP && LEFT && !RIGHT && !DOWN)
        {
            upLeft(position, rotate, moveSpeed, delta);
        }
        if (UP && RIGHT && !LEFT && !DOWN)
        {
            upRight(position, rotate, moveSpeed, delta);
        }
        if (DOWN && LEFT && !RIGHT && !UP)
        {
            downLeft(position, rotate, moveSpeed, delta);
        }
        if (DOWN && RIGHT && !LEFT && !UP)
        {
            downRight(position, rotate, moveSpeed, delta);
        }
    }

    protected void look(Vector3f rotate, int delta)
    {
        DX = Mouse.getDX();
        DY = -Mouse.getDY();
        rotate.y += DX * lookSpeed * delta * LOOK_FORMAT;
        ROTATE_LIMIT.set(rotate.x, rotate.y, rotate.z);
        if ((ROTATE_LIMIT.x = ROTATE_LIMIT.x + DY * lookSpeed * LOOK_FORMAT) > MAX_LOOK_UP)
        {
            rotate.x = MAX_LOOK_UP;
        } else if ((ROTATE_LIMIT.x = ROTATE_LIMIT.x + DY * lookSpeed * LOOK_FORMAT) < MAX_LOOK_DOWN)
        {
            rotate.x = MAX_LOOK_DOWN;
        } else
        {
            rotate.x += DY * lookSpeed * delta * LOOK_FORMAT;
        }
    }

    protected void down(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, 0, 0, -moveSpeed * delta * MOVE_FORMAT);
    }

    protected void up(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, 0, 0, moveSpeed * delta * MOVE_FORMAT);
    }

    protected void right(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, -moveSpeed * delta * MOVE_FORMAT, 0, 0);
    }

    protected void left(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, moveSpeed * delta * MOVE_FORMAT, 0, 0);
    }

    protected void downRight(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, -moveSpeed * delta * MOVE_FORMAT, 0, -moveSpeed * delta * MOVE_FORMAT);
    }

    protected void downLeft(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, moveSpeed * delta * MOVE_FORMAT, 0, -moveSpeed * delta * MOVE_FORMAT);
    }

    protected void upRight(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, -moveSpeed * delta * MOVE_FORMAT, 0, moveSpeed * delta * MOVE_FORMAT);
    }

    protected void upLeft(Vector3f position, Vector3f rotate, float moveSpeed, int delta)
    {
        moveFromLook(position, rotate, moveSpeed * delta * MOVE_FORMAT, 0, moveSpeed * delta * MOVE_FORMAT);
    }

    protected void moveFromLook(Vector3f position, Vector3f rotate, float dx, float dy, float dz)
    {
        position.z += dx * (float) Math.cos(MathUtil.toRadians(rotate.y - 90)) + dz * Math.cos(MathUtil.toRadians(rotate.y));
        position.x -= dx * (float) Math.sin(MathUtil.toRadians(rotate.y - 90)) + dz * Math.sin(MathUtil.toRadians(rotate.y));
        position.y += dy * (float) Math.sin(MathUtil.toRadians(rotate.x - 90)) + dz * Math.sin(MathUtil.toRadians(rotate.x));
    }
}
