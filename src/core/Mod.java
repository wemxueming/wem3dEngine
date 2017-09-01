package core;

public class Mod {
    private Mesh[] meshes;

    public Mod(Mesh[] meshes) {
        this.meshes = meshes;
    }

    public Mesh[] getMeshes() {
        return meshes;
    }

    public void setMeshes(Mesh[] meshes) {
        this.meshes = meshes;
    }
}
