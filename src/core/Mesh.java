package core;
public class Mesh
{
	private int indiceCount;
	private Material material;

	public Mesh(int indiceCount, Material material)
	{
		this.indiceCount = indiceCount;
		this.material = material;
	}

	public Material getMaterial()
	{
		return material;
	}

	public void setMaterial(Material material)
	{
		this.material = material;
	}

	public int getIndiceCount()
	{
		return indiceCount;
	}

	public void setIndiceCount(int indiceCount)
	{
		this.indiceCount = indiceCount;
	}
}
