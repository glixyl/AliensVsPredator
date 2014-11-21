package com.arisux.avp.util;

public enum PlayerMode
{
	NORMAL(0), MARINE(1), PREDATOR(2), XENOMORPH(3), MATRIARCH(4);
	
	public int id;
	
	private PlayerMode(int id)
	{
		this.id = id;
	}
}
