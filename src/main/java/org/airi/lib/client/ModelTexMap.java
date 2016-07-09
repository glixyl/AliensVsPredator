package com.arisux.airi.lib.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTexMap<MODEL extends ModelBaseWrapper>
{
    private Texture texture;
    private MODEL model;
    
    public ModelTexMap(MODEL model, Texture texture)
    {
        this.model = model;
        this.texture = texture;
    }

    public MODEL getModel()
    {
        return this.model;
    }

    public Texture getTexture()
    {
        return this.texture;
    }
    
    public void bindTexture()
    {
        this.getTexture().bind();
    }
    
    public void drawStandaloneModel()
    {
        this.drawStandaloneModel(null);
    }
    
    public void drawStandaloneModel(Object o)
    {
        this.getModel().render(o);
    }
    
    public void draw()
    {
        this.draw(null);
    }
    
    public void draw(Object o)
    {
        if (this.model != null && this.texture != null)
        {
            this.bindTexture();
            this.drawStandaloneModel(o);
        }
    }
}
