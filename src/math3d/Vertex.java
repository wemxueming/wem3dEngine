package math3d;

/**
 * Created by wem on 2017/5/14.
 */
public class Vertex {
    public static final int LENGTH = 11;

    protected Vector3f position;
    protected Vector2f texcoord;
    protected Vector3f normal;
    protected Vector3f tangent;

    public Vertex(Vector3f position, Vector2f texcoord, Vector3f normal) {
        this.position = position;
        this.texcoord = texcoord;
        this.normal = normal;
    }

    public float[] getElements() {
        if (!isTangent()) {
            return new float[]{
                    position.x, position.y, position.z,
                    texcoord.x, texcoord.y,
                    normal.x, normal.y, normal.z,
            };
        } else {
            return new float[]{
                    position.x, position.y, position.z,
                    texcoord.x, texcoord.y,
                    normal.x, normal.y, normal.z,
                    tangent.x, tangent.y, tangent.z
            };
        }
    }

    public boolean isTangent() {
        return tangent != null;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                ", texcoord=" + texcoord +
                ", normal=" + normal +
                ", setTangent=" + tangent +
                '}';
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTexcoord() {
        return texcoord;
    }

    public void setTexcoord(Vector2f texcoord) {
        this.texcoord = texcoord;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }

    public Vector3f getTangent() {
        return tangent;
    }

    public void setTangent(Vector3f tangent) {
        this.tangent = tangent;
    }
}
