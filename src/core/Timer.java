package core;

import util.Util;

public class Timer
{
    private long lastDeltaTime;
    private long lastFrameTime;
    private int frame;
    private int delta;
    private int fps;

    public void start()
    {
        lastDeltaTime = Util.getTime();
        lastFrameTime = Util.getTime();
    }

    public void update()
    {
        long time = Util.getTime();
        delta = (int) (time - lastDeltaTime);
        lastDeltaTime = time;
        if (Util.getTime() - lastFrameTime > 1000)
        {
            fps = frame;
            frame = 0;
            lastFrameTime += 1000;
        }
        frame++;
        //System.out.println(fps);
    }

    public int delta()
    {
        return delta;
    }

    public int fps()
    {
        return fps;
    }


}
