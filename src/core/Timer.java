package core;

public class Timer {
    private long lastDeltaTime;
    private long lastFrameTime;
    private int frame;
    private int delta;
    private int fps;

    public void start() {
        lastDeltaTime = getTime();
        lastFrameTime = getTime();
    }

    public void update() {
        long time = getTime();
        delta = (int) (time - lastDeltaTime);
        lastDeltaTime = time;
        if (getTime() - lastFrameTime > 1000) {
            fps = frame;
            frame = 0;
            lastFrameTime += 1000;
        }
        frame++;
        //System.out.println(fps);
    }

    public int getDelta() {
        return delta;
    }

    public int getFps() {
        return fps;
    }

    public static long getTime() {
        return System.nanoTime() / 1000000;
    }
}
