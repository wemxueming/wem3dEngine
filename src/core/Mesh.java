package core;

public class Mesh
{
    private int count;
    private Material material;

    public Mesh(int count, Material material)
    {
        this.count = count;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
