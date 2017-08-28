package core;

import core.util.Utils;
import java.util.Map;

public interface Controller
{
	Map<String, Integer> KEY_MAP = Utils.getKeyboardMap();
	void control(int delta);
	void nextControl(int delta);
	boolean isActive();
	void setActive(boolean b);
}
