package org.avp.items.render;

import org.lwjgl.opengl.GL11;

import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GlStateManager;
import com.arisux.airi.lib.client.ItemRenderer;
import com.arisux.airi.lib.client.ModelBaseWrapper;
import com.arisux.airi.lib.client.Texture;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class RenderItemSummoner extends ItemRenderer
{
    private Class<? extends Entity> entityClass;
    private float                   scale;
    private float                   x;
    private float                   y;
    private ModelBaseWrapper        modelCache;
    private Texture                 textureCache;
    private Entity                  entityCache;
    private Render                  renderCache;

    public RenderItemSummoner(Class<? extends Entity> entityClass)
    {
        super(null);
        this.entityClass = entityClass;
    }

    public RenderItemSummoner setX(float x)
    {
        this.x = x;
        return this;
    }

    public RenderItemSummoner setY(float y)
    {
        this.y = y;
        return this;
    }

    public RenderItemSummoner setScale(float scale)
    {
        this.scale = scale;
        return this;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        GlStateManager.disable(GL11.GL_CULL_FACE);
        super.renderItem(type, item, data);
    }

    public void renderCachedModel()
    {
//        if (entityCache == null)
//        {
//            entityCache = Entities.constructEntity(Minecraft.getMinecraft().theWorld, this.entityClass);
//        }

        if (renderCache == null)
        {
            renderCache = RenderManager.instance.getEntityClassRenderObject(entityClass);
        }

        if (renderCache instanceof RendererLivingEntity)
        {
//            if (modelCache == null)
            {
                modelCache = new ModelBaseWrapper(AccessWrapper.getMainModel((RendererLivingEntity) renderCache));
            }

            if (textureCache == null)
            {
                textureCache = new Texture(AccessWrapper.getEntityTexture(renderCache, entityCache));
            }

            GlStateManager.pushMatrix();
            {
                GlStateManager.enableLighting();
                GlStateManager.scale(1F, -1F, 1F);
                GlStateManager.translate(0F, -1F, 0F);
                GlStateManager.rotate(180F, 0F, 0F, 1F);
                GlStateManager.rotate(-45F, 0F, 1F, 0F);
                textureCache.bind();
                modelCache.render();
                GlStateManager.disableLight();
                GlStateManager.disableLighting();
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
//        scale = 0.5F;
//        GlStateManager.rotate(195F, 1.0F, 0.0F, 0.0F);
//        GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(30F, 0.0F, 0.0F, 1.0F);
//        GlStateManager.translate(-0.5F, 0F, 0F);
//        GlStateManager.scale(scale, scale, scale);
//        this.renderCachedModel();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
//        GlStateManager.rotate(195F, 1.0F, 0.0F, 0.0F);
//        GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(30F, 0.0F, 0.0F, 1.0F);
//        GlStateManager.translate(-25F, 0F + y, 20.85F);
//        GlStateManager.scale(scale, scale, scale);
//        this.renderCachedModel();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
//        scale = 7.5F;
//        GlStateManager.translate(8F + x, -1.77F + y, -4F);
//        GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
//        GlStateManager.scale(7.5F, 7.5F, 7.5F);
//        GlStateManager.enable(GL11.GL_DEPTH_TEST);
//        GlStateManager.enable(GL11.GL_ALPHA_TEST);
//        GlStateManager.enable(GL11.GL_BLEND);
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        this.renderCachedModel();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
//        GlStateManager.rotate(180F, 0.0F, 0.0F, 1F);
//        GlStateManager.rotate(90F, 0.0F, 1F, 0F);
//
//        if (entityClass.getSuperclass() == EntityXenomorph.class || entityClass == EntityYautja.class || entityClass == EntityMarine.class)
//        {
//            GlStateManager.scale(0.5F, 0.5F, 0.5F);
//            GlStateManager.translate(0F, -0.5F, -0.5F);
//        }
//        else if (entityClass.getSuperclass() == EntityChestburster.class)
//        {
//            GlStateManager.scale(0.5F, 0.5F, 0.5F);
//            GlStateManager.translate(0F, -1.5F, -0.5F);
//        }
//        else
//        {
//            GlStateManager.scale(1F, 1F, 1F);
//            GlStateManager.translate(0F, -1.25F, 0F);
//        }
//
//        this.renderCachedModel();
    }
}
