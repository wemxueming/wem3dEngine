package core;
import core.util.Io;
import core.util.OGL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Renderer
{
	private int modelProgram;

	public Renderer()
	{
		modelProgram = OGL.createProgram(loadShaderSource("core/glsl/model.vert"), loadShaderSource("core/glsl/model.frag"));
	}

	public void render(Map<Integer, Model> models)
	{
		GL20.glUseProgram(modelProgram);
		for (Model model : models.values())
		{
			GL30.glBindVertexArray(model.getVao());
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getEbo());
			GL11.glDrawElements(GL11.GL_TRIANGLES, 0, GL11.GL_UNSIGNED_INT, 0);
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
