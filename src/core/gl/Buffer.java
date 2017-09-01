package core.gl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import util.MathUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffer {
    private int id;
    private int target;

    public Buffer(FloatBuffer buffer, int start, int... formats) {
        id = Contexts.getContexts().createBuffer();
        target = GL15.GL_ARRAY_BUFFER;
        int stride = MathUtil.toTotal(formats) * MathUtil.FLOAT_BIT;
        GL15.glBindBuffer(target, id);
        GL15.glBufferData(target, buffer, GL15.GL_STATIC_DRAW);
        formatBuffer(formats, stride, start, false, 1);
        GL15.glBindBuffer(target, 0);
    }

    public Buffer(int size, int start, int... formats) {
        id = Contexts.getContexts().createBuffer();
        target = GL15.GL_ARRAY_BUFFER;
        int stride = MathUtil.toTotal(formats) * MathUtil.FLOAT_BIT;
        GL15.glBindBuffer(target, id);
        GL15.glBufferData(target, BufferUtils.createFloatBuffer(size * stride), GL15.GL_STATIC_DRAW);
        formatBuffer(formats, stride, start, true, 1);
        GL15.glBindBuffer(target, 0);
    }

    public Buffer(IntBuffer buffer) {
        id = Contexts.getContexts().createBuffer();
        target = GL15.GL_ELEMENT_ARRAY_BUFFER;
        GL15.glBindBuffer(target, id);
        GL15.glBufferData(target, buffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(target, 0);
    }

    public Buffer(int size, int binding) {
        target = GL31.GL_UNIFORM_BUFFER;
        id = Contexts.getContexts().createBuffer();
        GL15.glBindBuffer(target, id);
        GL15.glBufferData(target, size, GL15.GL_DYNAMIC_DRAW);
        GL15.glBindBuffer(target, 0);
        GL30.glBindBufferBase(target, binding, id);
    }

    public void begin() {
        GL15.glBindBuffer(target, id);
    }

    public void setBuffer(int location, FloatBuffer buffer) {
        GL15.glBufferSubData(target, location, buffer);
    }

    public void end() {
        GL15.glBindBuffer(target, 0);
    }

    protected void formatBuffer(int[] formats, int stride, int start, boolean divisored, int divisor) {
        int offset = 0;
        int length = formats.length + start;
        for (int i = start; i < length; i++) {
            int size = formats[i - start];
            GL20.glEnableVertexAttribArray(i);
            GL20.glVertexAttribPointer(i, size, GL11.GL_FLOAT, false, stride, offset);
            offset += size * MathUtil.FLOAT_BIT;
            if (divisored) {
                GL33.glVertexAttribDivisor(i, divisor);
            }
        }
    }

    public int getId() {
        return id;
    }
}
