package com.arisux.avp.entities.model;

class Vertex
{
	public double x, y, z;
	private ModelAcid model;

	public Vertex(ModelAcid model, double x, double y, double z)
	{
		double sq = Math.sqrt(x * x + y * y + z * z);
		this.model = model;
		this.x = x / sq;
		this.y = y / sq;
		this.z = z / sq;
	}

	public ModelAcid getModel()
	{
		return model;
	}
}
