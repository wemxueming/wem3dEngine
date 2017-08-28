package core;
import core.util.Buf;
import core.util.Utils;
import core.util.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Loader
{
	public static Model createPlan(float width, float height, boolean faced)
	{
		List<Vertex> vertexs = new ArrayList<Vertex>();
		if (faced)
		{
			vertexs.add(new Vertex(-width/2, -height/2, 0).setTexcoord(0, 0).setNormal(0, 0, 1));
			vertexs.add(new Vertex(width/2, -height/2, 0).setTexcoord(1, 0).setNormal(0, 0, 1));
			vertexs.add(new Vertex(-width/2, height/2, 0).setTexcoord(0, 1).setNormal(0, 0, 1));
			vertexs.add(new Vertex(width/2, height/2, 0).setTexcoord(1, 1).setNormal(0, 0, 1));
		}
		else
		{
			vertexs.add(new Vertex(-width/2, 0, -height/2).setTexcoord(0, 0).setNormal(0, 1, 0));
			vertexs.add(new Vertex(width/2, 0, -height/2).setTexcoord(1, 0).setNormal(0, 1, 0));
			vertexs.add(new Vertex(-width/2, 0, height/2).setTexcoord(0, 1).setNormal(0, 1, 0));
			vertexs.add(new Vertex(width/2, 0, height/2).setTexcoord(1, 1).setNormal(0, 1, 0));
		}
		int[] arrays = new int[]{
				0,2,1,
				1,2,3
		};
		List<Integer> indices = Utils.asList(arrays);
		Mod mod = new Mod(new Mesh[]{new Mesh(indices.size(), new Material())});
		return new Model(Buf.createF(vertexs), Buf.createI(indices), mod);
	}
}
