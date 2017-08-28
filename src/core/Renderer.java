package core;
import core.util.Buf;
import core.util.Cal;
import core.util.Io;
import core.util.OGL;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Map;

public class Renderer
{
	private int modelProgram;

	private int cameraUbo;
	private int modelUbo;

	private FloatBuffer MAT4_BUFFER = BufferUtils.createFloatBuffer(16);
	private FloatBuffer VEC3_BUFFER = BufferUtils.createFloatBuffer(3);

	private int modelCameraPosLoc;

	public Renderer()
	{
		modelProgram = OGL.createProgram(loadShaderSource("src/core/glsl/model.vert"), loadShaderSource("src/core/glsl/model.frag"));
		cameraUbo = OGL.createBuffer(64 + 64, 0);
		modelUbo = OGL.createBuffer(64, 1);
		init();
	}

	public void init()
	{
		modelCameraPosLoc = GL20.glGetUniformLocation(modelProgram, "cameraPos");
	}

	public void render(Scene scene)
	{
		Camera camera = scene.getCamera();
		Map<Integer, Model> models = scene.getModels();

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, cameraUbo);
		GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, Buf.update(MAT4_BUFFER, camera.getProjectionMatrix()));
		GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 64, Buf.update(MAT4_BUFFER, camera.getViewMatrix()));
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);


		GL20.glUseProgram(modelProgram);
		GL20.glUniform3f(modelCameraPosLoc, -camera.getPosition().getX(), -camera.getPosition().getY(), -camera.getPosition().getZ());
		for (Model model : models.values())
		{
			GL30.glBindVertexArray(model.getVao());
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getEbo());
			for (Object3D instance : model.getInstances().values())
			{
				GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, modelUbo);
				GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, Buf.update(MAT4_BUFFER, instance.getTransformMatrix()));
				GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
				int indiceOffset = 0;
				for (Mesh mesh : instance.getMod().getMeshes())
				{
					GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndiceCount(), GL11.GL_UNSIGNED_INT, indiceOffset);
					indiceOffset += mesh.getIndiceCount() * Cal.INT_BIT;
				}
			}
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
			GL30.glBindVertexArray(0);
		}
		GL20.glUseProgram(0);
	}

	protected CharSequence loadShaderSource(String src)
	{
		BufferedReader reader = null;
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			reader = new BufferedReader(new FileReader(src));
			while ((line = reader.readLine()) != null)
			{
				if (line.startsWith("#include "))
				{
					String subFileSrc = line.split("\\s+")[1].trim();
					CharSequence subFile = Io.readString(subFileSrc);
					stringBuilder.append(subFile).append("\n");
				}
				else
				{
					stringBuilder.append(line).append("\n");
				}
			}
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return stringBuilder;
	}
}
