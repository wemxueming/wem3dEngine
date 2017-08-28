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
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class Renderer
{
	private int modelProgram;

	private int cameraUbo;
	private int transformUbo;
	private int lightUbo;
	private int materialUbo;

	private IntBuffer LIGHT_SIZE_BUFFER = BufferUtils.createIntBuffer(1);


	private int modelCameraPosLoc;
	private int modelAlbedoMapLoc;
	private int modelMetallicMapLoc;
	private int modelRoughnessMapLoc;
	private int modelAoMapLoc;
	private int modelNormalMapLoc;
	private int modelHeightMapLoc;

	public Renderer(int maxLight)
	{
		Map<String, Integer> defineMap = new HashMap<String, Integer>();
		defineMap.put("MAX_LIGHT", maxLight);
		modelProgram = OGL.createProgram(loadShaderSource("src/core/glsl/model.vert",null), loadShaderSource("src/core/glsl/model.frag", defineMap));
		cameraUbo = OGL.createBuffer(Camera.PROJECTION_VIEW_SIZE, 0);
		transformUbo = OGL.createBuffer(Object3D.TRANSFORM_SIZE, 1);
		lightUbo = OGL.createBuffer(16 + Light.PER_LIGHT_SIZE * maxLight, 2);
		materialUbo = OGL.createBuffer(Material.MATERIAL_SIZE, 3);
		init();
	}

	public void init()
	{
		modelCameraPosLoc = GL20.glGetUniformLocation(modelProgram, "cameraPos");
		modelAlbedoMapLoc = GL20.glGetUniformLocation(modelProgram, "albedoMap");
		modelMetallicMapLoc = GL20.glGetUniformLocation(modelProgram, "metallicMap");
		modelRoughnessMapLoc = GL20.glGetUniformLocation(modelProgram, "roughnessMap");
		modelAoMapLoc = GL20.glGetUniformLocation(modelProgram, "aoMap");
		modelNormalMapLoc = GL20.glGetUniformLocation(modelProgram, "normalMap");
		modelHeightMapLoc = GL20.glGetUniformLocation(modelProgram, "heightMap");
	}

	public void render(Scene scene)
	{
		Camera camera = scene.getCamera();
		Map<Integer, Model> models = scene.getModels();
		Map<Integer, Light> lights = scene.getLights();

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		//render camera
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, cameraUbo);
		GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, camera.getProjectionViewBuffer());
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);

		//render lights
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, lightUbo);
		GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER,0, Buf.update(LIGHT_SIZE_BUFFER, lights.size()));
		int lightIndex = 0;
		for (Light light : lights.values())
		{
			GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER,16 + Light.PER_LIGHT_SIZE * lightIndex, light.getLightBuffer());
			lightIndex ++;
		}
		GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);

		//render model
		GL20.glUseProgram(modelProgram);
		GL20.glUniform3f(modelCameraPosLoc, -camera.getPosition().getX(), -camera.getPosition().getY(), -camera.getPosition().getZ());
		GL20.glUniform1i(modelAlbedoMapLoc, 0);
		GL20.glUniform1i(modelMetallicMapLoc, 1);
		GL20.glUniform1i(modelRoughnessMapLoc, 2);
		GL20.glUniform1i(modelAoMapLoc, 3);
		GL20.glUniform1i(modelNormalMapLoc, 4);
		GL20.glUniform1i(modelHeightMapLoc, 5);
		for (Model model : models.values())
		{
			GL30.glBindVertexArray(model.getVao());
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getEbo());
			for (Object3D instance : model.getInstances().values())
			{
				GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, transformUbo);
				GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, instance.getTransformBuffer());
				GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
				int indiceOffset = 0;
				for (Mesh mesh : instance.getMod().getMeshes())
				{
					Material material = mesh.getMaterial();

					OGL.enableTexture(GL11.GL_TEXTURE_2D, material.getAlbedoTexture(), 0);
					OGL.enableTexture(GL11.GL_TEXTURE_2D, material.getMetallicTexture(), 1);
					OGL.enableTexture(GL11.GL_TEXTURE_2D, material.getRoughnessTexture(), 2);
					OGL.enableTexture(GL11.GL_TEXTURE_2D, material.getAoTexture(), 3);
					OGL.enableTexture(GL11.GL_TEXTURE_2D, material.getNormalTexture(), 4);
					OGL.enableTexture(GL11.GL_TEXTURE_2D, material.getHeightTexture(), 5);

					GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, materialUbo);
					GL15.glBufferSubData(GL31.GL_UNIFORM_BUFFER, 0, material.getMaterialBuffer());
					GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0);
					GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndiceCount(), GL11.GL_UNSIGNED_INT, indiceOffset);
					indiceOffset += mesh.getIndiceCount() * Cal.INT_BIT;

					OGL.disableTexture(GL11.GL_TEXTURE_2D);
					OGL.disableTexture(GL11.GL_TEXTURE_2D);
					OGL.disableTexture(GL11.GL_TEXTURE_2D);
					OGL.disableTexture(GL11.GL_TEXTURE_2D);
					OGL.disableTexture(GL11.GL_TEXTURE_2D);
					OGL.disableTexture(GL11.GL_TEXTURE_2D);
				}
			}
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
			GL30.glBindVertexArray(0);
		}
		GL20.glUseProgram(0);
	}

	protected CharSequence loadShaderSource(String src, Map<String, Integer> defineMap)
	{
		BufferedReader reader = null;
		String line;
		int versionIndex = 0;
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			reader = new BufferedReader(new FileReader(src));
			while ((line = reader.readLine()) != null)
			{
				if (versionIndex == 1)
				{
					if (defineMap != null)
					{
						for (Map.Entry<String, Integer> entry : defineMap.entrySet())
						{
							stringBuilder.append("#define " + entry.getKey() + " " + entry.getValue()).append("\n");
						}
					}
					versionIndex = 2;
				}
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
				if (line.matches("(\\s*)(#version)(.*)"))
				{
					versionIndex = 1;
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
