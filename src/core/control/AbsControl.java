package core.control;

public abstract class AbsControl implements Control
{
    private static int ID = -1;
    private int id;
    protected boolean active;

    public AbsControl()
    {
        id = ++ID;
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

    @Override
    public int id()
    {
        return id;
    }
}
