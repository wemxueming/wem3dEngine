package core.gl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import util.MathUtil;

public class VertexArray
{
    private int id;
    private int location;

    public VertexArray()
    {
        id = Contexts.getContexts().createVertexArray();
    }

    public void begin()
    {
        location = 0;
        GL30.glBindVertexArray(id);
    }

    public void end()
    {
        GL30.glBindVertexArray(0);
        location = 0;
    }

    public void drawTriangle(int indiceCount)
    {
        GL11.glDrawElements(GL11.GL_TRIANGLES, indiceCount, GL11.GL_UNSIGNED_INT, location);
        location += indiceCount * MathUtil.FLOAT_BIT;
    }

    public void drawTriangleStrip(int indiceCount)
    {
        GL11.glDrawElements(GL11.GL_TRIANGLE_STRIP, indiceCount, GL11.GL_UNSIGNED_INT, location);
        location += indiceCount * MathUtil.FLOAT_BIT;
    }

    public void drawTriangleInstance(int indiceCount, int instanceCount)
    {
        GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, indiceCount, GL11.GL_UNSIGNED_INT, location, instanceCount);
        location += indiceCount * MathUtil.FLOAT_BIT;
    }

    public int id()
    {
        return id;
    }
}
