package core.gl;

import math3d.Matrix4f;
import math3d.Vector2f;
import math3d.Vector3f;
import math3d.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import java.nio.FloatBuffer;

public class Shader {
    private static FloatBuffer MAT4_BUFFER = BufferUtils.createFloatBuffer(16);
    private int program;


    public Shader() {
        program = Contexts.getContexts().createProgram();
    }

    public int createVertexShader(CharSequence string) {
        int shader = Contexts.getContexts().createShader(program, GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(shader, string);
        GL20.glCompileShader(shader);
        return shader;
    }

    public int createFragmentShader(CharSequence string) {
        int shader = Contexts.getContexts().createShader(program, GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(shader, string);
        GL20.glCompileShader(shader);
        return shader;
    }

    public int createGeometryShader(CharSequence string) {
        int shader = Contexts.getContexts().createShader(program, GL32.GL_GEOMETRY_SHADER);
        GL20.glShaderSource(shader, string);
        GL20.glCompileShader(shader);
        return shader;
    }

    public void addShader(int shader) {
        GL20.glAttachShader(program, shader);
    }

    public void build() {
        GL20.glLinkProgram(program);
        GL20.glValidateProgram(program);
    }

    public void begin() {
        GL20.glUseProgram(program);
    }

    public void end() {
        GL20.glUseProgram(0);
    }

    public int getUniform(String name) {
        return GL20.glGetUniformLocation(program, name);
    }

    public void setUniform(int location, int value) {
        GL20.glUniform1i(location, value);
    }

    public void setUniform(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    public void setUniform(int location, boolean value) {
        if (value) {
            setUniform(location, 1);
        } else {
            setUniform(location, 0);
        }
    }

    public void setUniform(int location, Vector2f value) {
        if (value != null) {
            GL20.glUniform2f(location, value.getX(), value.getY());
        }
    }

    public void setUniform(int location, Vector3f value) {
        if (value != null) {
            GL20.glUniform3f(location, value.getX(), value.getY(), value.getZ());
        }
    }

    public void setUniform(int location, Vector4f value) {
        if (value != null) {
            GL20.glUniform4f(location, value.getX(), value.getY(), value.getZ(), value.getW());
        }
    }

    public void setUniform(int location, Matrix4f value) {
        if (value != null) {
            MAT4_BUFFER.clear();
            value.store(MAT4_BUFFER);
            MAT4_BUFFER.flip();
            GL20.glUniformMatrix4(location, false, MAT4_BUFFER);
        }
    }
}
