package core;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Map;

public class Control
{
	private Map<String, Controller> controllers = new HashMap<String, Controller>();

	public void update(int delta)
	{
		if (Keyboard.next())
		{
			for (Controller controller : controllers.values())
			{
				if (controller.isActive())
				{
					controller.nextControl(delta);
				}
			}
		}
		for (Controller controller : controllers.values())
		{
			if (controller.isActive())
			{
				controller.control(delta);
			}
		}
	}

	public Map<String, Controller> getControllers()
	{
		return controllers;
	}

	public void setControllers(Map<String, Controller> controllers)
	{
		this.controllers = controllers;
	}
}
