package com.arisux.avp.entities.model;

class VerticeAcid
{
	public double x;
	public double y;
	public double z;
	final ModelAcid model;

	public VerticeAcid(ModelAcid var1, double var2, double var4, double var6)
	{
		this.model = var1;
		double var8 = Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
		this.x = var2 / var8;
		this.y = var4 / var8;
		this.z = var6 / var8;
	}
}
