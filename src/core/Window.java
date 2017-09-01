package core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class Window {
    private String title;
    private int width;
    private int height;
    private boolean fullScreen;
    private boolean syncFps;
    private int maxFps;

    public Window() {
        this("3D Window", 800, 600, false, true, 100);
    }

    public Window(String title, int width, int height, boolean fullScreen, boolean syncFps, int maxFps) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.fullScreen = fullScreen;
        this.syncFps = syncFps;
        this.maxFps = maxFps;
        init();
    }

    protected void init() {
        setTitle(title);
        setMode(width, height, fullScreen);
        PixelFormat pixelFormat = new PixelFormat();
        ContextAttribs contextAttribs = new ContextAttribs(4, 4)
                .withForwardCompatible(true).withProfileCore(true);
        try {
            Display.create(pixelFormat, contextAttribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        GL11.glViewport(0, 0, width, height);
    }

    public void update() {
        if (syncFps) {
            Display.sync(maxFps);
        }
        Display.update();
    }

    public void destroy() {
        Display.destroy();
    }

    public void setTitle(String title) {
        this.title = title;
        Display.setTitle(title);
    }

    public void setMode(int width, int height, boolean fullScreen) {
        this.width = width;
        this.height = height;
        this.fullScreen = fullScreen;
        if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullScreen)) {
            return;
        }
        try {
            DisplayMode targetDisplayMode = null;

            if (fullScreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i = 0; i < modes.length; i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width, height);
            }
            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullScreen);
                return;
            }
            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullScreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullScreen + e);
        }
    }

    public void setSyncFps(boolean syncFps) {
        this.syncFps = syncFps;
    }

    public void setMaxFps(int maxFps) {
        this.maxFps = maxFps;
    }

    public boolean isCreated() {
        return Display.isCreated();
    }

    public boolean isClosed() {
        return Display.isCloseRequested();
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public boolean isSyncFps() {
        return syncFps;
    }

    public int getMaxFps() {
        return maxFps;
    }
}
