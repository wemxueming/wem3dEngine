package core;
import core.util.OGL;
import core.util.Utils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import java.util.HashMap;
import java.util.Map;

public class Scene
{
	private boolean running;
	private long time;
	private long lastDeltaTime;
	private long lastFrameTime;
	private int frame;
	private int delta;
	private int fps;

	private String title = "wem3d scene";
	private int width = 800;
	private int height = 600;
	private boolean fullScreen = false;
	private boolean syncFps= true;
	private int maxFps = 100;

	private Renderer renderer;
	private Map<Integer, Model> models = new HashMap<Integer, Model>();

	public Scene()
	{
		init();
		renderer = new Renderer();
	}

	protected void init()
	{
		setTitle(title);
		setMode(width, height, fullScreen);
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAttribs = new ContextAttribs(4, 4)
				.withForwardCompatible(true).withProfileCore(true);
		try
		{
			Display.create(pixelFormat, contextAttribs);
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glViewport(0,0, width, height);
	}

	public void run()
	{
		lastDeltaTime = Utils.getTime();
		lastFrameTime = Utils.getTime();
		running = Display.isCreated();
		while (running && !Display.isCloseRequested())
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glEnable(GL11.GL_DEPTH_TEST);

			renderer.render(models);

			if (syncFps)
			{
				Display.sync(maxFps);
			}
			Display.update();
			updateTime();
		}
		OGL.destroy();
		Display.destroy();
	}

	protected void updateTime()
	{
		time = Utils.getTime();
		delta = (int)(time - lastDeltaTime);
		lastDeltaTime = time;
		if (Utils.getTime() - lastFrameTime > 1000)
		{
			fps = frame;
			frame = 0;
			lastFrameTime += 1000;
		}
		frame ++;
		//System.out.println(fps);
	}

	public void setTitle(String title)
	{
		this.title = title;
		Display.setTitle(title);
	}

	public void setMode(int width, int height, boolean fullScreen)
	{
		this.width = width;
		this.height = height;
		this.fullScreen = fullScreen;
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height) && (Display.isFullscreen() == fullScreen))
		{
			return;
		}
		try
		{
			DisplayMode targetDisplayMode = null;

			if (fullScreen)
			{
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i = 0; i < modes.length; i++)
				{
					DisplayMode current = modes[i];

					if ((current.getWidth() == width) && (current.getHeight() == height))
					{
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq))
						{
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()))
							{
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()))
						{
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else
			{
				targetDisplayMode = new DisplayMode(width, height);
			}
			if (targetDisplayMode == null)
			{
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullScreen);
				return;
			}
			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullScreen);

		} catch (LWJGLException e)
		{
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullScreen + e);
		}
	}

	public String getTitle()
	{
		return title;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public boolean isFullScreen()
	{
		return fullScreen;
	}

	public boolean isSyncFps()
	{
		return syncFps;
	}

	public void setSyncFps(boolean syncFps)
	{
		this.syncFps = syncFps;
	}

	public int getMaxFps()
	{
		return maxFps;
	}

	public void setMaxFps(int maxFps)
	{
		this.maxFps = maxFps;
	}
}
