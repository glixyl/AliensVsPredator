package com.arisux.avp.items.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.arisux.avp.AliensVsPredator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDisc extends Render
{
    private static final ResourceLocation resourceLocation = new ResourceLocation(AliensVsPredator.INSTANCE.properties.TEXTURE_PATH_DISC);

    private void renderBoomerang(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.bindTexture(resourceLocation);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glRotatef(var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var9 - 90.0F, 0.0F, 1.0F, 0.0F);
        Tessellator var10 = Tessellator.instance;
        boolean var11 = false;
        float var13 = 0.0F;
        float var14 = 0.5F;
        float var15 = 1.0F;
        float var16 = 0.08F;
        float var17 = 0.2F;
        float var18 = 0.9F;
        float var19 = 1.0F - var17;
        float var20 = 0.5F;
        float var21 = 0.65625F;
        GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        var10.startDrawingQuads();
        var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var10.addVertexWithUV(0.0D, 0.0D, 1.0D, (double)var14, (double)var13);
        var10.addVertexWithUV(1.0D, 0.0D, 1.0D, (double)var13, (double)var13);
        var10.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)var13, (double)var14);
        var10.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var14, (double)var14);

        if (var11)
        {
            var10.addVertexWithUV(0.0D, 0.0D, 1.0D, (double)var15, (double)var13);
            var10.addVertexWithUV(1.0D, 0.0D, 1.0D, (double)var14, (double)var13);
            var10.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)var14, (double)var14);
            var10.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var15, (double)var14);
        }

        var10.draw();
        GL11.glNormal3f(0.0F, -1.0F, 0.0F);
        var10.startDrawingQuads();
        var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var10.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)var13, (double)var14);
        var10.addVertexWithUV(1.0D, 0.0D, 1.0D, (double)var14, (double)var14);
        var10.addVertexWithUV(0.0D, 0.0D, 1.0D, (double)var14, (double)var13);
        var10.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var13, (double)var13);

        if (var11)
        {
            var10.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)var14, (double)var14);
            var10.addVertexWithUV(1.0D, 0.0D, 1.0D, (double)var15, (double)var14);
            var10.addVertexWithUV(0.0D, 0.0D, 1.0D, (double)var15, (double)var13);
            var10.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)var14, (double)var13);
        }

        var10.draw();
        float var22 = (float)Math.sqrt(2.0D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glNormal3f(-var22, 0.0F, var22);
        var10.startDrawingQuads();
        var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var10.addVertexWithUV((double)var17, (double)(-var16), (double)var19, (double)var14, (double)var20);
        var10.addVertexWithUV((double)var17, (double)var16, (double)var19, (double)var14, (double)var21);
        var10.addVertexWithUV((double)var18, (double)var16, (double)var19, (double)var13, (double)var21);
        var10.addVertexWithUV((double)var18, (double)(-var16), (double)var19, (double)var13, (double)var20);

        if (var11)
        {
            var10.addVertexWithUV((double)var17, (double)(-var16), (double)var19, (double)var15, (double)var20);
            var10.addVertexWithUV((double)var17, (double)var16, (double)var19, (double)var15, (double)var21);
            var10.addVertexWithUV((double)var18, (double)var16, (double)var19, (double)var14, (double)var21);
            var10.addVertexWithUV((double)var18, (double)(-var16), (double)var19, (double)var14, (double)var20);
        }

        var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var10.addVertexWithUV((double)var17, (double)(-var16), (double)var19, (double)var14, (double)var20);
        var10.addVertexWithUV((double)var17, (double)var16, (double)var19, (double)var14, (double)var21);
        var10.addVertexWithUV((double)var17, (double)var16, (double)var17, (double)var13, (double)var21);
        var10.addVertexWithUV((double)var17, (double)(-var16), (double)var17, (double)var13, (double)var20);

        if (var11)
        {
            var10.addVertexWithUV((double)var17, (double)(-var16), (double)var19, (double)var15, (double)var20);
            var10.addVertexWithUV((double)var17, (double)var16, (double)var19, (double)var15, (double)var21);
            var10.addVertexWithUV((double)var17, (double)var16, (double)var17, (double)var14, (double)var21);
            var10.addVertexWithUV((double)var17, (double)(-var16), (double)var17, (double)var14, (double)var20);
        }

        var10.draw();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.renderBoomerang(var1, var2, var4, var6, var8, var9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return resourceLocation;
    }
}