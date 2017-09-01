package core;

import util.Util;

import java.util.Map;

public interface Controller
{
    Map<String, Integer> KEY_MAP = Util.getKeyboardMap();

    void control(int delta);

    void nextControl(int delta);

    boolean isActive();

    void setActive(boolean b);
}
