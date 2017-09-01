package core.gl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Contexts {
    private Map<Integer, Integer> PROGRAM_MAP = new HashMap<Integer, Integer>();
    private Map<Integer, List<Integer>> SHADER_MAP = new HashMap<Integer, List<Integer>>();
    private Map<Integer, Integer> VAO_MAP = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> VBO_MAP = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> TEXTURE_MAP = new HashMap<Integer, Integer>();

    private static Contexts CONTEXTS = new Contexts();

    private Contexts() {

    }

    public int createProgram() {
        int program = GL20.glCreateProgram();
        List<Integer> shaderList = new ArrayList<Integer>();
        PROGRAM_MAP.put(program, program);
        SHADER_MAP.put(program, shaderList);
        return program;
    }

    public int createShader(int program, int type) {
        int shader = GL20.glCreateShader(type);
        SHADER_MAP.get(program).add(shader);
        return shader;
    }

    public int createVertexArray() {
        int vao = GL30.glGenVertexArrays();
        VAO_MAP.put(vao, vao);
        return vao;
    }

    public int createBuffer() {
        int vbo = GL15.glGenBuffers();
        VBO_MAP.put(vbo, vbo);
        return vbo;
    }

    public int createTexture() {
        int texture = GL11.glGenTextures();
        TEXTURE_MAP.put(texture, texture);
        return texture;
    }

    public void destroy() {
        for (int texture : TEXTURE_MAP.values()) {
            GL11.glDeleteTextures(texture);
        }
        for (int vbo : VBO_MAP.values()) {
            GL15.glDeleteBuffers(vbo);
        }
        for (int vao : VAO_MAP.values()) {
            GL30.glDeleteVertexArrays(vao);
        }
        for (int program : PROGRAM_MAP.values()) {
            for (int shader : SHADER_MAP.get(program)) {
                GL20.glDetachShader(program, shader);
                GL20.glDeleteShader(shader);
            }
            GL20.glDeleteProgram(program);
        }
    }

    public static Contexts getContexts() {
        return CONTEXTS;
    }
}
