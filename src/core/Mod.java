package core;
public class Mod
{
	private Mesh[] meshes;

	public Mod(Mesh[] meshs)
	{
		this.meshes = meshs;
	}

	public Mesh[] getMeshes()
	{
		return meshes;
	}

	public void setMeshes(Mesh[] meshes)
	{
		this.meshes = meshes;
	}
}
