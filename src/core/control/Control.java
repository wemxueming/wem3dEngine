package core.control;

import util.Util;

import java.util.Map;

public interface Control
{
    Map<String, Integer> KEY = Util.getKeyboardMap();

    void control(int delta);

    void nextControl(int delta);

    boolean isActive();

    void setActive(boolean b);

    int id();
}
