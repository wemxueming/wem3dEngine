package core;

import core.control.Control;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Map;

public class Controller
{
    private Map<Integer, Control> controls = new HashMap<Integer, Control>();
    private Timer timer;

    public Controller(Timer timer)
    {
        this.timer = timer;
    }

    public void update()
    {
        if (Keyboard.next())
        {
            for (Control control : controls.values())
            {
                if (control.isActive())
                {
                    control.nextControl(timer.delta());
                }
            }
        }
        for (Control control : controls.values())
        {
            if (control.isActive())
            {
                control.control(timer.delta());
            }
        }
    }

    public Map<Integer, Control> getControls()
    {
        return controls;
    }

    public void setControls(Map<Integer, Control> controls)
    {
        this.controls = controls;
    }

    public Timer getTimer()
    {
        return timer;
    }

    public void setTimer(Timer timer)
    {
        this.timer = timer;
    }
}
